package pro2.io;

import org.junit.Test;

import java.io.*;

/**
 * 出现乱码的原因：
 * ①编码和解码字符集不统一
 * ②字节缺少，长度丢失
 * Created by Administrator on 2015/12/2.
 */
public class TestConverter {
    @Test
    public void test() {
        String str = "杨萧";
        try {
            // byte[] bytes = str.getBytes("gbk");
            byte[] bytes = str.getBytes("utf-8");

            System.out.println(new String(bytes, "utf-8"));
            // System.out.println(new String(bytes, "iso-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCopyFile() {
        File file = new File("E:/Books/novels/《小王子》.txt");
        File nFile = new File("E:/Books/novels/ccc.txt");

        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            InputStream inputStream = new FileInputStream(file);
            OutputStream outputStream = new FileOutputStream(nFile);
            // 指定字符集要和读入文本的编码格式一致
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
                System.out.println(line);
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
