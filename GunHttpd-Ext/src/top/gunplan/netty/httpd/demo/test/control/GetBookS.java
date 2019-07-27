package top.gunplan.netty.httpd.demo.test.control;

import top.gunplan.netty.httpd.anno.GunHttpmapping;
import top.gunplan.netty.httpd.demo.test.dto.Gmh;
import top.gunplan.netty.httpd.handle.GunHttpMappingHandle;
import top.gunplan.netty.httpd.protocols.BaseGunHttp2Response;
import top.gunplan.netty.httpd.protocols.GunHttpStdInfo;
import top.gunplan.netty.httpd.util.GunHttpdJsonUtilFactory;
import top.gunplan.netty.httpd.util.GunHttpdJsonUtilInterface;
import top.gunplan.netty.protocol.GunNetInbound;
import top.gunplan.netty.protocol.GunNetOutBound;
import top.gunplan.netty.protocol.resputil.GunMappingJsonResp;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @demo
 * @author dosdrtt
 */
@GunHttpmapping(mappingRule = "/getBooks.gmh")
public class GetBookS implements GunHttpMappingHandle<GunNetOutBound> {

    @Override
    public GunNetOutBound doOutput(GunNetInbound protocl) {
        BaseGunHttp2Response response = new BaseGunHttp2Response() {

            @Override
            public String toResponse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

                List<GunMappingJsonResp> resps = new ArrayList<>();
                GunMappingJsonResp resp = new GunMappingJsonResp();
                //connection db


                GunHttpdJsonUtilInterface ifc = GunHttpdJsonUtilFactory.newBzJsonUtil();
                ifc.refObject(new Gmh());
                return ifc.formatToString();
            }
        };
        response.setIswrite(true);

        response.setContentType(GunHttpStdInfo.ContentType.TEXT_JSON);

        return response;
    }
}
