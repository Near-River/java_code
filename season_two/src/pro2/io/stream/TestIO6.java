package pro2.io.stream;

import org.junit.Test;
import pro2.io.FileUtils;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Near on 2015/12/3.
 */
public class TestIO6 {
    /**
     * 打印流：PrintStream  // 输出流
     */
    @Test
    public void test() {
        File file = new File("E:/Books/a.txt");
        PrintStream printStream = null;
        /*printStream = System.out;
        printStream.println("hello");*/
        try {
            printStream = new PrintStream(new BufferedOutputStream(new FileOutputStream(file)));
            printStream.println("杨萧");
            printStream.println(true);
            printStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            FileUtils.close(printStream);
        }
    }

    /**
     * 流的重定向：setIn()  setOut()
     */
    @Test
    public void test2() {
        File file = new File("E:/Books/b.txt");
        PrintStream printStream = null;
        try {
            // 设置打印流为自动刷新缓冲
            printStream = new PrintStream(new BufferedOutputStream(new FileOutputStream(file)), true);
            // 重定向到文件输出流
            System.setOut(printStream);
            System.out.println("你好，我是小明。");

            // 重定向到系统(控制台)输出流
            System.setOut(new PrintStream(new BufferedOutputStream(
                    new FileOutputStream(FileDescriptor.out)), true));
            System.out.println("你好，我是小明。");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() throws IOException {
        File file = new File("E:/Books/a.txt");
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            System.setIn(inputStream);
            Scanner scanner = new Scanner(System.in);
            System.out.println(scanner.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /*InputStream is = System.in;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        System.out.println("please input something ...");
        String str = bufferedReader.readLine();
        System.out.println(str);*/
    }
}