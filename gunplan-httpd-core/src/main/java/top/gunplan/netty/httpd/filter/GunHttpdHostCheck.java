package top.gunplan.netty.httpd.filter;


import top.gunplan.netty.GunChannelException;
import top.gunplan.netty.GunNettySystemService;
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
        return httpMessage.getRequestHead().get("Host").equals((GunNettySystemService.PROPERTY_MANAGER.acquireProperty(GunHttpProperty.class)).getHttpHost()) ?
                DealResult.NEXT : DealResult.CLOSED;
    }
}
