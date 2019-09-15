package top.gunplan.netty.httpd.demo.test.control.error;


import top.gunplan.netty.httpd.anno.GunHttpmapping;
import top.gunplan.netty.httpd.handle.GunHttpMappingHandle;
import top.gunplan.netty.httpd.protocols.AbstractGunHttp2Response;
import top.gunplan.netty.httpd.protocols.BaseGunHttp2Response;
import top.gunplan.netty.httpd.protocols.GunHttpStdInfo;
import top.gunplan.netty.protocol.GunNetInbound;

@GunHttpmapping(mappingRule = "/*")
public class NotFoundPage implements GunHttpMappingHandle<AbstractGunHttp2Response> {

    @Override
    public AbstractGunHttp2Response doOutput(GunNetInbound protocl) {
        BaseGunHttp2Response response = new BaseGunHttp2Response() {
            @Override
            public String toResponse() {
                return "<!DOCTYPE html>" +
                        "<html>" +
                        "<head>" +
                        "</head>" +
                        "<body>" +
                        "<h1>love mcw</h1>" +
                        "" +
                        "</body>" +
                        "</html>";
            }
        };
        response.setProtocolType(GunHttpStdInfo.HttpProtoclType.HTTP1_1);
        response.setContentType(GunHttpStdInfo.ContentType.TEXT_HTML);
        response.setCode(GunHttpStdInfo.statusCode.CLIENT_ERROR);
        return response;
    }
}
