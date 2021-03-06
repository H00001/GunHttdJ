package top.gunplan.netty.httpd.util;


/**
 * @author frank albert
 * @version 0.0.0.1
 * #date 2019/05/28
 */
public final class GunHttpdJsonUtilFactory {
    public static GunHttpdJsonUtilInterface newBzJsonUtil() throws NoSuchMethodException {
        GunHttpdJsonUtilInterface util = new GunHttpdJsonBuilder();
        util.addRules(Integer.class, Integer.class.getMethod("intValue"));
        return util;
    }
}
