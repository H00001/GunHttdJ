package top.gunplan.httpd.example.example.control.error;


import top.gunplan.netty.httpd.anno.GunHttpMapping;
import top.gunplan.netty.httpd.handle.GunHttpMappingHandle;
import top.gunplan.netty.httpd.protocols.AbstractGunHttp2Response;
import top.gunplan.netty.httpd.protocols.BaseGunHttp2Response;
import top.gunplan.netty.httpd.protocols.GunHttp2InputProtocol;
import top.gunplan.netty.httpd.protocols.GunHttpStdInfo;

@GunHttpMapping(mappingRule = "/*")
public class NotFoundPage implements GunHttpMappingHandle<AbstractGunHttp2Response> {

    @Override
    public AbstractGunHttp2Response doOutput(GunHttp2InputProtocol protocol) {
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
        response.setProtocolType(GunHttpStdInfo.HttpProtocolType.HTTP1_1);
        response.getHeaderBuilder().withContentType(GunHttpStdInfo.ContentType.TEXT_HTML);
        response.setCode(GunHttpStdInfo.statusCode.NOT_FOUND);
        return response;
    }
}
