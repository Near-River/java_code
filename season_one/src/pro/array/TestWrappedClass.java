package pro.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Near on 2015/11/25.
 */
public class TestWrappedClass {
    @Test
    public void test(){
        Integer a1 = new Integer(1000);
        Integer a2 = 1000;  // jdk5.0之后  引入自动装箱机制，编译器帮助我们改进代码：Integer a = new Integer(1000);

        Integer b1 = new Integer(100);
        Integer b2 = null;
        int c = b1;  //自动拆箱，编译器改进：b.intValue();

        System.out.println(c);

        Integer  d1 = 1234;
        Integer  d2 = 1234;

        System.out.println(d1 == d2);
        System.out.println(d1.equals(d2));

        Integer d3 = 100;    // [-128,127]之间的数，仍然当做基本数据类型来处理。
        Integer d4 = 100;
        System.out.println(d3 == d4);
        System.out.println(d3.equals(d4));
    }
}
