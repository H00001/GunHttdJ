package top.gunplan.netty.httpd.filter;

import top.gunplan.netty.httpd.protocols.GunStdString;
import top.gunplan.netty.GunNettyFilter;
import top.gunplan.netty.anno.GunNetFilterOrder;
import top.gunplan.netty.impl.GunInputFilterChecker;
import top.gunplan.netty.impl.GunOutputFilterChecker;


/**
 * @author dosdrtt
 */
@GunNetFilterOrder
public class GunStdToStringFilter implements GunNettyFilter {
    @Override
    public DealResult doInputFilter(GunInputFilterChecker filterDto) {
        GunStdString s = new GunStdString();
        s.unSerialize(filterDto.getSrc());
        filterDto.setObject(s);
        return DealResult.NEXT;
    }

    @Override
    public DealResult doOutputFilter(GunOutputFilterChecker filterDto) {
        return DealResult.NEXT;
    }
}
