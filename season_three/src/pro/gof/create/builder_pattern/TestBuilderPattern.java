package pro.gof.create.builder_pattern;

import org.junit.Test;

/**
 * 建造者模式(常常与工厂模式搭配使用)
 * Created by near on 2015/12/11.
 */
public class TestBuilderPattern {
    @Test
    public void test(){
        AirShipDirector airShipDirector = new MyAirShipDirector(new MyAirShipBuilder());
        AirShip airShip = airShipDirector.directAirShip();

        System.out.println(airShip);
    }
}
