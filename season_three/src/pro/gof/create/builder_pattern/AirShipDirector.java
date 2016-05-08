package pro.gof.create.builder_pattern;

/**
 * Created by near on 2015/12/11.
 */
public interface AirShipDirector {
    // 组装飞船
    AirShip directAirShip();
}

class MyAirShipDirector implements AirShipDirector {
    private AirShipBuilder airShipBuilder;

    public MyAirShipDirector(AirShipBuilder airShipBuilder) {
        this.airShipBuilder = airShipBuilder;
    }

    @Override
    public AirShip directAirShip() {
        // 构建者创建组件
        Engine engine = airShipBuilder.buildEngine();
        EscapeTower escapeTower = airShipBuilder.buildEscapeTower();
        OrbitalModule orbitalModule = airShipBuilder.buildOrbitalModule();

        // 组装者组装组件
        AirShip airShip = new AirShip();
        airShip.setEngine(engine);
        airShip.setEscapeTower(escapeTower);
        airShip.setOrbitalModule(orbitalModule);

        return airShip;
    }
}