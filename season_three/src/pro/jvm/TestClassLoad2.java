package pro.jvm;

import org.junit.Test;

/**
 * Created by near on 2015/12/9.
 */
public class TestClassLoad2 {
    @Test
    public void test() {
        // 获取引用程序类加载器
        System.out.println(ClassLoader.getSystemClassLoader());
        // 获取扩展类加载器
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());

        // System.out.println(System.getProperty("java.class.path"));

        MyClassLoader myClassLoader = new MyClassLoader();
        MyClassLoader myClassLoader2 = new MyClassLoader();
        try {
            Class<?> c = myClassLoader.findClass("pro.jvm.Dog");
            Class<?> c2 = myClassLoader.findClass("pro.jvm.Dog");
            Class<?> c3 = myClassLoader2.findClass("pro.jvm.Dog");

            // 被两个类加载器加载的同一个类，JVM 不认为是相同的类
            System.out.println(c.hashCode());
            System.out.println(c2.hashCode());
            System.out.println(c3.hashCode());

            // 使用自定义类加载器
            System.out.println(c.getClassLoader());
            System.out.println(c2.getClassLoader());
            System.out.println(c3.getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 测试解密类加载器
    @Test
    public void test2() {
        int a = 7;
        System.out.println(Integer.toBinaryString(a ^ 0xff));

        // EncryptUtil.encrypt("D:/demo/Dog.class", "D:/demo/Dog2.class");
        MyClassLoaderPlus myClassLoaderPlus = new MyClassLoaderPlus();

        try {
            Class<?> c = myClassLoaderPlus.findClass("Dog");
            System.out.println(c.getClassLoader());
            System.out.println(c);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 测试线程上下文类加载器
    @Test
    public void test3() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);

        Thread.currentThread().setContextClassLoader(new MyClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());

        try {
            Class c = Thread.currentThread().getContextClassLoader().loadClass("pro.jvm.Dog");
            System.out.println(c.getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}