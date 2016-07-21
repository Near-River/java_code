package pro.collection_plus;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 使用队列模拟银行存取业务
 * 先进先出策略
 * <p>
 * Created by Near on 2015/11/30.
 */
public class TestQueue {
    @Test
    public void test() {
        Queue<Request> queue = new ArrayDeque<Request>();
        // 模拟排队情况
        for (int i = 0; i < 10; i++) {
            final int num = i + 1;
            queue.offer(new Request() {
                @Override
                public void deposit() {
                    System.out.println("为第" + num + "个人办理业务.");
                }
            });
        }
        service(queue);
        System.out.println(queue.size());
    }

    private static void service(Queue<Request> queue) {
        Request request = null;
        int count = 0;
        while ((request = queue.poll()) != null) {
            System.out.println("第" + (++count) + "个人需办理业务.");
            request.deposit();
        }
    }

}

interface Request {
    void deposit();
}
