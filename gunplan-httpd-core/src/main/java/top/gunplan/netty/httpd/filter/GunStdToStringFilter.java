package top.gunplan.netty.httpd.filter;

import top.gunplan.netty.anno.GunNetFilterOrder;
import top.gunplan.netty.filter.GunNettyInboundFilter;
import top.gunplan.netty.httpd.protocols.GunStdString;
import top.gunplan.netty.impl.checker.GunInboundChecker;


/**
 * @author dosdrtt
 */
@GunNetFilterOrder
public class GunStdToStringFilter implements GunNettyInboundFilter {
    @Override
    public DealResult doInputFilter(GunInboundChecker filterDto) {
        GunStdString s = new GunStdString();
        s.unSerialize(filterDto.source());
        filterDto.setTransfer(s);
        return DealResult.NEXT;
    }
}
