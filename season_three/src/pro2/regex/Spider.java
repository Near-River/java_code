package pro2.regex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 网络爬虫
 * Created by near on 2015/12/13.
 */
public class Spider {

    public static void main(String[] args) {
        String s_url = "http://www.163.com";
        String charset = "gb2312";
        String context = getUrlContext(s_url, charset);

        // 捕获组的方向引用 + 开启非贪婪模式
        String s_pattern = "<a href=\"(http://.*?)\"";
        Pattern pattern = Pattern.compile(s_pattern);

        Matcher matcher = pattern.matcher(context);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }


    private static String getUrlContext(String s_url, String charset) {
        URL url = null;
        BufferedReader bufferedReader = null;
        StringBuilder context = new StringBuilder();
        try {
            url = new URL(s_url);
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName(charset)));
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                // System.out.println(str);
                context.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return context.toString();
    }
}
