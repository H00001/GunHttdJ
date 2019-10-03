package top.gunplan.netty.httpd.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author frank albert
 * #date 2019/05/28
 */

public interface GunHttpdJsonUtilInterface {
    /**
     * type string string
     *
     * @param k string key
     * @param v inline followed:
     *          string value
     *          list value
     *          GunHttpdJsonUtilInterface value
     *          other value you can register at addRules method
     */
    void put(String k, Object v);


    /**
     * format string
     *
     * @return result
     * @throws InvocationTargetException can not execute the method
     * @throws IllegalAccessException    error parameters exception
     */
    String formatToString() throws InvocationTargetException, IllegalAccessException;

    /**
     * add rules (costumer rule to execute)
     *
     * @param clazz          mapping class
     * @param methodToInvoke what method would we invoke
     */
    void addRules(Class<?> clazz, Method methodToInvoke);


    /**
     * refObject
     * @param object change a object to json string
     * @return json string
     * @throws IllegalAccessException ref invoke error
     * @throws InvocationTargetException ref invoke error
     */
    String refObject(GunHttpdObjectCanRefToJson object) throws IllegalAccessException, InvocationTargetException;
}
