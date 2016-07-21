package pro2.http.myHttp;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Near on 2016/1/26.
 */
public class Dispatcher implements Runnable {
    private Socket client;
    private Request request;
    private Response response;
    private int code = 200;

    public Dispatcher(Socket client) {
        this.client = client;
        try {
            request = new Request(client.getInputStream());
            response = new Response(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            code = 500;
        }
    }

    @Override
    public void run() {
        Servlet servlet = new Servlet();
        servlet.service(request, response);

        try {
            response.pushToClient(code);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            response.pushToClient(500);
        } catch (IOException ignored) {
        }
        try {
            if (client != null) {
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
