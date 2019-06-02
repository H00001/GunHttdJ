package top.gunplan.netty.httpd.handle;


import top.gunplan.netty.httpd.GunHttpdException;
import top.gunplan.netty.httpd.anno.GunHttpBaseContent;
import top.gunplan.netty.httpd.anno.GunHttpmapping;
import top.gunplan.netty.httpd.property.GunHttpProperty;
import top.gunplan.netty.httpd.protocols.AbstractGunHttp2Response;
import top.gunplan.netty.httpd.protocols.GunHttp2InputProtocl;
import top.gunplan.netty.GunException;
import top.gunplan.netty.GunNettyHandle;
import top.gunplan.netty.impl.GunNettyPropertyManagerImpl;
import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.utils.AbstractGunBaseLogUtil;
import top.gunplan.utils.GunDirectoryUtil;
import top.gunplan.utils.GunStringUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * this class need to rely on {@link GunHttp2InputProtocl}
 *
 * @author dosdrtt
 */

public class GunStdHttpHandle implements GunNettyHandle {
    private final Map<String, GunHttpMappingHandle<AbstractGunHttp2Response>> um = new ConcurrentHashMap<>();

    @Override
    public int init() {
        final String handlePackName =         GunNettyPropertyManagerImpl.getProperty(GunHttpProperty.class).getScannPacket();
        ClassLoader loader = this.getClass().getClassLoader();
        List<GunDirectoryUtil.GunMappingFileReference> classfiles;
        try {
            classfiles = GunDirectoryUtil.scanAllFilesFromDirectory(loader.getResource("").getPath().replace("%20", " ") + handlePackName.replace(".", "/"), ".class");
        } catch (IOException e) {
            throw new GunException("please check the packet path is true " + handlePackName);
        }
        classfiles.parallelStream().forEach(classname -> {
            Class<? extends GunHttpMappingHandle<AbstractGunHttp2Response>> hm;
            try {
                /**
                 *
                 * warning：It could be inside class in Mappingclass with out GunHttpmapping Annotation(
                 */
                hm = (Class<? extends GunHttpMappingHandle<AbstractGunHttp2Response>>) loader.loadClass(handlePackName + classname.getBase() + classname.getClcasfile().getName().replace(".class", ""));
                if (hm.isAnnotationPresent(GunHttpmapping.class)) {
                    um.put(hm.isAnnotationPresent(GunHttpBaseContent.class) ? hm.getAnnotation(GunHttpBaseContent.class).baseContent() + hm.getAnnotation(GunHttpmapping.class).mappingRule()
                            : hm.getAnnotation(GunHttpmapping.class).mappingRule(), hm.getDeclaredConstructor().newInstance());
                }

            } catch (ClassNotFoundException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                throw new GunHttpdException(e);
            }

        });
        return 0;
    }

    public GunStdHttpHandle() {
        //  loadServicesTest();

    }

    @Override
    public GunNetOutputInterface dealDataEvent(GunNetInputInterface requestInterface) throws GunException {
        GunHttp2InputProtocl request = ((GunHttp2InputProtocl) requestInterface);
        AbstractGunBaseLogUtil.debug("request:" + request.getRequestUrl(), "[CONNECTION][HTTP]");
        GunHttpMappingHandle<AbstractGunHttp2Response> runner = null;
        try {
            runner = findHandelandRun(request.getRequestUrl());
        } catch (Exception exp) {
            AbstractGunBaseLogUtil.error(exp.getMessage());
        }
        assert runner != null;
        return runner.doOutput(request);
        //     localUrlMapping.get().
    }

    private GunHttpMappingHandle<AbstractGunHttp2Response> findHandelandRun(String requestUrl) throws GunException {
        GunHttpMappingHandle<AbstractGunHttp2Response> dealhandel = um.get(requestUrl);
        GunHttpMappingHandle<AbstractGunHttp2Response> instance0;
        while (dealhandel == null) {
            requestUrl = GunStringUtil.removeLastUrl(requestUrl);
            if ((instance0 = um.get(requestUrl + "*")) != null) {
                return instance0;
            }
            dealhandel = um.get(requestUrl + "*");
            if ("/".equals(requestUrl) && dealhandel == null) {
                throw new GunHttpdException("404 or 404 pages not found");
            }
        }
        try {
            //um.put(requestUrl, dealhandel);
            return dealhandel;
        } catch (Exception e) {
            throw new GunException(e);
        }
    }


    @Override
    public GunNetOutputInterface dealConnEvent(SocketChannel channel) throws GunException {
        AbstractGunBaseLogUtil.debug("connected....", "[CONNECTION]");
        return null;
    }

    @Override
    public void dealCloseEvent() {
        AbstractGunBaseLogUtil.urgency("CLOSED");
    }

    @Override
    public void dealExceptionEvent(Exception exp) {
        AbstractGunBaseLogUtil.urgency("CLOSED");
        exp.printStackTrace();
    }

}
