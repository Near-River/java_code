package pro.gof.action.mediator_pattern;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 中介者模式
 * Created by near on 2015/12/12.
 */
public class Client {
    @Test
    public void test() {
        Mediator mediator = new ConcreteMediator();
        Department department = new Development(mediator);
        Department department2 = new Finacial(mediator);

        mediator.register("研发部", department);
        mediator.register("财务部", department2);

        department.selfAction();
        department.outAction();
    }
}

/**
 * 抽象中介者
 */
interface Mediator {
    void register(String dName, Department department);

    void command(String dName);
}

/**
 * 具体中介者类
 */
class ConcreteMediator implements Mediator {
    private Map<String, Department> map = new HashMap<>();

    @Override
    public void register(String dName, Department department) {
        map.put(dName, department);
    }

    @Override
    public void command(String dName) {
        System.out.println("老板： 对" + dName + "下达命令");
        map.get(dName).selfAction();
    }
}

/**
 * 抽象同事对象
 */
interface Department {
    void selfAction();

    void outAction();
}

/**
 * 具体同事对象类
 */
class Development implements Department {
    private Mediator mediator;

    public Development(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void selfAction() {
        System.out.println("研发部：研发项目");
    }

    @Override
    public void outAction() {
        System.out.println("研发部汇报: 没钱了！");
        mediator.command("财务部");
    }
}

class Finacial implements Department {
    private Mediator mediator;

    public Finacial(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void selfAction() {
        System.out.println("财务部：处理财务");
    }

    @Override
    public void outAction() {
        System.out.println("财务部：汇报工作");
    }
}