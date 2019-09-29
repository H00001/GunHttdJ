package top.gunplan.netty.httpd.protocols;

public enum RPCProtocolParamType {
    /**
     *
     */
    INT((byte) 0x01), STRING((byte) 0x02),ERROR((byte)0x1c);
    byte val;

    RPCProtocolParamType(byte val) {
        this.val = val;
    }

    public static RPCProtocolParamType valueFrom(byte val) {
        RPCProtocolParamType[] types = values();
        for (RPCProtocolParamType tp : types) {
            if (tp.val == val) {
                return tp;
            }
        }
        return ERROR;
    }
}
