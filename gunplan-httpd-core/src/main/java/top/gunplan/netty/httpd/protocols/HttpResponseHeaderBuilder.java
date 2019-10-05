package top.gunplan.netty.httpd.protocols;

import java.util.*;

import static top.gunplan.netty.httpd.protocols.HttpResponseHeaderBuilder.HEADER_CONST.CONTENT_TYPE;
import static top.gunplan.netty.httpd.protocols.HttpResponseHeaderBuilder.HEADER_CONST.DATE;

/**
 * HttpResponseHeaderBuilder
 *
 * @author frank
 */
public class HttpResponseHeaderBuilder {

    private List<GunHttpStdInfo.GunCookies> cookies = new ArrayList<>(1);
    private Map<String, String> headers = new HashMap<>();

    public HttpResponseHeaderBuilder() {
        initDefault();
    }

    private void initDefault() {
        this.headers.put("Server", "windows server iis 1998");
        this.headers.put("Date", new Date().toString());
        this.headers.put("Connection", "keep-alive");
        this.headers.put(CONTENT_TYPE, GunHttpStdInfo.ContentType.APPLICATION_JSON.getVal());
        this.headers.put("Accept-Ranges", "bytes");
        this.cookies.add(new GunHttpStdInfo.GunCookies("iisSession", UUID.randomUUID().toString()));
    }

    public HttpResponseHeaderBuilder withContentType(GunHttpStdInfo.ContentType t) {
        this.headers.put(CONTENT_TYPE, t.getVal());
        return this;
    }

    public HttpResponseHeaderBuilder withServerName(String s) {
        this.headers.put(HEADER_CONST.SERVER_NAME, s);
        return this;
    }

    public HttpResponseHeaderBuilder withSetData(String s) {
        this.headers.put(DATE, s != null && s.length() != 0 ? s : new Date().toString());
        return this;
    }

    public List<GunHttpStdInfo.GunCookies> getCookies() {
        return cookies;
    }

    public Map<String, String> get() {
        return headers;
    }

    static final class HEADER_CONST {
        static final String SERVER_NAME = "Server";
        static final String DATE = "Date";
        static final String CONTENT_TYPE = "Content-Type";
    }
}
