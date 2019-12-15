package top.gunplan.netty.httpd.protocols;

import top.gunplan.netty.protocol.GunNetInbound;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * GunHttp2InputProtocol
 *
 * @author dosdrtt
 */
final public class GunHttp2InputProtocol implements GunNetInbound {
    private String requestBody;
    private Map<String, String> requestBodyMap = new HashMap<>();
    private HashMap<String, String> requestHead = new HashMap<>();
    private HashMap<String, String> http2Parameters = new HashMap<>(2);


    public GunHttp2InputProtocol() {

    }

    private String getParameters(String name) {
        return http2Parameters.get(name);
    }


    public String getRequestBody() {
        return requestBody;
    }

    private String requestUrl;

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    private GunHttpStdInfo.GunHttpRequestType method;

    public HashMap<String, String> getRequestHead() {
        return requestHead;
    }

    public void setRequestHead(HashMap<String, String> requestHead) {
        this.requestHead = requestHead;
    }

    public Map<String, String> bodyMapToMap() {
        String[] params = requestBody.split("&");
        Arrays.asList(params).forEach(c -> {
            requestBodyMap.put(c.split("=")[0], c.split("=")[1]);
        });
        return requestBodyMap;
    }


    public GunHttpStdInfo.GunHttpRequestType getMethod() {
        return method;
    }

    public void setMethod(GunHttpStdInfo.GunHttpRequestType method) {
        this.method = method;
    }


    public String getRequestUrl() {
        return requestUrl;
    }

    @Override
    public boolean unSerialize(byte[] in) {
        try {
            String httpContent = new String(in);
            int position = httpContent.indexOf("\r\n");
            this.analyzingHttpHeadFirst(httpContent.substring(0, position));
            int spiltPoint = httpContent.indexOf("\r\n\r\n");
            this.analyzingHttpHead(httpContent.substring(position + 2, spiltPoint).split("\r\n"));
            if (this.method == GunHttpStdInfo.GunHttpRequestType.POST) {
                functionToDealPostMethod(httpContent.substring(spiltPoint + 4));
            }

            return true;
        } catch (
                Exception e) {
            return false;
        }

    }


    private void functionToDealPostMethod(final String body) {
        this.requestBody = body;
    }

    private void analyzingHttpHeadFirst(final String httpHeadFirst) {
        final String[] block = httpHeadFirst.split(" ");
        this.method = GunHttpStdInfo.GunHttpRequestType.getType(block[0]);
        String reqUrl = block[1];
        var3310:
        if (reqUrl.contains("?")) {
            this.requestUrl = reqUrl.split("\\?")[0];
            String[] parameters;
            if (reqUrl.split("\\?")[1].contains("&")) {
                parameters = reqUrl.split("&");
            } else {
                parameters = new String[1];
                parameters[0] = reqUrl.split("\\?")[1];
            }
            for (String parameter : parameters) {
                http2Parameters.put(parameter.split("=")[0], parameter.split("=")[1]);
            }

        } else {
            this.requestUrl = reqUrl;
        }
    }


    private void analyzingHttpHead(String[] httphead) {
        for (String eachHead : httphead) {
            requestHead.put(eachHead.split(":")[0].trim(), eachHead.split(":")[1].trim());
        }
    }
}
