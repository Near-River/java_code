package pro2.network.chat;

import pro2.io.FileUtils;

import java.io.*;
import java.net.Socket;

/**
 * 发送数据的线程
 * Created by Near on 2015/12/5.
 */
public class Send implements Runnable {
    private BufferedReader bufferedReader = null;
    private DataOutputStream dataOutputStream = null;
    private boolean isRunning = true;

    public Send() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public Send(Socket socket) {
        this();
        try {
            dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        } catch (IOException e) {
            // e.printStackTrace();
            isRunning = false;
            FileUtils.close(dataOutputStream, bufferedReader);
        }
    }

    private String getMsg() {
        String msg = null;
        try {
            msg = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            isRunning = false;
            FileUtils.close(dataOutputStream, bufferedReader);
        }finally {
            return msg;
        }
    }

    public void sendMsg() {
        String msg = getMsg();
        if (msg != null && !("".equals(msg))) {
            try {
                dataOutputStream.writeUTF(msg);
                System.out.println("Send: " + msg);
                dataOutputStream.flush();
            } catch (IOException e) {
                // e.printStackTrace();
                isRunning = false;
                FileUtils.close(dataOutputStream, bufferedReader);
            }
        }
    }

    @Override
    public void run() {
        while(isRunning){
            sendMsg();
        }
    }
}
