package top.gunplan.httpd.example.example.control;

import top.gunplan.netty.httpd.anno.GunHttpMapping;
import top.gunplan.netty.httpd.handle.GunHttpMappingHandle;
import top.gunplan.netty.httpd.protocols.BaseGunHttp2Response;
import top.gunplan.netty.httpd.protocols.GunHttp2InputProtocol;
import top.gunplan.netty.httpd.protocols.GunHttpStdInfo;
import top.gunplan.netty.protocol.GunNetOutbound;


/**
 * @author dosdrtt
 */
@GunHttpMapping(mappingRule = "/index.aspx")
public class BaseMapping implements GunHttpMappingHandle<GunNetOutbound> {
    public BaseMapping() {

    }

    @Override
    public GunNetOutbound doOutput(GunHttp2InputProtocol protocol) {
        BaseGunHttp2Response response = new BaseGunHttp2Response() {
            @Override
            public String toResponse() {
                return "<!DOCTYPE html>" +
                        "<html>" +
                        "<head>" +
                        "</head>" +
                        "<body>" +
                        "<h1>this is a web server by GunNetty</h1>" +
                        "<p>get start <a href=\"http://h00001.github.io\">download</a></p>" +
                        "</body>" +
                        "</html>";
            }
        };
        response.setProtocolType(GunHttpStdInfo.HttpProtocolType.HTTP1_1);
        response.getHeaderBuilder().withContentType(GunHttpStdInfo.ContentType.TEXT_HTML);
        return response;
        // return (;
    }
}
