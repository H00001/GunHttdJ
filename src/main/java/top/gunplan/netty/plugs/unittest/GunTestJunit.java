package top.gunplan.netty.plugs.unittest;


import top.gunplan.netty.plugs.filter.GunStdHttp2Filter;
import top.gunplan.netty.plugs.handle.GunStdHttpHandle;
import top.gunplan.netty.plugs.property.GunHttpProperty;
import top.gunplan.netty.GunBootServer;

import top.gunplan.netty.common.GunNettyPropertyManagerImpl;
import top.gunplan.netty.filter.GunNettyStdFirstFilter;
import top.gunplan.netty.impl.GunBootServerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author dosdrtt
 */
public class GunTestJunit {


    public static void main(String[] args) {

        GunNettyPropertyManagerImpl.registerProperty(new GunHttpProperty());
        GunBootServer server = GunBootServerFactory.getInstance();
        ExecutorService es0 = new ThreadPoolExecutor(100, 1000,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());

        ExecutorService es1 = new ThreadPoolExecutor(100, 1000,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        server.setExecuters(es0, es1).getPipeline().addFilter(new GunNettyStdFirstFilter()).
                addFilter(new GunStdHttp2Filter()).
                //  addFilter(new GunHttpdHostCheck()).
                        setHandle(new GunStdHttpHandle("top.gunplan.netty.plugs.test"));
        try {
            int val = server.sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}