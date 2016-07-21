package pro2.network.ftp;

import pro2.io.FileUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * 服务器端：
 * 创建服务端（指定端口）
 * 准备客户端的连接(阻塞式)
 * 发送数据 + 接收数据
 * <p>
 * Created by Near on 2015/12/5.
 */
public class Server {
    public static void main(String[] args) {
        // receiveMsg();
        receiveMsgPlus();
    }

    public static void receiveMsg() {
        ServerSocket server = null;
        Socket socket = null;
        BufferedWriter bufferedWriter = null;
        try {
            // 创建服务端（指定端口）
            server = new ServerSocket(8080);
            // 准备客户端的连接(阻塞式)
            socket = server.accept();
            System.out.println("连接成功!");
            // 发送数据 + 接收数据
            String msg = "杨萧";

            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write(msg);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 支持接收单个客户端发送的消息
    public static void receiveMsgPlus() {
        ServerSocket server = null;
        Socket socket = null;
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        try {
            // 创建服务端（指定端口）
            server = new ServerSocket(8080);
            // 准备客户端的连接(阻塞式)
            socket = server.accept();
            System.out.println("连接成功!");
            while (true) {
                // 接收数据 + 发送数据
                dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                String msg = dataInputStream.readUTF();
                System.out.println("Receive: " + msg);

                dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                dataOutputStream.writeUTF("服务器接收到了消息: " + msg);
                dataOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.close(dataOutputStream, dataInputStream);
        }
    }
}
