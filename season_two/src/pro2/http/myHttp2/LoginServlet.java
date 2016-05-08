package pro2.http.myHttp2;

import java.io.IOException;

/**
 * Created by Near on 2016/1/26.
 */
public class LoginServlet extends Servlet {

    @Override
    public void doGet(Request request, Response response) throws IOException {
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        if (login(name, pwd)) {
            response.println("登录成功");
        } else {
            response.println("登录失败");
        }
    }

    public boolean login(String name, String pwd) {
        return name.equals("near") && pwd.equals("123");
    }

    @Override
    public void doPost(Request request, Response response) throws IOException {
    }
}
