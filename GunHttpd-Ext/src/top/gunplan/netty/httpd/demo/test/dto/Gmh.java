package top.gunplan.netty.httpd.demo.test.dto;

import top.gunplan.netty.httpd.anno.GunHttpdRefToJsonInfo;
import top.gunplan.netty.httpd.util.GunHttpdObjectCanRefToJson;

public class Gmh implements GunHttpdObjectCanRefToJson {
    @GunHttpdRefToJsonInfo(canToJson = true)
    String Type= "123";
    @GunHttpdRefToJsonInfo(canToJson = true)
    String Val = "ddd";
}
