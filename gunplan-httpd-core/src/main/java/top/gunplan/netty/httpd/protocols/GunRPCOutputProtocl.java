package top.gunplan.netty.httpd.protocols;

import top.gunplan.utils.GunBytesUtil;

/**
 *
 */
public class GunRPCOutputProtocl extends AbstractGunRPCProtocl {
    @Override
    public boolean unSerialize(byte[] in) {
        GunBytesUtil.GunReadByteStream util = new GunBytesUtil.GunReadByteStream(in);
        publicUnSet(util);
        returnValue = readOnceParam(util);
        return checkEnd(util);
    }

    private Object returnValue;

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }


    @Override
    public byte[] serialize() {
        int len = returnValue instanceof Integer ? 6 + 5 : 6 + ((String) returnValue).length() + 2;
        byte[] serize = new byte[len];
        GunBytesUtil.GunWriteByteStream serizUtil = new GunBytesUtil.GunWriteByteStream(serize);
        publicSet(serizUtil);
        writeOnceParam(serizUtil, returnValue);
        serizUtil.write(endFlage);
        return serize;
    }
}
