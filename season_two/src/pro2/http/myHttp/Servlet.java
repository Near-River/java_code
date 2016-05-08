package pro2.http.myHttp;

/**
 * Created by Near on 2016/1/26.
 */
public class Servlet {

    public void service(Request request, Response response) {
        response.println("<html><head><title>HTTP响应示例</title></head>");
        response.print("<body><h1>Hello World!</h1>");
        response.print(request.getParameter("name"));
        response.println("</body></html>");

        System.out.println(request.getParameter("name"));
    }

}
