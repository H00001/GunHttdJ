package top.gunplan.netty.plugs.handle;

import top.gunplan.netty.plugs.protocols.GunStdString;
import top.gunplan.netty.GunException;
import top.gunplan.netty.GunNettyHandle;
import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.nio.channels.SocketChannel;

/**
 * GunOutputHandle
 */
public class GunOutputHandle implements GunNettyHandle {


    @Override
    public GunNetOutputInterface dealDataEvent(GunNetInputInterface m) {
        if (m instanceof GunStdString) {
            GunStdString httpProtocl = ((GunStdString) m);
            AbstractGunBaseLogUtil.urgency(httpProtocl.getString());
        }
        return null;
    }

    @Override
    public GunNetOutputInterface dealConnEvent(SocketChannel channel) throws GunException {
        AbstractGunBaseLogUtil.error("CONNECTED ");
        return null;
    }

    @Override
    public void dealCloseEvent() {
        AbstractGunBaseLogUtil.error("CLOSED ");
    }

    @Override
    public void dealExceptionEvent(Exception exp) {

    }


}

