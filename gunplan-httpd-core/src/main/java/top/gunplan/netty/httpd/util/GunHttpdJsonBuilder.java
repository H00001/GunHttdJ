package top.gunplan.netty.httpd.util;

import top.gunplan.netty.httpd.anno.GunHttpdRefToJsonInfo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * * This util is used to format json string;
 *
 * @author frank albert
 * @ThreadNotSafe
 * @date 2019/05/22
 */
public class GunHttpdJsonBuilder implements GunHttpdJsonUtilInterface {
    private Map<String, Object> mmap = new HashMap<>();
    private Map<Class<?>, Method> rules = new HashMap<>();

    @Override
    public void put(String k, Object v) {
        mmap.put(k, v == null ? "nullptr" : v);
    }


    @Override
    public String formatToString() throws InvocationTargetException, IllegalAccessException {
        StringBuilder builder = new StringBuilder("{");
        for (String k : mmap.keySet()) {
            Object o = mmap.get(k);
            builder.append("\"").append(k).append("\":");
            if (o instanceof String) {
                builder.append("\"").append(o.toString()).append("\",");
            } else {
                readInvoke(builder, o);
            }
        }

        String seResult = builder.substring(0, builder.length() - 1);
        return seResult + "}";
    }

    @Override
    public void addRules(Class<?> clazz, Method methodToInvoke) {
        if (clazz != null && methodToInvoke != null) {
            this.rules.put(clazz, methodToInvoke);
        }
    }

    @Override
    public String refObject(GunHttpdObjectCanRefToJson object) throws IllegalAccessException, InvocationTargetException {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            GunHttpdRefToJsonInfo info = field.getAnnotation(GunHttpdRefToJsonInfo.class);
            if (info != null && info.canToJson()) {
                this.put(info.jsonKey().length() != 0 ? info.jsonKey() : field.getName(), field.get(object));
            }
        }
        return formatToString();
    }

    private String serizList(List list) throws InvocationTargetException, IllegalAccessException {
        StringBuilder builder = new StringBuilder("[");
        for (Object o : list) {
            if (o instanceof String) {
                builder.append("\"").append(o.toString()).append("\"").append(",");
            } else {
                readInvoke(builder, o);
            }
        }
        String sResult = builder.length() != 1 ? builder.substring(0, builder.length() - 1) : builder.toString();
        sResult += ("]");
        return sResult;
    }

    private void readInvoke(StringBuilder builder, Object o) throws InvocationTargetException, IllegalAccessException {
        if (o instanceof List) {
            builder.append(serizList((List) o)).append(",");
        } else if (o instanceof GunHttpdJsonUtilInterface) {
            builder.append(((GunHttpdJsonUtilInterface) o).formatToString());
        } else {
            Method md;
            if ((md = rules.get(o.getClass())) != null) {
                builder.append(md.invoke(o)).append(",");
            }
        }
    }
}
