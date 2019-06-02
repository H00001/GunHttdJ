package top.gunplan.netty.httpd.handle;

import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;

/**
 * @param <T> extends GunNetOutputInterface
 * @see GunNetOutputInterface
 * @see GunHttpMappingHandle
 * @author dosdrtt
 */
public interface GunHttpMappingHandle<T extends GunNetOutputInterface> {
    /**
     *
     * @param protocl {@link GunNetInputInterface}
     * @return {@link GunNetOutputInterface}
     */
    T doOutput(GunNetInputInterface protocl);
}

