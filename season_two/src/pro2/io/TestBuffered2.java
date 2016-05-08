package pro2.io;

import org.junit.Test;

import java.io.*;

/**
 * 字符缓冲流操作
 * Created by Near on 2015/12/2.
 */
public class TestBuffered2 {
    /**
     * 文件的拷贝操作
     */
    @Test
    public void testCopyFile() {
        String srcName = "E:/Books/novels/《小王子》.txt";
        String destName = "E:/Books/novels/bbb.txt";

        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(srcName));
            bufferedWriter = new BufferedWriter(new FileWriter(destName));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
                // 写入换行符
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            System.out.println("拷贝成功!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.close(bufferedWriter, bufferedReader);
        }
    }
}

