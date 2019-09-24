package top.gunplan.netty.httpd.protocols;

import top.gunplan.netty.impl.GunNetInBoundOutBound;

/**
 * @author dosdrtt
 */
public class GunStdString implements GunNetInBoundOutBound {
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
