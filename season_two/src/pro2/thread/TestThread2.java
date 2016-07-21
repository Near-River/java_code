package pro2.thread;

/**
 * 测试线程常用的方法
 * Created by Near on 2015/12/3.
 */
public class TestThread2 extends Thread {
    private boolean flag = true;
    private int i = 0;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public static void main(String args[]) throws InterruptedException {
        // test();
        test2();
    }

    public static void test() {
        TestThread2 testThread2 = new TestThread2();
        Thread thread = new Thread(testThread2, "myThread");
        System.out.println(thread.getName());
        thread.setName("haha");
        System.out.println(thread.getName());
        System.out.println(thread.isAlive());

        thread.start();
        System.out.println(thread.isAlive());

        System.out.println(Thread.currentThread().getName());
    }

    // 线程的优先级测试
    public static void test2() {
        TestThread2 myThread = new TestThread2();
        Thread thread = new Thread(myThread, "myThread1");
        TestThread2 myThread2 = new TestThread2();
        Thread thread2 = new Thread(myThread2, "myThread2");

        thread.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.MIN_PRIORITY);
        thread.start();
        thread2.start();

        try {
            Thread.sleep(1);
            myThread.setFlag(false);
            myThread2.setFlag(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (flag) {
            System.out.println(Thread.currentThread().getName() + ": " + ++i);
        }
    }

}

