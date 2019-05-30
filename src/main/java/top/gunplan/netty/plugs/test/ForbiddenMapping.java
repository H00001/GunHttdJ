package top.gunplan.netty.plugs.test;

import top.gunplan.netty.plugs.anno.GunHttpBaseContent;
import top.gunplan.netty.plugs.anno.GunHttpmapping;
import top.gunplan.netty.plugs.handle.GunHttpMappingHandle;
import top.gunplan.netty.plugs.protocols.BaseGunHttp2Response;
import top.gunplan.netty.plugs.protocols.GunHttpStdInfo;
import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;

@GunHttpmapping(mappingRule = "/manage/*")
@GunHttpBaseContent
public class ForbiddenMapping implements GunHttpMappingHandle<GunNetOutputInterface> {

    @Override
    public GunNetOutputInterface doOutput(GunNetInputInterface protocl) {
        BaseGunHttp2Response response = new BaseGunHttp2Response() {

            @Override
            public String toResponse() {
                return "403";
            }
        };
        response.setIswrite(true);
        response.setCode(GunHttpStdInfo.statusCode.FORBIDDEN);
        response.setProtoclType(GunHttpStdInfo.HttpProtoclType.HTTP2_0);
        response.setContentType(GunHttpStdInfo.ContentType.TEXT_HTML);

        return response;
        // return (;
    }

}
