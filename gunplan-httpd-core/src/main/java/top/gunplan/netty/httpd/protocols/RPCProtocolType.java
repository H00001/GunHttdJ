package top.gunplan.netty.httpd.protocols;

/**
 * @author dosdrtt
 * @deprecated
 */

@Deprecated
public enum RPCProtocolType {
    /**
     *
     */
    REQUEST(0x0001), RESPONSE(0x0002);
    int value;

    RPCProtocolType(int i) {
        this.value = i;
    }

    public static RPCProtocolType valuefrom(int val) {
        RPCProtocolType[] types = values();
        for (RPCProtocolType tp : types) {
            if (tp.value == val) {
                return tp;
            }
        }
        return null;
    }
}
