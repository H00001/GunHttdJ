package top.gunplan.netty.httpd.filter;

import top.gunplan.netty.GunChannelException;
import top.gunplan.netty.GunNettyFilter;
import top.gunplan.netty.anno.GunNetFilterOrder;
import top.gunplan.netty.httpd.protocols.GunHttp2InputProtocl;
import top.gunplan.netty.impl.GunNettyInputFilterChecker;
import top.gunplan.netty.impl.GunNettyOutputFilterChecker;

/**
 * @author dosdrtt
 */
@GunNetFilterOrder(index = 1)
public class GunStdHttp2Filter implements GunNettyFilter {
    @Override
    public DealResult doInputFilter(GunNettyInputFilterChecker filterDto) {
        GunHttp2InputProtocl protocl = new GunHttp2InputProtocl();
        if (protocl.unSerialize(filterDto.source())) {
            filterDto.setTransfer(protocl);
            return DealResult.NEXT;
        } else {
            return DealResult.NOTDEALALLNEXT;
        }
    }


    @Override
    public DealResult doOutputFilter(GunNettyOutputFilterChecker gunNettyOutputFilterChecker) throws GunChannelException {
        return DealResult.NEXT;
    }
}
