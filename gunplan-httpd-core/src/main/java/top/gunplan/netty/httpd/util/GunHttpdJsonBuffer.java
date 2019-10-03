package top.gunplan.netty.httpd.util;

import java.lang.reflect.InvocationTargetException;

/**
 * @author frank
 * #date 2019/05/22
 */

public class GunHttpdJsonBuffer extends GunHttpdJsonBuilder {

    @Override
    public synchronized void put(String k, Object v) {
        super.put(k, v);
    }

    @Override
    public synchronized String formatToString() throws InvocationTargetException, IllegalAccessException {
        return super.formatToString();
    }

    @Override
    public synchronized String refObject(GunHttpdObjectCanRefToJson object) throws IllegalAccessException, InvocationTargetException {
        return super.refObject(object);
    }
}
