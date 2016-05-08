package pro.oop;

import org.junit.Test;

/**
 * Created by Near on 2015/11/25.
 */
public class TestInnerClass {
    @Test
    public void test() {
        A.B b = new A().new B();
        b.fun();

        A.C c = new A.C();
        c.fun();
    }
}

class A {
    int type = 0;
    static String color = "red";

    class B {
        int type = 1;

        public void fun() {
            System.out.println(A.this.type);
            System.out.println(type);
        }
    }

    static class C {
        static String color = "blue";

        public void fun() {
            System.out.println(A.color);
            System.out.println(color);
        }
    }
}