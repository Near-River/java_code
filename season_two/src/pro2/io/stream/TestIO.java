package pro2.io.stream;

import org.junit.Test;
import pro2.io.FileUtils;

import java.io.*;

/**
 * 字节流操作
 * Created by Near on 2015/12/2.
 */
public class TestIO {
    @Test
    public void testRead() {
        // 获取文件
        File file = new File("E:/Books/novels/《小王子》.txt");
        // 选择读取文件流
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                String s = new String(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 资源释放
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testWrite() {
        File file = new File("E:/Books/novels/aaa.txt");
        /*
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        */
        OutputStream outputStream = null;
        try {
            // outputStream = new FileOutputStream(file);
            // 以追加方式添加信息
            outputStream = new FileOutputStream(file, true);
            String string = "我的名字叫路飞！";
            byte[] bytes = string.getBytes();
            outputStream.write(bytes, 0, bytes.length);
            // 对缓冲字节进行刷新
            outputStream.flush();
            System.out.println("OK!!!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
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

        FileUtils.copyFile(srcName, destName);
    }

    @Test
    public void testCopyDir() {
        String srcName = "E:/Books/novels";
        String destName = "E:/Books/a";

        FileUtils.copyDir(srcName, destName);
    }
}

