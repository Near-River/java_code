package pro.gof.structure.decorator_pattern;

/**
 * Created by near on 2015/12/12.
 */

/**
 * Component 抽象构件角色
 */
public interface ICar {
    void move();
}

/**
 * ConcreteComponent 具体构件角色
 */
class Car implements ICar {
    @Override
    public void move() {
        System.out.println("陆地上跑...");
    }
}

/**
 * Decorator 装饰角色
 * 持有一个抽象构件的引用，将客户端的请求转发给真实对象
 */
class SuperCar implements ICar {
    private ICar car;

    public SuperCar(ICar car) {
        this.car = car;
    }

    @Override
    public void move() {
        // 将客户端的请求转发给真实对象
        car.move();
    }
}

/**
 * ConcreteDecorator 具体装饰角色
 * 负责给构件对象增加新的责任
 */
class FlyCar extends SuperCar {
    public FlyCar(ICar car) {
        super(car);
    }

    // 给构件对象增加新的功能
    public void fly(){
        System.out.println("天上飞...");
    }

    @Override
    public void move() {
        super.move();
        fly();
    }
}

/**
 * ConcreteDecorator 具体装饰角色
 */
class SwimCar extends SuperCar {
    public SwimCar(ICar car) {
        super(car);
    }

    public void swim(){
        System.out.println("水里游...");
    }

    @Override
    public void move() {
        super.move();
        swim();
    }
}