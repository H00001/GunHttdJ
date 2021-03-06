package top.gunplan.httpd.example.example.control;

import top.gunplan.netty.httpd.anno.GunHttpBaseContent;
import top.gunplan.netty.httpd.anno.GunHttpMapping;
import top.gunplan.netty.httpd.handle.GunHttpMappingHandle;
import top.gunplan.netty.httpd.protocols.BaseGunHttp2Response;
import top.gunplan.netty.httpd.protocols.GunHttp2InputProtocol;
import top.gunplan.netty.httpd.protocols.GunHttpStdInfo;
import top.gunplan.netty.protocol.GunNetOutbound;


@GunHttpMapping(mappingRule = "/manage/*")
@GunHttpBaseContent
public class ForbiddenMapping implements GunHttpMappingHandle<GunNetOutbound> {

    @Override
    public GunNetOutbound doOutput(GunHttp2InputProtocol protocol) {
        BaseGunHttp2Response response = new BaseGunHttp2Response() {
            @Override
            public String toResponse() {
                return "<h1>403 forbidden</h1><hr/><center>windows server iis 1998</center>";
            }
        };
        response.setCode(GunHttpStdInfo.statusCode.FORBIDDEN);
        response.setProtocolType(GunHttpStdInfo.HttpProtocolType.HTTP2_0);
        response.getHeaderBuilder().withContentType(GunHttpStdInfo.ContentType.TEXT_HTML);
        return response;
    }

}
