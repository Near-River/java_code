package pro2.http;

import pro2.io.FileUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * 对客户端的请求进行响应
 * Created by Near on 2016/1/26.
 */
public class Server2 {
    private ServerSocket server;
    public static final String CRLF = "\r\n";
    public static final String BLANK = " ";

    public static void main(String[] args) {
        Server2 server = new Server2();
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
     * 接收客户端
     */
    private void receive() {
        BufferedWriter bw = null;
        try {
            Socket client = server.accept();
            byte[] data = new byte[20480];
            int len = client.getInputStream().read(data);
            //接收客户端的请求信息
            String requestInfo = new String(data, 0, len).trim();
            System.out.println(requestInfo);

            //响应客户端请求
            StringBuilder responseContext = new StringBuilder();
            responseContext.append("<html><head><title>HTTP响应示例</title>" +
                    "</head><body><h1>Hello World!</h1></body></html>");

            StringBuilder response = new StringBuilder();
            //1)  HTTP协议版本、状态代码、描述
            response.append("HTTP/1.1").append(BLANK).append("200").append(BLANK).append("OK").append(CRLF);
            //2)  响应头(Response Head)
            response.append("Server:Near Server/0.0.1").append(CRLF);
            response.append("Date:").append(new Date()).append(CRLF);
            response.append("Content-type:text/html;charset=GBK").append(CRLF);
            //设置正文长度 ：字节长度
            response.append("Content-Length:").append(responseContext.toString().getBytes().length).append(CRLF);
            //3)正文之前加空格
            response.append(CRLF);
            //4)添加正文
            response.append(responseContext);

            System.out.println(responseContext);

            //输出流
            bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bw.write(response.toString());
            bw.flush();
        } catch (IOException ignored) {
        } finally {
            FileUtils.close(bw);
        }
    }

    public void stop() {

    }
}
