package pro2.http.myHttp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 对客户端的请求进行响应
 * Created by Near on 2016/1/26.
 */
public class Server {
    private ServerSocket server;

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start() {
        try {
            server = new ServerSocket(8080);
            this.receive();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收客户端请求
     */
    private void receive() {
        try {
            Socket client = server.accept();
            Request request = new Request(client.getInputStream());

            //响应客户端请求
            Response response = new Response(client.getOutputStream());
            response.println("<html><head><title>HTTP响应示例</title></head>");
            response.println("<body><h1>Hello World!</h1>" + request.getParameter("name") + "</body></html>");
            response.pushToClient(200);
            response.close();
        } catch (IOException ignored) {
        }
    }

    public void stop() {
    }
}
