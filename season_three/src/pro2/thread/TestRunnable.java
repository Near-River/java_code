package pro2.thread;

/**
 * 模拟购票系统
 *      存在问题：线程并发访问资源，读取脏数据
 * Created by Near on 2015/12/4.
 */
public class TestRunnable implements Runnable {
    private static int i = 10;

    public static void main(String args[]) {
        Runnable testRunnable = new TestRunnable();
        Thread thread = new Thread(testRunnable);
        Thread thread2 = new Thread(testRunnable);
        Thread thread3 = new Thread(testRunnable);
        thread.start();
        thread2.start();
        thread3.start();
    }

    @Override
    public void run() {
        // 作临界区
        while (i > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i <= 0) {
                break;
            }
            System.out.println(Thread.currentThread() + "获得" + i);
            i--;
        }
    }
}
