package pro2.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 任务调度
 * Timer 定时器类    TimerTask  任务类
 * 设置定时器任务
 * <p>
 * Created by Near on 2015/12/4.
 */
public class TestTaskScheduling {

    public static void main(String args[]) {
        Timer timer = new Timer();

        // 两秒钟后打印文字
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("lalalalalala................");
                // do something...
            }
        }, new Date(System.currentTimeMillis() + 2000));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
    }

}
