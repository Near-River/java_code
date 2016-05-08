package pro2.io.stream;

import org.junit.Test;
import pro2.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Near on 2015/12/3.
 */
public class TestIO7 {
    /**
     * RandomAccessFile: 做文件分割(明确分割快的分配大小和实际大小)
     *      seek()
     */
    @Test
    public void test() {
        File file = new File("E:/Books/a.txt");
        RandomAccessFile randomAccessFile = null;
        byte[] bytes = new byte[1024];
        int len = 0;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            // randomAccessFile.seek(20);
            while ((len = randomAccessFile.read(bytes)) != -1) {
                System.out.println(new String(bytes));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.close(randomAccessFile);
        }
    }

    /**
     * 测试自定义文件分割类：SplitFile
     */
    @Test
    public void test2() {
        /*String path = "E:/Books/novels/《小王子》.txt";

        SplitFile splitFile = new SplitFile(path);
        // System.out.println(splitFile.toString());
        System.out.println(splitFile.getSize());*/

        // 做文件分割与合并
        String srcPath = "E:/pictures/01238_HD.jpg";
        String destDirPath = "E:/pictures/temp";
        String destFilePath = "E:/pictures/1.jpg";
        SplitFile splitFile = new SplitFile(srcPath, 10240*5);
        System.out.println(splitFile.getSize());

        // splitFile.split(destDirPath);
        // splitFile.merge(destDirPath, destFilePath);
        splitFile.mergePlus(destDirPath, destFilePath);
    }
}