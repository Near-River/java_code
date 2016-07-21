package pro2.io;

import org.junit.Test;

import java.io.*;

/**
 * 字节缓冲流操作
 * 缓冲流（字节流）
 * Created by Near on 2015/12/2.
 */
public class TestBuffered {
    @Test
    public void testRead() {
        // 获取文件
        File file = new File("E:/Books/novels/《小王子》.txt");
        // 选择读取文件流
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                // System.out.println(new String(bytes));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 资源释放
            FileUtils.close(inputStream);
        }
    }

    @Test
    public void testWrite() {
        File file = new File("E:/Books/novels/aaa.txt");

        OutputStream outputStream = null;
        try {
            // outputStream = new FileOutputStream(file);
            // 以追加方式添加信息
            outputStream = new BufferedOutputStream(new FileOutputStream(file, true));
            String string = "我的名字叫路飞！";
            byte[] bytes = string.getBytes();
            outputStream.write(bytes, 0, bytes.length);
            // 对缓冲字节进行刷新
            outputStream.flush();
            System.out.println("OK!!!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.close(outputStream);
        }
    }
}

