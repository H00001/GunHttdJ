package top.gunplan.httpd.example.example.control;

import top.gunplan.netty.httpd.handle.GunHttpMappingHandle;
import top.gunplan.netty.httpd.protocols.GunHttp2InputProtocol;
import top.gunplan.netty.protocol.GunNetOutbound;

public class InputServlet implements GunHttpMappingHandle<GunNetOutbound> {
    @Override
    public GunNetOutbound doOutput(GunHttp2InputProtocol protocol) {
        // protocol.getRequestBody()
        // return;
        return null;
    }
}
