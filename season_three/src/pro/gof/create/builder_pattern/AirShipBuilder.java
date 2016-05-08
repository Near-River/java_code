package pro.gof.create.builder_pattern;

/**
 * Created by near on 2015/12/11.
 */
public interface AirShipBuilder {
    Engine buildEngine();

    OrbitalModule buildOrbitalModule();

    EscapeTower buildEscapeTower();
}

class MyAirShipBuilder implements AirShipBuilder {
    @Override
    public Engine buildEngine() {
        return new Engine("创建引擎");
    }

    @Override
    public OrbitalModule buildOrbitalModule() {
        return new OrbitalModule("创建轨道舱");
    }

    @Override
    public EscapeTower buildEscapeTower() {
        return new EscapeTower("创建逃逸塔");
    }
}
