package pro2.http.myHttp;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * 对客户端的请求进行响应
 * Created by Near on 2016/1/26.
 */
public class ServerPlus {
    private ServerSocket server;
    private boolean isShutDown = false;

    public static void main(String[] args) {
        ServerPlus server = new ServerPlus();
        server.start();
    }

    public void start() {
        start(8080);
    }

    public void start(int port) {
        try {
            server = new ServerSocket(port);
            this.receive();
        } catch (IOException e) {
            stop();
        }
    }

    /**
     * 接收客户端
     */
    private void receive() {
       /* try {
            Socket client = server.accept();

            Request request = new Request(client.getInputStream());
            Response response = new Response(client.getOutputStream());
            Servlet servlet = new Servlet();
            servlet.service(request, response);

        } catch (IOException e) {
            stop();
        }*/

        try {
            while (!isShutDown) {
                new Thread(new Dispatcher(server.accept())).start();
            }
        } catch (IOException e) {
            stop();
        }
    }

    public void stop() {
        isShutDown = true;
        try {
            if (server != null) {
                server.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
