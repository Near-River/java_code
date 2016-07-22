package pro.gof.create.single_pattern;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.concurrent.CountDownLatch;

/**
 * 使用反射和反序列化破解单例模式  如何防止漏洞 ?
 * Created by near on 2015/12/10.
 */
public class TestSingletonPattern {
    @Test
    public void test() {
        SingletonDemo singletonDemo1 = SingletonDemo.getInstance();
        Class<?> c = null;
        try {
            c = SingletonDemo.class;
            Constructor<SingletonDemo> constructor = (Constructor<SingletonDemo>) c.getDeclaredConstructor(null);
            constructor.setAccessible(true);
            SingletonDemo singletonDemo2 = constructor.newInstance(null);

            System.out.println(singletonDemo1.hashCode());
            System.out.println(singletonDemo2.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        SingletonDemo singletonDemo1 = SingletonDemo.getInstance();
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:/demo/a.txt"));
            objectOutputStream.writeObject(singletonDemo1);
            objectOutputStream.flush();

            objectInputStream = new ObjectInputStream(new FileInputStream("D:/demo/a.txt"));
            SingletonDemo singletonDemo2 = (SingletonDemo) objectInputStream.readObject();
            System.out.println(singletonDemo1.hashCode());
            System.out.println(singletonDemo2.hashCode());

            objectInputStream.close();
            objectOutputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试几种单例模式的效率
     * 使用同步辅助类：CountDownLatch
     * 在完成一组在其他线程中执行的操作之前，它允许一个或多个线程被抑制等待
     * countDown()    await()
     */
    @Test
    public void test3() {
        int size = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(size);

        long start = System.currentTimeMillis();
        for (int i=0; i<size; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        Object instance = SingletonDemo2.getInstance();
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
        try {
            // 阻塞 main 线程，直到所有任务线程运行完毕
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start));
    }
}

class SingletonDemo implements Serializable {
    private static SingletonDemo instance = null;

    private SingletonDemo() {
        // 防反射破解
        if (instance != null) {
            throw new RuntimeException();
        }
    }

    public static synchronized SingletonDemo getInstance() {
        if (instance == null) {
            instance = new SingletonDemo();
        }
        return instance;
    }

    // 防反序列化破解
    private Object readResolve() throws ObjectStreamException {
        // 当调用反序列化时，直接返回 instance 对象
        return instance;
    }
}
