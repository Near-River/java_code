package pro2.thread;

/**
 * 单例模式练习
 * Created by Near on 2015/12/4.
 */
public class SingletonPattern {
    public static void main(String args[]) {
        /*Jvm jvm = Jvm.getInstance();
        Jvm jvm2 = Jvm.getInstance();
        System.out.println(jvm);
        System.out.println(jvm2);

        Dog dog = new Dog();
        Dog dog2 = new Dog();
        System.out.println(dog);
        System.out.println(dog2);*/

        Jvm jvm = Jvm.getInstance();
        Jvm2 jvm2 = Jvm2.getInstance();
        Jvm3 jvm3 = Jvm3.getInstance();
    }
}

// 单例类创建方式一（懒汉式）
class Jvm {
    private static Jvm instance = null;

    private Jvm() {
        System.out.println("Jvm Constructor...");
    }

    /*public synchronized static Jvm getInstance() {
        if (instance == null) {
            instance = new Jvm();
        }
        return instance;
    }*/

    // 性能优化版
    public static Jvm getInstance() {
        // 提高利率，已创建对象不再同步
        if (instance == null) {
            synchronized (Jvm.class) {
                if (instance == null) {
                    instance = new Jvm();
                }
            }
        }
        return instance;
    }
}


// 单例类创建方式二（饿汉式）
class Jvm2 {
    private static Jvm2 instance = new Jvm2();

    private Jvm2() {
        System.out.println("Jvm2 Constructor...");
    }

    public static Jvm2 getInstance() {
        return instance;
    }
}

// 单例类创建方式二（饿汉式升级版）延缓类对象创建的时间
class Jvm3 {
    private static class JvmHolder {
        private static Jvm3 instance = new Jvm3();
    }

    private Jvm3() {
        System.out.println("Jvm3 Constructor...");
    }

    public static Jvm3 getInstance() {
        return JvmHolder.instance;
    }
}

class Dog {
}