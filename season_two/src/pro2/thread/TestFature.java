package pro2.thread;


/**
 * Created by near on 2015/12/26.
 */
public class TestFature {

    public static void main(String[] args) {
        Client client = new Client();
        // 返回一个FutureData
        Data data = client.request("name");
        System.out.println("请求完毕！");
        try {
            // 处理其他业务
            // 在次过程中，真是数据RealData组装完成，重复利用等待时间
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        System.out.println("数据 = " + data.getResult()); // 真实数据

    }
}

class Client {
    public Data request(final String queryStr) {
        final FutureData future = new FutureData();
        // 开启一个新的线程来构造真实数据
        new Thread() {
            public void run() {
                RealData realData = new RealData(queryStr);
                future.setRealData(realData);
            }
        }.start();
        return future;
    }
}

interface Data {
    String getResult();
}

class FutureData implements Data {
    protected RealData realData = null;
    protected boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        // 唤醒所有正在等待该对象的线程
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        while (!isReady) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        return realData.getResult();
    }
}

class RealData implements Data {
    protected String result;

    public RealData(String para) { // 构造比较慢
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            sb.append(para);
            try {
                Thread.sleep(800);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        result = sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}