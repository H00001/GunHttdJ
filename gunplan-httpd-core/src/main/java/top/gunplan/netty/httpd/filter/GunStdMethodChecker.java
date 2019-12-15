package top.gunplan.netty.httpd.filter;

import top.gunplan.netty.GunChannelException;
import top.gunplan.netty.anno.GunNetFilterOrder;
import top.gunplan.netty.filter.GunNettyInboundFilter;
import top.gunplan.netty.httpd.protocols.GunHttp2InputProtocol;
import top.gunplan.netty.httpd.protocols.GunHttpStdInfo;
import top.gunplan.netty.impl.checker.GunInboundChecker;

@GunNetFilterOrder(index = 2)
public class GunStdMethodChecker implements GunNettyInboundFilter {

    @Override
    public DealResult doInputFilter(GunInboundChecker gunInboundChecker) throws GunChannelException {
        GunHttp2InputProtocol httpMessage = (GunHttp2InputProtocol) gunInboundChecker.transferTarget();
        if (httpMessage.getMethod() != GunHttpStdInfo.GunHttpRequestType.GET) {
            gunInboundChecker.channel().generalClose();
            return DealResult.CLOSED;
        }
        return DealResult.NEXT;
    }
}
