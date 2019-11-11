package top.gunplan.netty.httpd.example.unittest;

import top.gunplan.netty.httpd.handle.GunStdHttpHandle;
import top.gunplan.netty.impl.property.GunNettyCoreProperty;
import top.gunplan.netty.observe.GunNettyDefaultObserve;


/**
 * @author frank
 */
public class GunHttpdObserve extends GunNettyDefaultObserve {
    @Override
    public boolean onBooting(GunNettyCoreProperty property) {
        return new GunStdHttpHandle().ginit() != -1;
    }

    @Override
    public void onBooted(GunNettyCoreProperty property) {

    }
}
