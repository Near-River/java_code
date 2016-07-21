package pro2.http.myHttp2;

import java.util.Map;

/**
 * Created by Near on 2016/1/26.
 */
public class WebApp {
    private static ServletContext servletContext;

    static {
        servletContext = new ServletContext();

        Map<String, String> mapping = servletContext.getMapping();
        mapping.put("/login", "login");
        mapping.put("/dologin", "login");
        mapping.put("/register", "register");

        Map<String, String> servlet = servletContext.getServlet();
        servlet.put("login", "pro2.http.myHttp2.LoginServlet");
        servlet.put("register", "pro2.http.myHttp2.RegisterServlet");
    }

    public static Servlet getServlet(String uri) {
        if ((null == uri) || (uri = uri.trim()).equals("")) {
            return null;
        }

        String servletName = servletContext.getServlet().get(servletContext.getMapping().get(uri));
        try {
            return (Servlet) Class.forName(servletName).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
