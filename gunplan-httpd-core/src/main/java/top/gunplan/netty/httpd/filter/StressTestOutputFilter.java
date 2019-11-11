package top.gunplan.netty.httpd.filter;


//import top.gunplan.netty.GunChannelException;
//import top.gunplan.netty.anno.GunNetFilterOrder;
//import top.gunplan.netty.filter.GunNettyOutboundFilter;
//import top.gunplan.netty.httpd.protocols.GunHttp2InputProtocol;
//import top.gunplan.netty.httpd.protocols.GunHttpStdInfo;
//import top.gunplan.netty.impl.checker.GunInboundChecker;
//import top.gunplan.netty.impl.checker.GunOutboundChecker;
//
//import java.io.IOException;
//import java.nio.ByteBuffer;
//
//
//@GunNetFilterOrder(index = 2)
//public class StressTestOutputFilter implements GunNettyOutboundFilter {
//    @Override
//    public DealResult doInputFilter(GunInboundChecker filterDto) throws GunChannelException {
//        GunHttp2InputProtocolfilterDto.transferTarget()).getMethod()== GunHttpStdInfo.GunHttpRequestType.GET){filterDto.channel().closeAndRemove()
//    }
//
//    @Override
//    public DealResult doOutputFilter(GunOutboundChecker gunOutboundChecker) throws GunChannelException {
//        try {
//            gunOutboundChecker.channel().channel().write(ByteBuffer.wrap(gunOutboundChecker.translate().source()));
//        } catch (IOException e) {
//            throw new GunChannelException(e);
//        }
//        gunOutboundChecker.channel().closeAndRemove(true);
//        return DealResult.CLOSED;
//
//    }
//}
