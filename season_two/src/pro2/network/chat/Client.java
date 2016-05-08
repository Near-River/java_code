package pro2.network.chat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 客户端：
 * 创建客户端（必须指定服务器端的端口）
 * 准备数据（字节数组）
 * 接收数据 + 发送数据
 * Created by Near on 2015/12/5.
 */
public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        InetAddress inetAddress = null;
        // 创建客户端（必须指定服务器端的地址端口）
        try {
            inetAddress = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inetAddress, 8080);

            new Thread(new Send(socket)).start();
            new Thread(new Receiver(socket)).start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
