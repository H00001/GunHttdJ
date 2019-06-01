package top.gunplan.netty.plugs.protocols;

import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.netty.protocol.GunProtoclContorl;

import java.lang.reflect.InvocationTargetException;

/**
 * @author dosdrtt
 * @version 0.0.0.1
 */
public abstract class AbstractGunHttp2Response implements GunNetOutputInterface, GunProtoclContorl {

    @Override
    public byte[] serialize() {
        try {
            return getResponseBody().getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * this function is used to set conf response result
     *
     * @return response string
     */
    public abstract String getResponseBody() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, Exception;
}
