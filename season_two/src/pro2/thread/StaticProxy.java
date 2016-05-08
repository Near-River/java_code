package pro2.thread;

import org.junit.Test;

/**
 * 静态代理模式：真实角色 + 代理角色 (均实现相同的接口)
 * Created by Near on 2015/12/4.
 */
public class StaticProxy {
    @Test
    public void test() {
        Marry me = new SomeBody("王二狗");
        WeddingCompany<SomeBody> weddingCompany = new WeddingCompany(me);
        weddingCompany.marry();
    }
}

interface Marry {
    void marry();
}

class SomeBody implements Marry {
    private String name;

    public SomeBody() {
    }

    public SomeBody(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void marry() {
        System.out.println(this.name + "结婚了");
    }
}

class WeddingCompany<T extends Marry> implements Marry {
    private T someBody;

    public WeddingCompany() {
    }

    public WeddingCompany(T someBody) {
        this.someBody = someBody;
    }

    public T getSomeBody() {
        return someBody;
    }

    public void setSomeBody(T someBody) {
        this.someBody = someBody;
    }

    @Override
    public void marry() {
        beforeMarry();
        someBody.marry();
        afterMarry();
    }

    public void beforeMarry() {
        System.out.println("Before...");
    }

    public void afterMarry() {
        System.out.println("After...");
    }
}