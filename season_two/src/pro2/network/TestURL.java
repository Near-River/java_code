package pro2.network;

import org.junit.Test;
import pro2.io.FileUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * URL：协议 + 主机域名 + 端口 + 资源 URI
 * 构建：URL(String spec) // 绝对路径
 * URL(URL context, String spec) // 相对路径
 * Created by Near on 2015/12/5.
 */
public class TestURL {
    @Test
    public void test() {
        try {
            URL url = new URL("http://www.baidu.com:80/index.html?name=near&password=123");

            System.out.println(url.getPort());
            System.out.println(url.getProtocol());
            System.out.println(url.getHost());
            // 获取相对路径
            System.out.println(url.getPath());
            // 获取资源
            System.out.println(url.getFile());
            // 获取参数
            System.out.println(url.getQuery());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 网络爬虫：获取资源
     */
    @Test
    public void test2() {
        URL url = null;
        File file = new File("D:/baidu.html");
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            url = new URL("http://www.baidu.com");

            InputStream inputStream = url.openStream();
            /*byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                System.out.println(new String(bytes));
            }*/
            bufferedReader = null;
            String line = null;
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            while ((line = bufferedReader.readLine()) != null) {
                // System.out.println(line);
                bufferedWriter.write(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.close(bufferedWriter, bufferedReader);
        }
    }
}