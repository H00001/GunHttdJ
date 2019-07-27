package top.gunplan.netty.httpd.filter;

import top.gunplan.netty.GunNettyFilter;
import top.gunplan.netty.anno.GunNetFilterOrder;
import top.gunplan.netty.httpd.protocols.GunStdString;
import top.gunplan.netty.impl.GunNettyInputFilterChecker;
import top.gunplan.netty.impl.GunNettyOutputFilterChecker;


/**
 * @author dosdrtt
 */
@GunNetFilterOrder
public class GunStdToStringFilter implements GunNettyFilter {
    @Override
    public DealResult doInputFilter(GunNettyInputFilterChecker filterDto) {
        GunStdString s = new GunStdString();
        s.unSerialize(filterDto.source());
        filterDto.setTransfer(s);
        return DealResult.NEXT;
    }

    @Override
    public DealResult doOutputFilter(GunNettyOutputFilterChecker filterDto) {
        return DealResult.NEXT;
    }
}
