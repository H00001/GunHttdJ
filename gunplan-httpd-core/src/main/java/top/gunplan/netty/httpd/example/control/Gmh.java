package top.gunplan.netty.httpd.example.control;

import top.gunplan.netty.httpd.anno.GunHttpRefToJsonInfo;
import top.gunplan.netty.httpd.util.GunHttpdObjectCanRefToJson;

public class Gmh implements GunHttpdObjectCanRefToJson {
    @GunHttpRefToJsonInfo(canToJson = false)
    String type = "123";
    @GunHttpRefToJsonInfo(canToJson = true, jsonKey = "pcb")
    String val = "ddd";
}
