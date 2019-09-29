package top.gunplan.netty.httpd.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

enum HTTP_TYPE {
    /**
     *
     */
    GET, POST, PUT, HEAD, DELETE
}

@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GunHttpmapping {
    String mappingRule();
    HTTP_TYPE type() default HTTP_TYPE.GET;
}