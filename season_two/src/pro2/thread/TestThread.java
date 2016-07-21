package pro2.thread;

/**
 * Created by Near on 2015/12/3.
 */
public class TestThread extends Thread {
    static int i = 0;

    public static void main(String args[]) throws InterruptedException {
        Tiger tiger = new Tiger();
        Rubbit rubbit = new Rubbit();

        tiger.start();
        rubbit.start();

        // 线程阻塞机制的测试
         /*while (true) {
            Thread.sleep(1000);
            if (++i % 3 == 0) {
                // 暂停 main 线程
                Thread.yield();
            }else {
                System.out.println(Thread.currentThread().getName());
            }
        }*/

        /*
        try {
            // 线程 tiger 运行完成，线程 rubbit 才可以运行
            tiger.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
    }
}

class Rubbit extends Thread {
    int i = 0;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println("Rubbit: " + (++i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Tiger extends Thread {
    int i = 0;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
                System.out.println("Tiger: " + (++i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}