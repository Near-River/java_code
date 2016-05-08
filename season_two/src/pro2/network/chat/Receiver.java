package pro2.network.chat;

import pro2.io.FileUtils;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Near on 2015/12/5.
 */
public class Receiver implements Runnable {
    private DataInputStream dataInputStream = null;
    private boolean isRunning = true;

    public Receiver() {}

    public Receiver(Socket socket) {
        try {
            dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            // e.printStackTrace();
            isRunning = false;
            FileUtils.close(dataInputStream);
        }
    }

    public String receive() {
        String msg = null;
        try {
            msg = dataInputStream.readUTF();
        } catch (IOException e) {
            // e.printStackTrace();
            isRunning = false;
            FileUtils.close(dataInputStream);
        } finally {
            return msg;
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            String msg = receive();
            if (msg != null && !("".equals(msg))) {
                System.out.println("Receive: " + msg);
            }
        }
    }
}
