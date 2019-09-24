package top.gunplan.netty.httpd;

import top.gunplan.netty.GunException;
import top.gunplan.netty.GunExceptionType;

/**
 * @author dosdrtt
 * @date 2019/05/09
 * @see top.gunplan.netty.GunException
 */
public class GunHttpdException extends GunException {
    private static final long serialVersionUID = -4519667691718155560L;

    public GunHttpdException(String why) {
        super(GunExceptionType.EXC1, why);
    }

    public GunHttpdException(Exception exp) {
        super(exp);
    }
}
