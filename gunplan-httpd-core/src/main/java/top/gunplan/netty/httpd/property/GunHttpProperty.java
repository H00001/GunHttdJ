package top.gunplan.netty.httpd.property;


import top.gunplan.netty.GunProperty;
import top.gunplan.netty.anno.GunPropertyMap;



/**
 * @author dosdrtt
 */
@GunPropertyMap(name = "http")
public class GunHttpProperty implements GunProperty {
    private String scannPacket = null;

    private String httpHost = null;

    public String getScanPacket() {
        return scannPacket;
    }

    public String getHttpHost() {
        return httpHost;
    }

    public GunHttpProperty() {

    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}
