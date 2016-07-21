package pro2.http.myHttp2;

import java.io.IOException;

/**
 * Created by Near on 2016/1/26.
 */
public class RegisterServlet extends Servlet {

    @Override
    public void doGet(Request request, Response response) throws IOException {
    }

    @Override
    public void doPost(Request request, Response response) throws IOException {
        response.println("<html><head><title>注册</title></head>");
        response.print("<body><h1>用户名：");
        response.print(request.getParameter("name") + "</h1>");
        response.println("</body></html>");

        System.out.println(request.getParameter("name"));
    }
}