package pro2.http.myHttp2;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Near on 2016/1/26.
 */
public class Request {
    public static final String CRLF = "\r\n";

    private String reqMethod;
    private String uri;
    private Map<String, List<String>> reqParams;
    private String requestInfo;

    private InputStream inputStream;

    public Request() {
        reqMethod = "";
        uri = "";
        reqParams = new HashMap<String, List<String>>();
        requestInfo = "";
    }

    public Request(InputStream inputStream) {
        this();
        this.inputStream = inputStream;
        parseReqInfo();
    }

    /**
     * 获取请求正文
     */
    public void setRequestInfo() {
        byte[] data = new byte[20480];
        int len = 0;
        try {
            len = inputStream.read(data);
            //接收客户端的请求信息
            requestInfo = new String(data, 0, len).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从请求正文中解析出请求方式、URI等信息
     */
    public void parseReqInfo() {
        setRequestInfo();
        /**
         * =====================================
         * 从信息的首行分解出 :请求方式    请求路径    请求参数(get方式下存在)
         *   如:GET /index.html?name=123&pwd=5456 HTTP/1.1
         *
         *   如果为post方式，请求参数在最后正文中
         * =====================================
         */
        if (!(requestInfo == null || "".equals(requestInfo.trim()))) {
            String firstLine = requestInfo.substring(0, requestInfo.indexOf(CRLF));
            int index = requestInfo.indexOf("/");
            reqMethod = firstLine.substring(0, index).trim();
            String uriStr = requestInfo.substring(index, requestInfo.indexOf("HTTP/")).trim();
            String params = null;
            if ("get".equals(reqMethod.toLowerCase())) {
                if (uriStr.contains("?")) {
                    String[] temp = uriStr.split("\\?");
                    uri = temp[0];
                    //接收请求参数
                    params = temp[1];
                } else {
                    uri = uriStr;
                }
            } else if ("post".equals(reqMethod.toLowerCase())) {
                uri = uriStr;
                params = requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
            }
            if (params != null) {
                parseParams(params);
            }
        }
    }

    private void parseParams(String params) {
        String[] entry = params.split("&");
        for (String keyValue : entry) {
            String[] temp = keyValue.split("=");

            String key = temp[0].trim();
            String value = null;
            if (temp.length != 1) {
                // value = temp[1].trim();
                try {
                    value = URLDecoder.decode(temp[1].trim(), "utf-8");
                    // System.out.println(value);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    value = null;
                }
            }

            if (reqParams.containsKey(key)) {
                reqParams.get(key).add(value);
            } else {
                List<String> list = new ArrayList<String>();
                list.add(value);
                reqParams.put(key, list);
            }
        }

    }

    /**
     * 根据请求的 name 获取对应的多个值
     */
    public String[] getParameterValues(String name) {
        List<String> values = null;
        if ((values = reqParams.get(name)) == null) {
            return null;
        } else {
            return values.toArray(new String[0]);
        }
    }

    /**
     * 根据请求的name 获取对应的单个值
     */
    public String getParameter(String name) {
        String[] values = getParameterValues(name);
        if (null == values) {
            return null;
        }
        return values[0];
    }

    public String getUri() {
        return uri;
    }
}
