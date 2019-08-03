package top.gunplan.netty.httpd.handle;

import top.gunplan.netty.GunChannelException;
import top.gunplan.netty.GunException;
import top.gunplan.netty.GunNettyHandle;
import top.gunplan.netty.common.GunNettyContext;
import top.gunplan.netty.httpd.protocols.GunStdString;
import top.gunplan.netty.protocol.GunNetInbound;
import top.gunplan.netty.protocol.GunNetOutbound;

import java.net.SocketAddress;

/**
 * GunOutputHandle
 */
public class GunOutputHandle implements GunNettyHandle {


    @Override
    public GunNetOutbound dealDataEvent(GunNetInbound m) {
        if (m instanceof GunStdString) {
            GunStdString httpProtocol = ((GunStdString) m);
            GunNettyContext.logger.urgency(httpProtocol.getString());
        }
        return null;
    }

    @Override
    public GunNetOutbound dealConnEvent(SocketAddress socketAddress) throws GunException {
        return null;
    }


    @Override
    public void dealCloseEvent() {
        GunNettyContext.logger.debug("CLOSED ");
    }

    @Override
    public void dealExceptionEvent(GunChannelException e) {
        GunNettyContext.logger.error(e);
    }


}

