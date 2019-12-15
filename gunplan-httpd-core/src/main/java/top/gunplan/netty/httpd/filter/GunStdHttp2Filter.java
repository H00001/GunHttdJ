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
        if (filterDto.tranToObject(GunHttp2InputProtocol.class)) {
            return DealResult.NEXT;
        } else {
            filterDto.channel().closeAndRemove(true);
            return DealResult.CLOSED;
        }
    }

}


//this is a web server
//it lesten on localhost:8822
//can you understand ?
//now it is working
//how ever if i use ab plug to test
//i do not know the reason
//please help me to solve the problem

