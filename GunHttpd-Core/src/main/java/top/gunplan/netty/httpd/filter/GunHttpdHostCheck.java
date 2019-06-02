package top.gunplan.netty.httpd.filter;

import top.gunplan.netty.httpd.protocols.GunHttp2InputProtocl;
import top.gunplan.netty.impl.GunNettyPropertyManagerImpl;
import top.gunplan.netty.httpd.property.GunHttpProperty;
import top.gunplan.netty.GunNettyFilter;
import top.gunplan.netty.anno.GunNetFilterOrder;
import top.gunplan.netty.impl.GunInputFilterChecker;
import top.gunplan.netty.impl.GunOutputFilterChecker;


/**
 * @author dosdrtt
 */
@GunNetFilterOrder(index = 2)
public class GunHttpdHostCheck implements GunNettyFilter {

    @Override
    public DealResult doInputFilter(GunInputFilterChecker filterDto) {
        GunHttp2InputProtocl httpmessage = (GunHttp2InputProtocl) filterDto.getObject();
        return httpmessage.getRequstHead().get("Host").equals((GunNettyPropertyManagerImpl.getProperty(GunHttpProperty.class)).getHttphost()) ? DealResult.NEXT : DealResult.NOTDEALALLNEXT;
    }

    @Override
    public DealResult doOutputFilter(GunOutputFilterChecker filterDto) {
        return DealResult.NEXT;
    }
}
