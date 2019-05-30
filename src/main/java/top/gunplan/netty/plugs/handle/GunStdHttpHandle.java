package top.gunplan.netty.plugs.handle;


import top.gunplan.netty.plugs.anno.GunHttpmapping;
import top.gunplan.netty.plugs.protocols.AbstractGunHttp2Response;
import top.gunplan.netty.plugs.protocols.GunHttp2InputProtocl;
import top.gunplan.netty.GunException;
import top.gunplan.netty.GunNettyHandle;
import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.utils.AbstractGunBaseLogUtil;
import top.gunplan.utils.GunDirectoryUtil;
import top.gunplan.utils.GunStringUtil;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * this class need to rely on {@link GunHttp2InputProtocl}
 *
 * @author dosdrtt
 */

public class GunStdHttpHandle implements GunNettyHandle {
    private Map<String, Class<? extends GunHttpMappingHandle<AbstractGunHttp2Response>>> urlMapping = new HashMap<>();
    private Map<String, GunHttpMappingHandle<AbstractGunHttp2Response>> urlMappingObject = new ConcurrentHashMap<>();

//    private void loadServicesTest() {
 //      ServiceLoader<GunHttpMappingHandle> services = ServiceLoader.load(GunHttpMappingHandle.class);
//
//        Iterator<GunHttpMappingHandle<? extends GunNetOutputInterface>> handel = services.iterator();
//    }

    public GunStdHttpHandle(final String handlePackName) {


      //  loadServicesTest();
        ClassLoader loader = this.getClass().getClassLoader();
        List<GunDirectoryUtil.GunMappingFileReference> classfiles;
        try {
            classfiles = GunDirectoryUtil.scanAllFilesFromDirectory(loader.getResource("").getPath().replace("%20", " ") + handlePackName.replace(".", "/"),".class");
        } catch (IOException e) {
            throw new GunException("please check the packet path is true "+handlePackName);
        }
        assert classfiles != null;
        classfiles.parallelStream().forEach(classname -> {
            Class<? extends GunHttpMappingHandle<AbstractGunHttp2Response>> httpMapping;
            try {
                /**
                 *
                 * warning：It could be inside class in Mappingclass with out GunHttpmapping Annotation(
                 */
                httpMapping = (Class<? extends GunHttpMappingHandle<AbstractGunHttp2Response>>) loader.loadClass(handlePackName + classname.getBase() + classname.getClcasfile().getName().replace(".class", ""));
                if (httpMapping.isAnnotationPresent(GunHttpmapping.class)) {
                    urlMapping.put(httpMapping.getAnnotation(GunHttpmapping.class).mappingRule(), httpMapping);
                }

            } catch (ClassNotFoundException e) {
                throw new GunException(e);
            }
        });
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
        Class<? extends GunHttpMappingHandle<AbstractGunHttp2Response>> dealhandel = urlMapping.get(requestUrl);
        GunHttpMappingHandle<AbstractGunHttp2Response> instance0;
        while (dealhandel == null) {
            requestUrl = GunStringUtil.removeLastUrl(requestUrl);
            if ((instance0 = urlMappingObject.get(requestUrl + "*")) != null) {
                return instance0;
            }
            dealhandel = urlMapping.get(requestUrl + "*");
            if ("/".equals(requestUrl) && dealhandel == null) {
                throw new GunException("404 or 404 pages not found");
            }
        }
        try {
            final GunHttpMappingHandle<AbstractGunHttp2Response> instance = dealhandel.getConstructor().newInstance();
            urlMappingObject.put(requestUrl, instance);
            return instance;
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
