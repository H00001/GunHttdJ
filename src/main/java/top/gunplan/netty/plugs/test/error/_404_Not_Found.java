package top.gunplan.netty.plugs.test.error;


import top.gunplan.netty.plugs.anno.GunHttpmapping;
import top.gunplan.netty.plugs.handle.GunHttpMappingHandle;
import top.gunplan.netty.plugs.protocols.AbstractGunHttp2Response;
import top.gunplan.netty.plugs.protocols.BaseGunHttp2Response;
import top.gunplan.netty.plugs.protocols.GunHttpStdInfo;
import top.gunplan.netty.protocol.GunNetInputInterface;

@GunHttpmapping(mappingRule = "/*")
public class _404_Not_Found implements GunHttpMappingHandle<AbstractGunHttp2Response> {

    @Override
    public AbstractGunHttp2Response doOutput(GunNetInputInterface protocl) {
        BaseGunHttp2Response response = new BaseGunHttp2Response() {
            @Override
            public String toResponse() {
                return "<!DOCTYPE html>" +
                        "<html>" +
                        "<head>" +
                        "</head>" +
                        "<body>" +
                        "<h1>404 n0t found</h1>" +
                        "" +
                        "</body>" +
                        "</html>";
            }
        };
        response.setIswrite(true);
        response.setProtoclType(GunHttpStdInfo.HttpProtoclType.HTTP1_1);
        response.setContentType(GunHttpStdInfo.ContentType.TEXT_HTML);
        response.setCode(GunHttpStdInfo.statusCode.NOTFOUND);
        return response;
    }
}
