package top.gunplan.netty.httpd.protocols;


import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author dosdrtt
 */
public abstract class BaseGunHttp2Response extends AbstractGunHttp2Response {
    private GunHttpStdInfo.HttpProtoclType protoclType = GunHttpStdInfo.HttpProtoclType.HTTP1_1;
    private GunHttpStdInfo.statusCode code = GunHttpStdInfo.statusCode.OK;
    private GunHttpStdInfo.ContentType contentType = GunHttpStdInfo.ContentType.TEXT_JSON;

    protected BaseGunHttp2Response() {
        this.mmap.put("Server", "windows server iis 1998");
        this.mmap.put("Date", new Date().toString());
        this.mmap.put("Connection", "closed");
        this.mmap.put("Accept-Ranges", "bytes");
        this.cookies.add(new GunHttpStdInfo.GunCookies("iisSession", UUID.randomUUID().toString()));
    }

    private Map<String, String> mmap = new HashMap<>(4);
    private List<GunHttpStdInfo.GunCookies> cookies = new ArrayList<>(1);

    public Map<String, String> getMmap() {
        return mmap;
    }

    public void setMmap(Map<String, String> mmap) {
        this.mmap = mmap;
    }

    public GunHttpStdInfo.HttpProtoclType getProtocolType() {
        return protoclType;
    }

    public void setProtocolType(GunHttpStdInfo.HttpProtoclType protoclType) {
        this.protoclType = protoclType;
    }

    public GunHttpStdInfo.statusCode getCode() {
        return code;
    }

    public void setCode(GunHttpStdInfo.statusCode code) {
        this.code = code;
    }

    public GunHttpStdInfo.ContentType getContentType() {
        return contentType;
    }

    public void setContentType(GunHttpStdInfo.ContentType contentType) {
        this.contentType = contentType;
    }

    public List<GunHttpStdInfo.GunCookies> getCookies() {
        return cookies;
    }

    public void addCookie(GunHttpStdInfo.GunCookies cookies) {
        this.cookies.add(cookies);
    }

    @Override
    public String getResponseBody() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String, String> httpHead = this.mmap;
        StringBuilder http2resp = new StringBuilder();
        http2resp.append(protoclType.getVal()).append(" ").append(code.getVal()).append(" ");
        http2resp.append(code).append("\r\n");
        http2resp.append("Content-Type:").append(contentType.getVal()).append("\r\n");
        for (String key : httpHead.keySet()) {
            http2resp.append(key).append(":").append(httpHead.get(key)).append("\r\n");
        }
        for (GunHttpStdInfo.GunCookies cookie : cookies) {
            http2resp.append("Set-Cookie").append(":").append(cookie.toString()).append("\r\n");
        }

        http2resp.append("Content-Length:").append(this.toResponse().length()).append("\r\n\r\n");
        http2resp.append(this.toResponse());

        return http2resp.toString();
    }

    public abstract String toResponse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
