package pro.gof.structure.decorator_pattern;

import org.junit.Test;

/**
 * Created by near on 2015/12/11.
 */
public class TestDecoratorPattern {
    @Test
    public void test() {
        ICar car = new Car();
        // car.move();

        FlyCar flyCar = new FlyCar(car);
        flyCar.move();
        SwimCar swimCar = new SwimCar(car);
        swimCar.move();

        // 增加两个功能：fly + swim
        FlyCar flyCar2 = new FlyCar(new SwimCar(car));
        flyCar2.move();
    }
}
