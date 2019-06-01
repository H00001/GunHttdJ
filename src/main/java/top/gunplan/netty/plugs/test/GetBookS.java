package top.gunplan.netty.plugs.test;

import top.gunplan.netty.plugs.anno.GunHttpmapping;
import top.gunplan.netty.plugs.handle.GunHttpMappingHandle;
import top.gunplan.netty.plugs.protocols.BaseGunHttp2Response;
import top.gunplan.netty.plugs.protocols.GunHttpStdInfo;
import top.gunplan.netty.plugs.test.dto.Gmh;
import top.gunplan.netty.plugs.util.GunHttpdJsonUtilFactory;
import top.gunplan.netty.plugs.util.GunHttpdJsonUtilInterface;
import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.netty.protocol.resputil.GunMappingJsonResp;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@GunHttpmapping(mappingRule = "/getBooks.gmh")
public class GetBookS implements GunHttpMappingHandle<GunNetOutputInterface> {

    @Override
    public GunNetOutputInterface doOutput(GunNetInputInterface protocl) {
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
