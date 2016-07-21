package pro2.io;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Near on 2015/12/2.
 */
public class TestFile {
    @Test
    public void test() throws IOException {
        // 文件分割符和路径分隔符: \  ;
        System.out.println(File.separator + "  " + File.pathSeparator);

        File file = new File("E:/pictures/01238_HD.jpg");
        // File file = new File("01238_HD");
        // 如果是绝对路径，则返回绝对路径；如果是相对路径，则返回相对路径
        System.out.println(file.getName() + "  " + file.getPath());
        // System.out.println(file.getAbsoluteFile()+"  "+file.getAbsolutePath());

        // 判断信息
        System.out.println(file.isAbsolute() + " " + file.isFile() + " " + file.exists());
        System.out.println(file.canRead() + " " + file.canWrite());
        System.out.println("字节数: " + file.length());

        File newFile = new File("E:/aaa.txt");
        File tempFile = new File("E:/");

        System.out.println(File.createTempFile("haha", ".jpg", tempFile));

        if (!newFile.exists()) {
            try {
                // 不存在则创建新文件
                System.out.println(newFile.createNewFile());
                // Thread.sleep(1000);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test2() {
        File file = new File("E:/aaa.txt");
        File dir = new File("E:/pictures");

        // 创建文件夹
        // System.out.println(file.mkdir());
        // System.out.println(file.mkdirs());

        String[] filesName = dir.list();
        System.out.println(Arrays.toString(filesName));

        // 设置文件过滤器
        String[] filesNames2 = dir.list(new FilenameFilter() {
            @Override
            // dir 代表当前路径: E:/pictures
            public boolean accept(File dir, String name) {
                return name.endsWith(".png");
            }
        });
        System.out.println(Arrays.toString(filesNames2));

        File[] files = dir.listFiles();
        System.out.println(Arrays.toString(files));

        // 获取所有盘符的根路径：[C:\, D:\, E:\, F:\, G:\]
        File[] files2 = File.listRoots();
        System.out.println(Arrays.toString(files2));
    }
}