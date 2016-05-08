package pro2.network.ftp;

import pro2.io.FileUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

/**
 * 客户端：
 * 创建客户端（必须指定服务器端的端口）
 * 准备数据（字节数组）
 * 接收数据 + 发送数据
 * Created by Near on 2015/12/5.
 */
public class Client {
    public static void main(String[] args) {
        // sendMsg();
        sendMsgPlus();
    }

    public static void sendMsg() {
        Socket socket = null;
        InetAddress inetAddress = null;
        BufferedReader bufferedReader = null;
        try {
            // 创建客户端（必须指定服务器端的地址端口）
            // Socket(InetAddress address, int port)
            inetAddress = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inetAddress, 8080);
            // 接收数据 + 发送数据
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg = bufferedReader.readLine();
            System.out.println(msg);

            bufferedReader.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMsgPlus() {
        Socket socket = null;
        InetAddress inetAddress = null;
        BufferedReader bufferedReader = null;
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        try {
            // 创建客户端（必须指定服务器端的地址端口）
            // Socket(InetAddress address, int port)
            inetAddress = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inetAddress, 8080);
            while (true) {
                // 发送数据 + 接收数据
                bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String msg = bufferedReader.readLine();
                dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                dataOutputStream.writeUTF(msg);
                System.out.println("Send: " + msg);
                dataOutputStream.flush();

                dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                msg = dataInputStream.readUTF();
                System.out.println("Receive: " + msg);
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.close(dataInputStream, dataOutputStream, bufferedReader);
        }
    }
}
