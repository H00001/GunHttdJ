package top.gunplan.netty.httpd.protocols;


import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author dosdrtt
 */
public abstract class BaseGunHttp2Response extends AbstractGunHttp2Response {
    private GunHttpStdInfo.HttpProtocolType protocolType = GunHttpStdInfo.HttpProtocolType.HTTP1_1;
    private GunHttpStdInfo.statusCode code = GunHttpStdInfo.statusCode.OK;
    private HttpResponseHeaderBuilder builder = new HttpResponseHeaderBuilder();

    protected BaseGunHttp2Response() {
    }


    public GunHttpStdInfo.HttpProtocolType getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(GunHttpStdInfo.HttpProtocolType protocolType) {
        this.protocolType = protocolType;
    }

    public GunHttpStdInfo.statusCode getCode() {
        return code;
    }

    public void setCode(GunHttpStdInfo.statusCode code) {
        this.code = code;
    }


    @Override
    public String getResponseBody() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String, String> httpHead = this.builder.get();
        StringBuilder http2resp = new StringBuilder();
        http2resp.append(protocolType.getVal()).append(" ").append(code.getVal()).append(" ");
        http2resp.append(code).append("\r\n");
        for (String key : httpHead.keySet()) {
            http2resp.append(key).append(":").append(httpHead.get(key)).append("\r\n");
        }
        for (GunHttpStdInfo.GunCookies cookie : this.builder.getCookies()) {
            http2resp.append("Set-Cookie").append(":").append(cookie.toString()).append("\r\n");
        }
        http2resp.append("Content-Length:").append(this.toResponse().length()).append("\r\n\r\n");
        http2resp.append(this.toResponse());

        return http2resp.toString();
    }

    public HttpResponseHeaderBuilder getHeaderBuilder() {
        return builder;
    }

    public abstract String toResponse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
