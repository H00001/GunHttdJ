package top.gunplan.netty.httpd.filter;


import top.gunplan.netty.GunChannelException;
import top.gunplan.netty.GunNettySystemServices;
import top.gunplan.netty.anno.GunNetFilterOrder;
import top.gunplan.netty.filter.GunNettyInboundFilter;
import top.gunplan.netty.httpd.property.GunHttpProperty;
import top.gunplan.netty.httpd.protocols.GunHttp2InputProtocol;
import top.gunplan.netty.impl.checker.GunInboundChecker;


/**
 * GunHttpdHostCheck
 * the filter is used to host check contorl
 *
 * @author dosdrtt
 */
@GunNetFilterOrder(index = 2)
public class GunHttpdHostCheck implements GunNettyInboundFilter {

    @Override
    public DealResult doInputFilter(GunInboundChecker inboundChecker) throws GunChannelException {
        GunHttp2InputProtocol httpMessage = (GunHttp2InputProtocol) inboundChecker.transferTarget();
        return httpMessage.getRequstHead().get("Host").equals((GunNettySystemServices.PROPERTY_MANAGER.acquireProperty(GunHttpProperty.class)).getHttphost()) ?
                DealResult.NEXT : DealResult.CLOSED;
    }
}
