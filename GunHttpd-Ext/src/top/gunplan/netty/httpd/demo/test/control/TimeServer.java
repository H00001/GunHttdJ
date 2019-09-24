package top.gunplan.netty.httpd.demo.test.control;


import top.gunplan.netty.httpd.anno.GunHttpmapping;
import top.gunplan.netty.httpd.handle.GunHttpMappingHandle;
import top.gunplan.netty.httpd.protocols.BaseGunHttp2Response;
import top.gunplan.netty.httpd.protocols.GunHttp2InputProtocol;
import top.gunplan.netty.protocol.GunNetOutbound;

@GunHttpmapping(mappingRule = "/time")
public class TimeServer implements GunHttpMappingHandle<GunNetOutbound> {
    @Override
    public GunNetOutbound doOutput(GunHttp2InputProtocol protocol) {
        return new BaseGunHttp2Response() {
            @Override
            public String toResponse() {
                return System.currentTimeMillis() + "";
            }
        };
    }
}
