package pro.gof.structure.facade_pattern;

import org.junit.Test;

/**
 * Created by near on 2015/12/12.
 */
public class TestFacadePattern {
    @Test
    public void test() {
        new RegisterFacade().register();
    }
}

// 模拟注册公司流程
class RegisterFacade {
    public void register(){
        工商局 a = new 海淀区工商局();
        a.checkName();
        质检局 b = new 海淀质检局();
        b.orgCodeCertificate();
        税务局 c  = new 海淀税务局();
        c.taxCertificate();
        银行 d = new 中国工商银行();
        d.openAccount();
    }
}

interface 工商局 {
    void checkName();
}

class 海淀区工商局 implements 工商局 {
    @Override
    public void checkName() {
        System.out.println("检查名字是否有冲突！");
    }
}

interface 税务局 {
    // 办理税务登记证
    void taxCertificate();
}

class 海淀税务局 implements 税务局 {
    @Override
    public void taxCertificate() {
        System.out.println("在海淀税务局办理税务登记证！");
    }
}

interface 银行 {
    void openAccount();
}

class 中国工商银行 implements 银行 {
    @Override
    public void openAccount() {
        System.out.println("在中国工商银行开户！");
    }
}

interface 质检局 {
    // 办理组织机构代码证
    void orgCodeCertificate();
}

class 海淀质检局 implements 质检局 {
    @Override
    public void orgCodeCertificate() {
        System.out.println("在海淀区质检局办理组织机构代码证！");
    }
}