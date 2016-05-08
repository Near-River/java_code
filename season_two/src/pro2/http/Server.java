package pro2.http;

import pro2.io.FileUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 手写 HTTPServer
 * 浏览器输入：http://localhost:8080
 * Created by Near on 2015/12/6.
 */
public class Server {
    private ServerSocket server = null;

    public static void main(String[] args) {
        new Server().start();
    }

    public void start() {
        try {
            server = new ServerSocket(8080);

            this.receive();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receive() {
        Socket socket = null;
        StringBuilder stringBuilder;
        String msg;
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        try {
            socket = server.accept();
            stringBuilder = new StringBuilder();
            /*
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while ((msg = bufferedReader.readLine()) != null) {
                stringBuilder.append(msg);
                stringBuilder.append("\r\n");
            }
            System.out.println(stringBuilder.toString().trim());*/

            byte[] bytes = new byte[1024];
            int len = 0;
            inputStream = new BufferedInputStream(socket.getInputStream());
            while ((len = inputStream.read(bytes)) != -1) {
                stringBuilder.append(new String(bytes, 0, len));
                stringBuilder.append("\r\n");
            }
            System.out.println(stringBuilder.toString().trim());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // FileUtils.close(bufferedReader);
            FileUtils.close(inputStream);
        }
    }

    public void stop() {
        //...
    }

}
