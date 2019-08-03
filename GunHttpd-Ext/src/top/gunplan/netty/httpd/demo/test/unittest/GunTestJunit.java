package top.gunplan.netty.httpd.demo.test.unittest;


import top.gunplan.netty.GunBootServer;
import top.gunplan.netty.GunNettySystemServices;
import top.gunplan.netty.GunNettyTimer;
import top.gunplan.netty.httpd.GunHttpdObserve;
import top.gunplan.netty.httpd.filter.GunStdHttp2Filter;
import top.gunplan.netty.httpd.handle.GunStdHttpHandle;
import top.gunplan.netty.httpd.property.GunHttpProperty;
import top.gunplan.netty.impl.GunBootServerFactory;
import top.gunplan.netty.impl.GunNettyStdFirstFilter;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author dosdrtt
 */
public class GunTestJunit {


    public static void main(String[] args) {

        GunNettySystemServices.PROPERTY_MANAGER.registerProperty(new GunHttpProperty());
        GunBootServer server = GunBootServerFactory.getInstance();
        ExecutorService es0 = new ThreadPoolExecutor(100, 1000,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());

        ExecutorService es1 = new ThreadPoolExecutor(100, 1000,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        server.setExecutors(es0, es1).registerObserve(new GunHttpdObserve()).pipeline().
                addFilter(new GunNettyStdFirstFilter()).
                addFilter(new GunStdHttp2Filter()).
                addTimer(new GunNettyTimer() {
                    @Override
                    public int interval() {
                        return 10;
                    }

                    @Override
                    public int runingTimes() {
                        return 0;
                    }

                    @Override
                    public int doWork(Set<SelectionKey> set) {
                        //.debug(set.size() + " connection");
                        set.parallelStream().forEach(selectionKey -> {
                            try {
                                selectionKey.channel().close();
                            } catch (IOException e) {
                                //       AbstractGunBaseLogUtil.error(e);
                            }
                        });
                        return 0;
                    }
                }).
                setHandle(new GunStdHttpHandle());
        try {
            int val = server.sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}