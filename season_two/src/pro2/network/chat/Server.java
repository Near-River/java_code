package pro2.network.chat;

import pro2.io.FileUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * 支持群聊：
 * 服务器端：
 * 创建服务端（指定端口）
 * 准备客户端的连接(阻塞式)
 * 发送数据 + 接收数据
 * <p>
 * Created by Near on 2015/12/5.
 */
public class Server {
    private Set<Session> sessions = new HashSet<Session>();

    public static void main(String[] args) {
        new Server().start();
    }

    public void start() {
        ServerSocket server = null;
        Socket socket = null;

        try {
            // 创建服务端（指定端口）
            server = new ServerSocket(8080);
            while (true) {
                // 准备客户端的连接(阻塞式)
                socket = server.accept();
                Session session = new Session(socket);
                sessions.add(session);
                new Thread(session).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Session implements Runnable {
        private DataInputStream dataInputStream = null;
        private DataOutputStream dataOutputStream = null;
        private boolean isRunning = true;

        public Session() {
        }

        Session(Socket socket) {
            try {
                dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            } catch (IOException e) {
                // e.printStackTrace();
                isRunning = false;
                FileUtils.close(dataOutputStream, dataInputStream);
                sessions.remove(this);
            }
        }

        public String receive() {
            String msg = null;
            try {
                msg = dataInputStream.readUTF();
                System.out.println("Receive: " + msg);
            } catch (IOException e) {
                // e.printStackTrace();
                isRunning = false;
                FileUtils.close(dataOutputStream, dataInputStream);
            }
            return msg;
        }

        void sendMsg(String msg) {
            if (msg != null && !("".equals(msg))) {
                try {
                    dataOutputStream.writeUTF(msg);
                    System.out.println("Send: " + msg);
                    dataOutputStream.flush();
                } catch (IOException e) {
                    // e.printStackTrace();
                    isRunning = false;
                    FileUtils.close(dataOutputStream, dataInputStream);
                }
            }
        }

        // 发送消息给其他的客户端
        void sendMsg2() {
            String msg = receive();
            if (msg != null && !("".equals(msg))) {
                for (Session session : sessions) {
                    if (session == this) {
                        continue;
                    }
                    session.sendMsg(msg);
                }
            }
        }

        @Override
        public void run() {
            while (isRunning) {
                // sendMsg(receive());
                sendMsg2();
            }
        }
    }
}
