package top.gunplan.netty.httpd.demo.test.unittest;


import top.gunplan.netty.GunBootServer;
import top.gunplan.netty.GunNettySystemServices;
import top.gunplan.netty.httpd.GunHttpdObserve;
import top.gunplan.netty.httpd.filter.GunStdHttp2Filter;
import top.gunplan.netty.httpd.handle.GunStdHttpHandle;
import top.gunplan.netty.httpd.property.GunHttpProperty;
import top.gunplan.netty.impl.GunBootServerFactory;
import top.gunplan.netty.impl.GunNettyStdFirstFilter;
import top.gunplan.netty.impl.property.GunGetPropertyFromBaseFile;


/**
 * @author dosdrtt
 */
public class GunTestJunit {


    public static void main(String[] args) {

        GunNettySystemServices.PROPERTY_MANAGER.setStrategy(new GunGetPropertyFromBaseFile());
        GunNettySystemServices.PROPERTY_MANAGER.registerProperty(new GunHttpProperty());
        GunBootServer server = GunBootServerFactory.newInstance();
        server.setExecutors(100, 100)
                .registerObserve(new GunHttpdObserve())
                .onHasChannel(ch -> ch
                        .addDataFilter(new GunNettyStdFirstFilter())
                        .addDataFilter(new GunStdHttp2Filter())
                        .setHandle(new GunStdHttpHandle())
                );
        try {
            server.setSyncType(false);
            int val = server.sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("waiting");
    }


}