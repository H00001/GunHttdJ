package top.gunplan.netty.plugs.property;


import top.gunplan.netty.anno.GunPropertyMap;
import top.gunplan.netty.impl.propertys.GunProperty;

/**
 * @author dosdrtt
 */
@GunPropertyMap(name = "http")
public class GunHttpProperty implements GunProperty {
    private String scannPacket = null;

    private String httphost = null;

    public String getScannPacket() {
        return scannPacket;
    }

    public String getHttphost() {
        return httphost;
    }

    public GunHttpProperty() {

    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}
