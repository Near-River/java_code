package pro2.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 * 可以应用于字符串的分割 split(regex)
 * Created by near on 2015/12/13.
 */
public class TestRegex {
    @Test
    public void test() {
        String str = "yangxiao123";
        // 正则表达式
        String s_pattern = "\\w+";
        Pattern pattern = Pattern.compile(s_pattern);

        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.matches());
        System.out.println(matcher.group());
        // 查找与模式匹配的下一个子序列是否存在
        boolean b = matcher.find();
        System.out.println(b);
    }

    @Test
    public void test2() {
        String str = "yangxiao&hello*wahaha";
        // 正则表达式
        String s_pattern = "\\w+";
        Pattern pattern = Pattern.compile(s_pattern);

        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    // 处理捕获组
    @Test
    public void test3() {
        String str = "yang123&hello4321*wahaha135";
        // 表达式对象
        String s_pattern = "([a-z]+)([0-9]+)";
        Pattern pattern = Pattern.compile(s_pattern);

        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }
    }

    @Test
    public void test4() {
        String str = "yang123&hello4321*wahaha135";
        String s_pattern = "[0-9]+";

        Pattern pattern = Pattern.compile(s_pattern);

        Matcher matcher = pattern.matcher(str);
        String newStr = matcher.replaceAll("@");
        System.out.println(newStr);
    }
}