package pro.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Near on 2015/11/25.
 */
public class TestString {
    @Test
    public void test() {
        String str1 = new String("haha");
        String str2 = new String("haha");

        String s1 = "haha";
        String s2 = "haha";

        System.out.println(str1.equals(str2));
        System.out.println(str1 == str2);
        System.out.println(s1.equals(s2));
        System.out.println(s1 == s2);

        /**
         * 模拟 String 的内部实现机制
         * String 为不可变得字符序列，只可以对字符数组的元素值进行修改
         *        而字符数组对象指向的地址空间是不可变的
         */
        final char[] value = new char[]{'a', 'b', 'c'};
        System.out.println(Arrays.toString(value));
        value[1] = 'k';
        // value = new char[]{};
        System.out.println(Arrays.toString(value));

        //*****************************************************************

        StringBuilder sb1 = new StringBuilder(8);
        // System.out.println(sb1.capacity());
        String s = null;
        sb1.append(true);
        sb1.append(100);
        sb1.append("java");
        sb1.append(s);
        System.out.println(sb1.toString());
    }
}
