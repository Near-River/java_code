package pro.jvm;

import org.junit.Test;

/**
 * Created by near on 2015/12/9.
 */
public class TestClassLoad {
    static{
        System.out.println("静态初始化TestClassLoad");
    }

    @Test
    public void test(){
        System.out.println("调用TestClassLoad的main方法");
        // 主动引用
		// new A();
		// System.out.println(A.width);
        /*try {
            Class.forName("pro.jvm.A");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        // 被动引用
		System.out.println(A.MAX);
		A[] as = new A[10];
        // System.out.println(B.width);
    }

}

class B  extends A {
    static {
        System.out.println("静态初始化B");
    }
}

class A extends A_Father {
    // 静态变量 = 静态域    field
    public static int width=100;
    public static final int MAX=100;

    static {
        System.out.println("静态初始化类A");
        width=300;
    }

    public A(){
        System.out.println("创建A类的对象");
    }
}

class A_Father extends Object {
    static {
        System.out.println("静态初始化A_Father");
    }
}
