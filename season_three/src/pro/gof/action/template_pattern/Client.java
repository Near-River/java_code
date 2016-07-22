package pro.gof.action.template_pattern;

import org.junit.Test;

/**
 * 模板模式
 * Created by near on 2015/12/12.
 */
public class Client {
    @Test
    public void test() {
        Bank bank = new DrawMoney();
        bank.process();

        // 采用匿名内部类
        Bank bank2 = new Bank() {
            @Override
            protected void transact() {
                System.out.println("取款9998");
            }
        };
        bank2.process();
    }
}

/**
 * 模拟银行办理业务流程
 */
abstract class Bank {
    protected void takeNumber() {
        System.out.println("排队取号");
    }

    protected abstract void transact();

    protected void evaluate() {
        System.out.println("反馈评分");
    }

    /**
     * 模板方法
     */
    public void process() {
        this.takeNumber();
        this.transact();
        this.evaluate();
    }
}

class DrawMoney extends Bank {
    @Override
    public void transact() {
        System.out.println("取款10000");
    }
}