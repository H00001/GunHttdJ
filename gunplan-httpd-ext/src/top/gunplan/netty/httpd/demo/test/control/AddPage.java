package top.gunplan.netty.httpd.demo.test.control;

import top.gunplan.netty.httpd.anno.GunHttpBaseContent;
import top.gunplan.netty.httpd.anno.GunHttpmapping;
import top.gunplan.netty.httpd.handle.GunHttpMappingHandle;
import top.gunplan.netty.httpd.protocols.BaseGunHttp2Response;
import top.gunplan.netty.httpd.protocols.GunHttp2InputProtocol;
import top.gunplan.netty.httpd.protocols.GunHttpStdInfo;
import top.gunplan.netty.protocol.GunNetOutbound;

import java.util.Arrays;
import java.util.HashMap;

@GunHttpmapping(mappingRule = "/AddPage")
@GunHttpBaseContent
public class AddPage implements GunHttpMappingHandle<GunNetOutbound> {
    public AddPage() {

    }

    @Override
    public GunNetOutbound doOutput(GunHttp2InputProtocol protocol) {
        String[] params = protocol.getRequestBody().split("&");
        HashMap<String, String> params_map = new HashMap<>(1);
        Arrays.asList(params).forEach(c -> {
            params_map.put(c.split("=")[0], c.split("=")[1]);
        });
        System.out.print(params_map.get("title"));
        System.out.print(params_map.get("url"));
        BaseGunHttp2Response response = new BaseGunHttp2Response() {
            @Override
            public String toResponse() {
                return "{\"status\":\"succeed\" }";
            }
        };
        response.setProtocolType(GunHttpStdInfo.HttpProtoclType.HTTP1_1);
        response.setContentType(GunHttpStdInfo.ContentType.TEXT_JSON);

        return response;
        // return (;
    }
}

