package pro2.io.stream;

import org.junit.Test;

import java.io.*;

/**
 * 字符流操作
 * Created by Near on 2015/12/2.
 */
public class TestIO2 {
    @Test
    public void testRead() {
        // 获取文件
        File file = new File("E:/Books/novels/《小王子》.txt");
        // 选择读取文件流
        Reader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[1024];
            int len = 0;
            while ((len = reader.read(chars)) != -1) {
                String s = new String(chars);
                System.out.println(s);
            }

        } catch (FileNotFoundException e) {
            // 捕获检查时异常
            System.out.println("目标文件不存在!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 资源释放
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testWrite() {
        File file = new File("E:/Books/novels/aaa.txt");
        Writer writer = null;
        try {
            // 以追加方式添加信息
            writer = new FileWriter(file, true);
            String string = "我的名字叫路飞！\r\nHello, World.";
            char[] chars = new char[string.length()];
            string.getChars(0, string.length(), chars, 0);
            writer.write(chars);
            // 对缓冲字节进行刷新
            writer.flush();
            System.out.println("OK!!!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 文件的拷贝操作
     */
    @Test
    public void testCopyFile() {
        String srcName = "E:/Books/novels/《小王子》.txt";
        String destName = "E:/Books/novels/bbb.txt";

        Reader reader = null;
        Writer writer = null;
        try {
            reader = new FileReader(srcName);
            writer = new FileWriter(destName);
            char[] chars = new char[1024];
            int len = 0;
            while ((len = reader.read(chars)) != -1) {
                writer.write(chars, 0, chars.length);
            }
            writer.flush();
            System.out.println("拷贝成功!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

