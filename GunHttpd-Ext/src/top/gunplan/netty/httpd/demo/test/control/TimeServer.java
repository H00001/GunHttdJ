package top.gunplan.netty.httpd.demo.test.control;


import top.gunplan.netty.httpd.anno.GunHttpmapping;
import top.gunplan.netty.httpd.handle.GunHttpMappingHandle;
import top.gunplan.netty.httpd.protocols.BaseGunHttp2Response;
import top.gunplan.netty.protocol.GunNetInbound;
import top.gunplan.netty.protocol.GunNetOutbound;

@GunHttpmapping(mappingRule = "/time")
public class TimeServer implements GunHttpMappingHandle<GunNetOutbound> {
    public TimeServer() {
    }


    @Override
    public GunNetOutbound doOutput(GunNetInbound protocol) {
        return new BaseGunHttp2Response() {
            @Override
            public String toResponse() {
                return System.currentTimeMillis() + "";
            }
        };
    }
}
