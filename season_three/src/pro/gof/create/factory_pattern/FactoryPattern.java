package pro.gof.create.factory_pattern;

import org.junit.Test;

/**
 * Created by near on 2015/12/10.
 */
public class FactoryPattern {
    @Test
    public void test(){
        Car car = SimpleFactoryPattern.createAudi();
        Car car2 = SimpleFactoryPattern.createBmw();

        Car car3 = new AudiCarFactory().createCar();
        Car car4 = new BmwCarFactory().createCar();
    }
}

interface Car{}

class Audi implements Car {
    public Audi() {
        System.out.println("创建奥迪");
    }
}

class Bmw implements Car {
    public Bmw() {
        System.out.println("创建宝马");
    }
}

// 简单工厂模式
class SimpleFactoryPattern{
    public static Car createAudi(){
        return new Audi();
    }

    public static Car createBmw(){
        return new Bmw();
    }
}

// 工厂方法模式
interface CarFactory{
    Car createCar();
}

class AudiCarFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new Audi();
    }
}

class BmwCarFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new Bmw();
    }
}