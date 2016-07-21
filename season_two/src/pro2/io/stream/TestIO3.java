package pro2.io.stream;

import org.junit.Test;
import pro2.io.FileUtils;

import java.io.*;

/**
 * 节点流操作
 * Created by Near on 2015/12/2.
 */
public class TestIO3 {
    /**
     * 字节数组节点流：ByteArrayInputStream / ByteArrayOutputStream
     */
    @Test
    public void testRead() {
        InputStream inputStream = null;
        String str = "我是一只小小小小鸟。\r\n娃哈哈，哈哈娃。";
        try {
            byte[] bytes = str.getBytes("utf-8");
            inputStream = new BufferedInputStream(new ByteArrayInputStream(bytes));

            byte[] flush = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(flush)) != -1) {
                String s = new String(flush, "utf-8");
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWrite() {
        byte[] dest = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String str = "我是一只小小小小鸟。\r\n娃哈哈，哈哈娃。";
        byte[] bytes = str.getBytes();
        byteArrayOutputStream.write(bytes, 0, bytes.length);

        dest = byteArrayOutputStream.toByteArray();
        System.out.println(new String(dest));
    }

    /**
     * 文件--程序（字节输入流）-->字节数组（字节数组输出流）
     * 字节数组--程序（字节数组输入流）-->文件（字节输出流）
     */
    @Test
    public void testReadAndWrite() {
        File src = new File("E:/pictures/default.png");
        File dest = new File("E:/pictures/touxiang.png");

        InputStream inputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        OutputStream outputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;

        byte[] bytes = null;
        byte[] flush = new byte[1024];
        int len = 0;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(src));
            byteArrayOutputStream = new ByteArrayOutputStream();
            while ((len = inputStream.read(flush)) != -1) {
                byteArrayOutputStream.write(flush, 0, flush.length);
            }
            byteArrayOutputStream.flush();

            // 获取文件源的字节数组节点流(即字节数组)
            bytes = byteArrayOutputStream.toByteArray();
            // System.out.println(new String(bytes));

            byteArrayInputStream = new ByteArrayInputStream(bytes);
            outputStream = new BufferedOutputStream(new FileOutputStream(dest));
            while ((len = byteArrayInputStream.read(flush)) != -1) {
                outputStream.write(flush, 0, flush.length);
            }
            outputStream.flush();
            System.out.println("Success!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.close(outputStream, inputStream);
        }
    }
}

