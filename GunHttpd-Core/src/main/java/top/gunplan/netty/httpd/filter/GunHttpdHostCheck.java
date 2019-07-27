package top.gunplan.netty.httpd.filter;

import top.gunplan.netty.GunNettyFilter;
import top.gunplan.netty.GunNettySystemServices;
import top.gunplan.netty.anno.GunNetFilterOrder;
import top.gunplan.netty.httpd.property.GunHttpProperty;
import top.gunplan.netty.httpd.protocols.GunHttp2InputProtocl;
import top.gunplan.netty.impl.GunNettyInputFilterChecker;
import top.gunplan.netty.impl.GunNettyOutputFilterChecker;


/**
 * GunHttpdHostCheck
 * the filter is used to host check contorl
 *
 * @author dosdrtt
 */
@GunNetFilterOrder(index = 2)
public class GunHttpdHostCheck implements GunNettyFilter {

    @Override
    public DealResult doInputFilter(GunNettyInputFilterChecker filterDto) {
        GunHttp2InputProtocl httpmessage = (GunHttp2InputProtocl) filterDto.getTransfer();
        return httpmessage.getRequstHead().get("Host").equals((GunNettySystemServices.PROPERTY_MANAGER.acquireProperty(GunHttpProperty.class)).getHttphost()) ? DealResult.NEXT : DealResult.NOTDEALALLNEXT;
    }

    @Override
    public DealResult doOutputFilter(GunNettyOutputFilterChecker filterDto) {
        return DealResult.NEXT;
    }
}
