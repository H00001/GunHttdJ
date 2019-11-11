package top.gunplan.netty.httpd.example.unittest;


import top.gunplan.netty.GunBootServer;
import top.gunplan.netty.GunNettySystemService;
import top.gunplan.netty.GunServerStateManager;
import top.gunplan.netty.httpd.filter.GunStdHttp2Filter;
import top.gunplan.netty.httpd.handle.GunStdHttpHandle;
import top.gunplan.netty.httpd.property.GunHttpProperty;
import top.gunplan.netty.impl.GunBootServerFactory;
import top.gunplan.netty.impl.GunNettyStdFirstFilter;
import top.gunplan.netty.impl.property.GunGetPropertyFromNet;


/**
 * @author dosdrtt
 */
public class GunTestJunit {


    public static void main(String[] args) {

        GunNettySystemService.PROPERTY_MANAGER.setStrategy(new GunGetPropertyFromNet("https://p.gunplan.top/static/http_conf.html"));
        GunNettySystemService.PROPERTY_MANAGER.registerProperty(new GunHttpProperty());
        GunBootServer server = GunBootServerFactory.newInstance();

        server.registerObserve(new top.gunplan.netty.httpd.example.unittest.GunHttpdObserve());
        server.useStealMode(false).
                setExecutors(1, 10)
                .onHasChannel(ch -> ch
                        .addDataFilter(new GunNettyStdFirstFilter())
                        .addDataFilter(new GunStdHttp2Filter())
                        //  .addDataFilter(new StressTestOutputFilter())
                        .setHandle(new GunStdHttpHandle())
                );
        try {
            server.setSyncType(true);
            int val = server.sync();
            if ((val | GunServerStateManager.GunNettyWorkState.BOOT_ERROR_2.state) != 0) {
                server.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("waiting");
    }
}