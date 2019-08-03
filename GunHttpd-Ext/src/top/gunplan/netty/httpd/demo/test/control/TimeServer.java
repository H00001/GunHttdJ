package top.gunplan.netty.httpd.demo.test.control;


import top.gunplan.netty.httpd.anno.GunHttpmapping;
import top.gunplan.netty.httpd.handle.GunHttpMappingHandle;
import top.gunplan.netty.protocol.GunNetInbound;
import top.gunplan.netty.protocol.GunNetOutbound;

@GunHttpmapping(mappingRule = "/time")
public class TimeServer implements GunHttpMappingHandle<GunNetOutbound> {
    public TimeServer() {
    }


    @Override
    public GunNetOutbound doOutput(GunNetInbound protocl) {
//        basegunhttp2response response = new basegunhttp2response() {
//            @override
//            public string toresponse() {
//                gunmappingjsonresp resp = new gunmappingjsonresp();
//                resp.put("time", string.valueof(system.currenttimemillis()));
//                return resp.totransfer();
//            }
//        };
//        response.setiswrite(true);
        //     return response;

        return null;
    }
}
