package top.gunplan.netty.httpd.protocols;

import top.gunplan.netty.impl.GunNetInBoundOutBound;
import top.gunplan.utils.GunBytesUtil;

import java.io.Serializable;
import java.util.Stack;

/**
 * AbstractGunRPCProtocol
 */
public abstract class AbstractGunRPCProtocol implements GunNetInBoundOutBound {
//    @Test
//    public void test() {
//        AbstractGunRPCProtocl it = new AbstractGunRPCProtocl();
//        it.setInterfaceName("hello");
//        it.setMethodName("rpc");
//        it.setType(RPCProtoclType.REQUEST);
//        it.setCode(RPCProtoclCode.SUCCEED);
//        it.poshParam("1234");
//
//        byte[] bom = it.serialize();
//        AbstractGunRPCProtocl it2 = new AbstractGunRPCProtocl();
//        it2.unSerialize(bom);
//        System.out.println("dd");
//    }

    // type 2 method 2 interfaceNamelen 1 interfacename ? methodNamelen 1 methodNamel? paramlen 1 end 2
    RPCProtocolType type;
    RPCProtocolCode code;
    Object[] parameters;

    Stack<Serializable> param = new Stack<>();
    byte[] endFlags = {0x0a, 0x05};

    public RPCProtocolType getType() {
        return type;
    }

    public void setType(RPCProtocolType type) {
        this.type = type;
    }

    public RPCProtocolCode getCode() {
        return code;
    }

    public void setCode(RPCProtocolCode code) {
        this.code = code;
    }

    void writeOnceParam(GunBytesUtil.GunWriteByteStream util, Object parama) {
        if (parama instanceof Integer) {
            util.writeByte(RPCProtocolParamType.INT.val);
            util.write32((Integer) parama);

        } else if (parama instanceof String) {
            util.writeByte(RPCProtocolParamType.STRING.val);
            util.writeByte((byte) ((String) parama).length());
            util.write((String) parama);
        }

    }

    Object readOnceParam(GunBytesUtil.GunReadByteStream util) {
        RPCProtocolParamType ptypei = RPCProtocolParamType.valueFrom(util.readByte());
        switch (ptypei) {
            case INT:
                return util.readInt32();
            case STRING:
                byte len = util.readByte();
                return new String(util.readByte(len));
            default:
                break;
        }
        return null;

    }

    void publicSet(GunBytesUtil.GunWriteByteStream util) {
        util.write(type.value);
        util.write(code.value);
    }


    boolean checkEnd(GunBytesUtil.GunReadByteStream unserizutil) {
        byte[] end = unserizutil.readByte(2);
        return GunBytesUtil.compareBytesFromEnd(end, endFlags[0], endFlags[1]);
    }

    void publicUnSet(GunBytesUtil.GunReadByteStream unserizutil) {
        this.type = RPCProtocolType.valuefrom(unserizutil.readInt());
        this.code = RPCProtocolCode.valueFrom(unserizutil.readInt());
    }


}
