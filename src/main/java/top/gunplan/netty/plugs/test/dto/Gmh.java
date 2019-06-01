package top.gunplan.netty.plugs.test.dto;

import top.gunplan.netty.plugs.anno.GunHttpdRefToJsonInfo;
import top.gunplan.netty.plugs.util.GunHttpdObjectCanRefToJson;

public class Gmh implements GunHttpdObjectCanRefToJson {
    @GunHttpdRefToJsonInfo(canToJson = true)
    String Type= "123";
    @GunHttpdRefToJsonInfo(canToJson = true)
    String Val = "ddd";
}
