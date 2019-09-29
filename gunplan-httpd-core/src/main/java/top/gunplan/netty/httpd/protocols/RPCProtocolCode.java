package top.gunplan.netty.httpd.protocols;

/**
 * @author dosdrtt
 */

public enum RPCProtocolCode {
    /**
     *
     */
    SUCCEED(0x00);
    int value;

    RPCProtocolCode(int i) {
        this.value = i;
    }

    public static RPCProtocolCode valueFrom(int val) {
        RPCProtocolCode[] types = values();
        for (RPCProtocolCode tp : types) {
            if (tp.value == val) {
                return tp;
            }
        }
        return null;
    }
}
