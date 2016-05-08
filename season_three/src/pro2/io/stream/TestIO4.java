package pro2.io.stream;

import org.junit.Test;

import java.io.*;

/**
 * 基本类型处理流:
 * DataInputStream readXxx() / DataInputStream writeXxx()
 * 读入和写入数据的类型和顺序要一致
 * Created by Near on 2015/12/3.
 */
public class TestIO4 {
    @Test
    public void testWrite() {
        // 获取文件
        File file = new File("E:/Books/a.txt");
        // 选择写入文件流
        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            dataOutputStream.writeBoolean(true);
            dataOutputStream.writeChar('a');
            dataOutputStream.writeDouble(100.0);
            dataOutputStream.writeLong(100000000L);
            dataOutputStream.writeUTF("飞雪连天射白鹿");
            /*dataOutputStream.writeChars("飞雪连天射白鹿");
            dataOutputStream.writeBytes("飞雪连天射白鹿");*/
            dataOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testRead() {
        // 获取文件
        File file = new File("E:/Books/a.txt");
        DataInputStream dataInputStream = null;

        try {
            dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
            boolean b = dataInputStream.readBoolean();
            char c = dataInputStream.readChar();
            double d = dataInputStream.readDouble();
            long l = dataInputStream.readLong();
            String s = dataInputStream.readUTF();
            System.out.println(b+"\t"+c+"\t"+d+"\t"+l+"\t"+s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dataInputStream != null) {
                try {
                    dataInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

