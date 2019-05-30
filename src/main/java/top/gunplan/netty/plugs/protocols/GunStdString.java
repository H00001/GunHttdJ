package top.gunplan.netty.plugs.protocols;

import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;

/**
 * @author dosdrtt
 */
public class GunStdString implements GunNetInputInterface, GunNetOutputInterface {
    private String value = null;

    @Override
    public boolean unSerialize(byte[] in) {
        value = new String(in);
        return true;
    }

    public String getString() {
        return value;
    }

    @Override
    public byte[] serialize() {
        return value.getBytes();
    }
}
