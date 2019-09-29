package top.gunplan.netty.httpd.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * GunHttpdRefToJsonInfo
 */
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GunHttpdRefToJsonInfo {
    boolean canToJson();

    String jsonKey() default "";
}
