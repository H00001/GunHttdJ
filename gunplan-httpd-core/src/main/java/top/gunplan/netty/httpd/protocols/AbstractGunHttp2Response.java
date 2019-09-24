package top.gunplan.netty.httpd.protocols;

import top.gunplan.netty.common.GunNettyContext;
import top.gunplan.netty.protocol.GunNetOutbound;

import java.lang.reflect.InvocationTargetException;

/**
 * @author dosdrtt
 * @version 0.0.0.2
 */
public abstract class AbstractGunHttp2Response implements GunNetOutbound {

    @Override
    public byte[] serialize() {
        try {
            return getResponseBody().getBytes();
        } catch (Exception e) {
            GunNettyContext.logger.error(e);
        }
        return null;
    }

    /**
     * @return String
     * @throws NoSuchMethodException     NoSuchMethodException
     * @throws IllegalAccessException    IllegalAccessException
     * @throws InvocationTargetException InvocationTargetException
     */
    public abstract String getResponseBody() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;
}
