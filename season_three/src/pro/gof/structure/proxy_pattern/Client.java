package pro.gof.structure.proxy_pattern;

import org.junit.Test;

/**
 * 静态代理：
 * Created by near on 2015/12/11.
 */
public class Client {
    @Test
    public void test() {
        Star realStar = new RealStar();
        Star proxyStar = new ProxyStar(realStar);

        proxyStar.signContract();
        proxyStar.bookTicket();
        proxyStar.sing();
        proxyStar.receivePayment();
    }
}

interface Star {
    void signContract();

    void bookTicket();

    // 真实对象的核心业务逻辑
    void sing();

    void receivePayment();
}

class RealStar implements Star {
    @Override
    public void signContract() {
    }

    @Override
    public void bookTicket() {
    }

    @Override
    public void sing() {
        System.out.println("RealStar: only you...");
    }

    @Override
    public void receivePayment() {
    }
}

class ProxyStar implements Star {
    private Star realStar;

    public ProxyStar(Star star) {
        this.realStar = star;
    }

    @Override
    public void signContract() {
        System.out.println("ProxyStar: sign contract");
    }

    @Override
    public void bookTicket() {
        System.out.println("ProxyStar: book ticket");
    }

    @Override
    public void sing() {
        realStar.sing();
    }

    @Override
    public void receivePayment() {
        System.out.println("ProxyStar: receive final payment");
    }
}