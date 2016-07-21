package pro2.network.udp;

import pro2.io.FileUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 服务器端：
 * 创建服务端（指定端口）
 * 准备接收容器（字节数组）
 * 数据包（接收数据）
 * 分析数据
 * 释放资源
 * <p>
 * Created by Near on 2015/12/5.
 */
public class Server {
    public static void main(String[] args) {
        receiveMsg();
        // receiveMsgPlus();
    }

    public static void receiveMsg() {
        DatagramSocket server = null;
        DatagramPacket packet = null;
        try {
            // 创建服务端（指定端口）
            server = new DatagramSocket(8088);
            // 准备接收容器（字节数组）
            byte[] bytes = new byte[1024];
            packet = new DatagramPacket(bytes, bytes.length);
            // 数据包（接收数据）
            server.receive(packet);
            // 分析数据
            byte[] data = packet.getData();
            System.out.println(new String(data, 0, data.length));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            server.close();
        }
    }

    public static void receiveMsgPlus() {
        DatagramSocket server = null;
        DatagramPacket packet = null;
        DataInputStream dataInputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            // 创建服务端（指定端口）
            server = new DatagramSocket(8088);
            // 准备接收容器（字节数组）
            byte[] bytes = new byte[1024];
            packet = new DatagramPacket(bytes, bytes.length);
            // 数据包（接收数据）
            server.receive(packet);
            // 分析数据
            byte[] data = packet.getData();
            byteArrayInputStream = new ByteArrayInputStream(data);
            dataInputStream = new DataInputStream(new BufferedInputStream(byteArrayInputStream));
            Double d = dataInputStream.readDouble();
            String s = dataInputStream.readUTF();

            System.out.println(d + "\t" + s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.close(dataInputStream);
            // 释放资源
            server.close();
        }
    }
}
