package pro.gof.create.single_pattern;

import org.junit.Test;

/**
 * 单例模式
 * Created by near on 2015/12/10.
 */
public class SingletonPattern {
    @Test
    public void test() {
        SingletonDemo1 singletonDemo1 = SingletonDemo1.getInstance();
        SingletonDemo1 singletonDemo2 = SingletonDemo1.getInstance();

        System.out.println(singletonDemo1 == singletonDemo2);
    }

    @Test
    public void test2() {
        /*SingletonDemo2 singletonDemo1 = SingletonDemo2.getInstance();
        SingletonDemo2 singletonDemo2 = SingletonDemo2.getInstance();*/

        SingletonDemo3 singletonDemo1 = SingletonDemo3.getInstance();
        SingletonDemo3 singletonDemo2 = SingletonDemo3.getInstance();

        System.out.println(singletonDemo1 == singletonDemo2);
    }

    @Test
    public void test3() {
        /*SingletonDemo4 singletonDemo1 = SingletonDemo4.getInstance();
        SingletonDemo4 singletonDemo2 = SingletonDemo4.getInstance();*/

        SingletonDemo5 singletonDemo1 = SingletonDemo5.INSTANCE;
        SingletonDemo5 singletonDemo2 = SingletonDemo5.INSTANCE;

        System.out.println(singletonDemo1 == singletonDemo2);
        System.out.println(singletonDemo1.getClass().getName());
    }
}

/**
 * 饿汉式单例模式
 */
class SingletonDemo1 {
    private static SingletonDemo1 instance = new SingletonDemo1();

    // 构造器私有化
    private SingletonDemo1() {
    }

    public static SingletonDemo1 getInstance() {
        return instance;
    }
}

/**
 * 懒汉式单例模式
 */
class SingletonDemo2 {
    private static SingletonDemo2 instance = null;

    private SingletonDemo2() {
    }

    public static synchronized SingletonDemo2 getInstance() {
        if (instance == null) {
            instance = new SingletonDemo2();
        }
        return instance;
    }
}

/**
 * 双重检测锁模式 不推荐使用
 */
class SingletonDemo3 {
    private static SingletonDemo3 instance = null;

    private SingletonDemo3() {
    }

    public static SingletonDemo3 getInstance() {
        if (instance == null) {
            SingletonDemo3 sc;
            synchronized (SingletonDemo3.class) {
                sc = instance;
                if (sc == null) {
                    synchronized (SingletonDemo3.class) {
                        if (sc == null) {
                            sc = new SingletonDemo3();
                        }
                    }
                }
            }
        }
        return instance;
    }
}

/**
 * 静态内部类的实现
 */
class SingletonDemo4 {
    private static class SingletonDemo4Instance {
        private static final SingletonDemo4 instance = new SingletonDemo4();
    }

    private SingletonDemo4() {
    }

    public static SingletonDemo4 getInstance() {
        return SingletonDemo4Instance.instance;
    }
}

enum SingletonDemo5 {
    // 枚举元素本身就是常量
    INSTANCE;

    // 定义相关操作
    public void operation() {
        // ...
    }
}