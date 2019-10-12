package top.gunplan.netty.httpd.handle;


import top.gunplan.netty.GunChannelException;
import top.gunplan.netty.GunException;
import top.gunplan.netty.GunNettyChildrenHandle;
import top.gunplan.netty.GunNettySystemService;
import top.gunplan.netty.common.GunNettyContext;
import top.gunplan.netty.common.GunNettyExecutors;
import top.gunplan.netty.filter.GunNettyFilter;
import top.gunplan.netty.httpd.GunHttpdException;
import top.gunplan.netty.httpd.anno.GunHttpBaseContent;
import top.gunplan.netty.httpd.anno.GunHttpMapping;
import top.gunplan.netty.httpd.property.GunHttpProperty;
import top.gunplan.netty.httpd.protocols.AbstractGunHttp2Response;
import top.gunplan.netty.httpd.protocols.GunHttp2InputProtocol;
import top.gunplan.netty.protocol.GunNetInbound;
import top.gunplan.netty.protocol.GunNetOutbound;
import top.gunplan.utils.GunDirectoryUtil;
import top.gunplan.utils.GunLogger;
import top.gunplan.utils.GunStringUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * this class need to rely on {@link GunHttp2InputProtocol}
 *
 * @author dosdrtt
 */

public class GunStdHttpHandle implements GunNettyChildrenHandle, Runnable {
    private static final GunLogger LOG = GunNettyContext.logger;
    private static final Map<String, GunHttpMappingHandle<AbstractGunHttp2Response>> um = new ConcurrentHashMap<>();
    private final ScheduledExecutorService ses = GunNettyExecutors.newScheduleExecutorPool(1);

    @SuppressWarnings("unchecked")
    private void scanLoop() throws IOException {
        URL url = this.getClass().getResource("");
        final URLClassLoader loader = new URLClassLoader(new URL[]{url});
        final String handlePackName = GunNettySystemService.PROPERTY_MANAGER.acquireProperty(GunHttpProperty.class).getScanPacket();
        List<GunDirectoryUtil.GunMappingFileReference> classFiles;
        try {
            classFiles = GunDirectoryUtil.scanAllFilesFromDirectory(loader.getResource("").getPath().replace("%20", " ") + handlePackName.replace(".", "/"), ".class");
        } catch (IOException e) {
            throw new GunHttpdException("please check the packet path is true " + handlePackName);
        }
        classFiles.parallelStream().forEach(classname -> {
            Class<? extends GunHttpMappingHandle<AbstractGunHttp2Response>> hm;
            try {
                /**
                 * warning：It could be inside class in Mapping class with out GunHttp mapping Annotation
                 */
                hm = (Class<? extends GunHttpMappingHandle<AbstractGunHttp2Response>>) loader.loadClass(handlePackName + classname.getBase() + classname.getClcasfile().getName().replace(".class", ""));
                if (hm.isAnnotationPresent(GunHttpMapping.class)) {
                    um.put(hm.isAnnotationPresent(GunHttpBaseContent.class) ? hm.getAnnotation(GunHttpBaseContent.class).baseContent() + hm.getAnnotation(GunHttpMapping.class).mappingRule()
                            : hm.getAnnotation(GunHttpMapping.class).mappingRule(), hm.getDeclaredConstructor().newInstance());
                }

            } catch (ClassNotFoundException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                throw new GunHttpdException(e);
            }

        });
        loader.close();

    }

    @Override
    public int init() {
        ses.scheduleAtFixedRate(this, 1000, 1000, TimeUnit.MILLISECONDS);
        try {
            scanLoop();
        } catch (Exception e) {
            LOG.error(e);
        }
        return 0;
    }

    public GunStdHttpHandle() {
        //  loadServicesTest();
    }

    @Override
    public GunNetOutbound dealDataEvent(GunNetInbound requestInterface) throws GunException {
        GunHttp2InputProtocol request = ((GunHttp2InputProtocol) requestInterface);
        LOG.debug("request:" + request.getRequestUrl(), "[CONNECTION][HTTP]");
        GunHttpMappingHandle<AbstractGunHttp2Response> runner = null;
        try {
            runner = findHandleRun(request.getRequestUrl());
        } catch (Exception exp) {
            LOG.error(exp.getMessage());
        }
        assert runner != null;
        return runner.doOutput(request);
    }

    private GunHttpMappingHandle<AbstractGunHttp2Response> findHandleRun(String requestUrl) throws GunException {
        GunHttpMappingHandle<AbstractGunHttp2Response> dealHandel = um.get(requestUrl);
        GunHttpMappingHandle<AbstractGunHttp2Response> instance0;
        while (dealHandel == null) {
            requestUrl = GunStringUtil.removeLastUrl(requestUrl);
            if ((instance0 = um.get(requestUrl + "*")) != null) {
                return instance0;
            }
            dealHandel = um.get(requestUrl + "*");
            if ("/".equals(requestUrl) && dealHandel == null) {
                throw new GunHttpdException("404 or 404 pages not found");
            }
        }
        return dealHandel;
    }


    @Override
    public void dealCloseEvent(SocketAddress socketAddress) {
        LOG.debug("CLOSED");
    }

    @Override
    public GunNettyFilter.DealResult dealExceptionEvent(GunChannelException e) {
        return null;
    }


    @Override
    public void run() {
        try {
            scanLoop();
        } catch (IOException e) {
            LOG.error(e);
        }
    }
}
