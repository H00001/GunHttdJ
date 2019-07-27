package top.gunplan.netty.httpd.handle;

import top.gunplan.netty.protocol.GunNetInbound;
import top.gunplan.netty.protocol.GunNetOutBound;


/**
 * @param <T> extends GunNetOutputInterface
 * @author dosdrtt
 * @see
 * @see GunNetInbound
 */
public interface GunHttpMappingHandle<T extends GunNetOutBound> {
    /**
     * @param protocl {@link GunNetInbound}
     * @return {@link top.gunplan.netty.protocol.GunNetOutBound}
     */
    T doOutput(GunNetInbound protocl);
}

