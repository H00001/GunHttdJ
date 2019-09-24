package top.gunplan.netty.httpd.demo.test.control;

import top.gunplan.netty.httpd.anno.GunHttpdRefToJsonInfo;
import top.gunplan.netty.httpd.util.GunHttpdObjectCanRefToJson;

public class Gmh implements GunHttpdObjectCanRefToJson {
    @GunHttpdRefToJsonInfo(canToJson = false)
    String type = "123";
    @GunHttpdRefToJsonInfo(canToJson = true, jsonKey = "pcb")
    String val = "ddd";
}
