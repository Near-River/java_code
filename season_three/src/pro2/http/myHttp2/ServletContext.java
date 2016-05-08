package pro2.http.myHttp2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Near on 2016/1/26.
 */
public class ServletContext {

    // 为每个servlet取别名
    // login  -->  LoginServlet
    private Map<String, String> servlet;

    private Map<String, String> mapping;

    ServletContext() {
        servlet = new HashMap<String, String>();
        mapping = new HashMap<String, String>();
    }

    public Map<String, String> getServlet() {
        return servlet;
    }

    public void setServlet(Map<String, String> servlet) {
        this.servlet = servlet;
    }

    public Map<String, String> getMapping() {
        return mapping;
    }

    public void setMapping(Map<String, String> mapping) {
        this.mapping = mapping;
    }

}
