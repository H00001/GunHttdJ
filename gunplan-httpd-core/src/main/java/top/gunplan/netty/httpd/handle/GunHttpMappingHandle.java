package top.gunplan.netty.httpd.handle;

import top.gunplan.netty.httpd.protocols.GunHttp2InputProtocol;
import top.gunplan.netty.protocol.GunNetInbound;
import top.gunplan.netty.protocol.GunNetOutbound;


/**
 * @param <T> extends GunNetOutputInterface
 * @author dosdrtt
 * @see
 * @see GunNetInbound
 */
public interface GunHttpMappingHandle<T extends GunNetOutbound> {

    /**
     * doOutput
     *
     * @param protocol {@link GunNetInbound}
     * @return {@link top.gunplan.netty.protocol.GunNetOutbound}
     */
    T doOutput(GunHttp2InputProtocol protocol);
}

