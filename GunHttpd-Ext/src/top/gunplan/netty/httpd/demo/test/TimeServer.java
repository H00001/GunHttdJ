package top.gunplan.netty.httpd.demo.test;


import top.gunplan.netty.httpd.anno.GunHttpmapping;
import top.gunplan.netty.httpd.protocols.BaseGunHttp2Response;
import top.gunplan.netty.httpd.handle.GunHttpMappingHandle;
import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.netty.protocol.resputil.GunMappingJsonResp;

@GunHttpmapping(mappingRule = "/time")
public class TimeServer implements GunHttpMappingHandle<GunNetOutputInterface> {
    public TimeServer() {
    }


    @Override
    public GunNetOutputInterface doOutput(GunNetInputInterface protocl) {
        BaseGunHttp2Response response = new BaseGunHttp2Response() {
            @Override
            public String toResponse() {
                GunMappingJsonResp resp = new GunMappingJsonResp();
                resp.put("time", String.valueOf(System.currentTimeMillis()));
                return resp.toTransfer();
            }
        };
        response.setIswrite(true);
        return response;
    }
}
