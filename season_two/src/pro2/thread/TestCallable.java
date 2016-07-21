package pro2.thread;

import java.util.concurrent.*;

/**
 * 通过 Callable 接口实现多线程
 * call() 方法可以抛出异常  run() 方法不能抛出异常
 * Callable 的任务执行后可以有返回值（Future 对象）
 * Future 对象是异步计算的结果，提供了对任务的相关操作方法
 * <p>
 * Created by Near on 2015/12/4.
 */
public class TestCallable {
    public static void main(String args[]) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Callable race = new Race();
        Future<Integer> future = executorService.submit(race);

        try {
            int res = (int) future.get();
            System.out.println(res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("Wrong!!!");
            // e.printStackTrace();
        } finally {
            // 停止服务
            executorService.shutdownNow();
        }
    }
}

class Race implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        // return 100;
        return 1 / 0;
    }
}