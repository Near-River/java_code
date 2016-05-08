package pro.gof.action.strategy_pattern;

import org.junit.Test;

/**
 * 策略模式
 * Created by near on 2015/12/12.
 */
public class Client {
    @Test
    public void test() {
        double price = 10000;
        Strategy strategy = new NewCustomerManyStrategy();
        Context context = new Context(strategy);

        context.printPrice(price);
    }
}

/**
 * 负责与具体的策略类打交道
 * 具体算法由客户端决定
 */
class Context{
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void printPrice(double price){
        System.out.println("您的报价：" + strategy.getPrice(price));
    }
}

/**
 * 抽象策略
 */
interface Strategy {
    double getPrice(double originalPrice);
}

class NewCustomerFewStrategy implements Strategy {
    @Override
    public double getPrice(double originalPrice) {
        System.out.println("不打折");
        return originalPrice;
    }
}

class NewCustomerManyStrategy implements Strategy {
    @Override
    public double getPrice(double originalPrice) {
        System.out.println("打九折");
        return originalPrice * 0.9;
    }
}

class OldCustomerFewStrategy implements Strategy {
    @Override
    public double getPrice(double originalPrice) {
        System.out.println("打八五折");
        return originalPrice * 0.85;
    }
}

class OldCustomerManyStrategy implements Strategy {
    @Override
    public double getPrice(double originalPrice) {
        System.out.println("打八折");
        return originalPrice * 0.8;
    }
}