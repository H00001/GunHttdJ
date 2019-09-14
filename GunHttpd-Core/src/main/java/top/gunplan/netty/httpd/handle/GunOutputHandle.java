package top.gunplan.netty.httpd.handle;

import top.gunplan.netty.GunChannelException;
import top.gunplan.netty.GunNettyChildrenHandle;
import top.gunplan.netty.common.GunNettyContext;
import top.gunplan.netty.filter.GunNettyFilter;
import top.gunplan.netty.httpd.protocols.GunStdString;
import top.gunplan.netty.protocol.GunNetInbound;
import top.gunplan.netty.protocol.GunNetOutbound;

import java.net.SocketAddress;

/**
 * GunOutputHandle
 */
public class GunOutputHandle implements GunNettyChildrenHandle {


    @Override
    public GunNetOutbound dealDataEvent(GunNetInbound m) {
        if (m instanceof GunStdString) {
            GunStdString httpProtocol = ((GunStdString) m);
            GunNettyContext.logger.urgency(httpProtocol.getString());
        }
        return null;
    }



    @Override
    public void dealCloseEvent(SocketAddress socketAddress) {

    }

    @Override
    public GunNettyFilter.DealResult dealExceptionEvent(GunChannelException e) {
        return null;
    }


}

