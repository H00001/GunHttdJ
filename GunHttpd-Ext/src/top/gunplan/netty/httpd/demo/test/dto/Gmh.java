package top.gunplan.netty.httpd.demo.test.dto;

import top.gunplan.netty.httpd.anno.GunHttpdRefToJsonInfo;
import top.gunplan.netty.httpd.util.GunHttpdObjectCanRefToJson;

public class Gmh implements GunHttpdObjectCanRefToJson {
    @GunHttpdRefToJsonInfo(canToJson = false)
    String Type= "123";
    @GunHttpdRefToJsonInfo(canToJson = true, jsonKey = "pcb")
    String Val = "ddd";
}
