package pro2.network.udp;

import pro2.io.FileUtils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 * 客户端：
 * 创建客户端（指定端口）
 * 准备数据（字节数组）
 * 数据打包（DatagramSocket+服务器地址和端口）
 * 发送数据
 * 释放资源
 * Created by Near on 2015/12/5.
 */
public class Client {
    public static void main(String[] args) {
        sendMsg();
        // sendMsgPlus();
    }

    public static void sendMsg() {
        DatagramSocket client = null;
        DatagramPacket packet = null;
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8088);
        try {
            // 创建客户端（指定端口）
            client = new DatagramSocket(8080);
            // 准备数据（字节数组）
            byte[] bytes = new String("我是谁").getBytes();
            // 数据打包（DatagramSocket+服务器地址和端口）
            packet = new DatagramPacket(bytes, 0, bytes.length, address);
            // 发送数据
            client.send(packet);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            client.close();
        }
    }

    public static void sendMsgPlus() {
        DatagramSocket client = null;
        DatagramPacket packet = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        DataOutputStream dataOutputStream = null;
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8088);
        try {
            // 创建客户端（指定端口）
            client = new DatagramSocket(8080);
            // 准备数据（字节数组）
            double d = 123.456;
            String s = null;
            byte[] bytes = new byte[1024];

            byteArrayOutputStream = new ByteArrayOutputStream();
            dataOutputStream = new DataOutputStream(new BufferedOutputStream(byteArrayOutputStream));
            dataOutputStream.writeDouble(d);

            Scanner scanner = new Scanner(System.in);
            s = scanner.next();
            dataOutputStream.writeUTF(s);
            dataOutputStream.flush();

            bytes = byteArrayOutputStream.toByteArray();

            // 数据打包（DatagramSocket+服务器地址和端口）
            packet = new DatagramPacket(bytes, 0, bytes.length, address);
            // 发送数据
            client.send(packet);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.close(dataOutputStream);
            // 释放资源
            client.close();
        }
    }
}
