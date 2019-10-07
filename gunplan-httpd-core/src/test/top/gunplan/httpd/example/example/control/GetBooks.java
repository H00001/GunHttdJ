package top.gunplan.httpd.example.example.control;

import top.gunplan.netty.httpd.anno.GunHttpMapping;
import top.gunplan.netty.httpd.handle.GunHttpMappingHandle;
import top.gunplan.netty.httpd.protocols.BaseGunHttp2Response;
import top.gunplan.netty.httpd.protocols.GunHttp2InputProtocol;
import top.gunplan.netty.httpd.protocols.GunHttpStdInfo;
import top.gunplan.netty.httpd.util.GunHttpdJsonUtilFactory;
import top.gunplan.netty.httpd.util.GunHttpdJsonUtilInterface;
import top.gunplan.netty.protocol.GunNetOutbound;

import java.lang.reflect.InvocationTargetException;

/**
 * @author dosdrtt
 */
@GunHttpMapping(mappingRule = "/getBooks.gmh")
public class GetBooks implements GunHttpMappingHandle<GunNetOutbound> {

    @Override
    public GunNetOutbound doOutput(GunHttp2InputProtocol protocol) {
        BaseGunHttp2Response response = new BaseGunHttp2Response() {
            @Override
            public String toResponse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
                GunHttpdJsonUtilInterface ifc = GunHttpdJsonUtilFactory.newBzJsonUtil();
                ifc.refObject(new Gmh());
                return ifc.formatToString();
            }
        };
        response.getHeaderBuilder().withContentType(GunHttpStdInfo.ContentType.TEXT_JSON);
        return response;
    }
}
