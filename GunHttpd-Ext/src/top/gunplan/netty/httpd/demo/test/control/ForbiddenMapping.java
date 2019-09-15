package top.gunplan.netty.httpd.demo.test.control;

import top.gunplan.netty.httpd.anno.GunHttpBaseContent;
import top.gunplan.netty.httpd.anno.GunHttpmapping;
import top.gunplan.netty.httpd.handle.GunHttpMappingHandle;
import top.gunplan.netty.httpd.protocols.BaseGunHttp2Response;
import top.gunplan.netty.httpd.protocols.GunHttpStdInfo;
import top.gunplan.netty.protocol.GunNetInbound;
import top.gunplan.netty.protocol.GunNetOutbound;


@GunHttpmapping(mappingRule = "/manage/*")
@GunHttpBaseContent
public class ForbiddenMapping implements GunHttpMappingHandle<GunNetOutbound> {

    @Override
    public GunNetOutbound doOutput(GunNetInbound protocol) {
        BaseGunHttp2Response response = new BaseGunHttp2Response() {
            @Override
            public String toResponse() {
                return "<h1>403 forbidden</h1><hr/><center>windows server iis 1998</center>";
            }
        };
        response.setCode(GunHttpStdInfo.statusCode.FORBIDDEN);
        response.setProtocolType(GunHttpStdInfo.HttpProtoclType.HTTP2_0);
        response.setContentType(GunHttpStdInfo.ContentType.TEXT_HTML);
        return response;
    }

}
