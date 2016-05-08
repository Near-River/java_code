package pro.gof.create.factory_pattern.abstract_factory_pattern;

import org.junit.Test;

/**
 * 抽象工厂模式，工厂方法模式的升级版
 * Created by near on 2015/12/10.
 */
public class TestFactoryPattern {
    @Test
    public void test(){
        CarFactory carFactory = new LuxureCarFactory();
        carFactory.createEngine().start();
        carFactory.createEngine().run();
        carFactory.createSeat().massage();
        carFactory.createTyre().revolve();
    }
}

