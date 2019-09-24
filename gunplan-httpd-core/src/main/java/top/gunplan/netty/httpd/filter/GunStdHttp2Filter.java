package top.gunplan.netty.httpd.filter;

import top.gunplan.netty.anno.GunNetFilterOrder;
import top.gunplan.netty.filter.GunNettyInboundFilter;
import top.gunplan.netty.httpd.protocols.GunHttp2InputProtocol;
import top.gunplan.netty.impl.checker.GunInboundChecker;

/**
 * @author dosdrtt
 */
@GunNetFilterOrder(index = 1)
public class GunStdHttp2Filter implements GunNettyInboundFilter {
    @Override
    public DealResult doInputFilter(GunInboundChecker filterDto) {
        GunHttp2InputProtocol protocol = new GunHttp2InputProtocol();
        if (protocol.unSerialize(filterDto.source())) {
            filterDto.setTransfer(protocol);
            return DealResult.NEXT;
        } else {
            filterDto.channel().closeAndRemove(true);
            return DealResult.CLOSED;
        }
    }

}
