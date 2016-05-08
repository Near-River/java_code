package pro.gof.create.builder_pattern;

/**
 * Created by near on 2015/12/11.
 */
public class AirShip {
    private OrbitalModule orbitalModule;
    private Engine engine;
    private EscapeTower escapeTower;

    public OrbitalModule getOrbitalModule() {
        return orbitalModule;
    }

    public void setOrbitalModule(OrbitalModule orbitalModule) {
        this.orbitalModule = orbitalModule;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public EscapeTower getEscapeTower() {
        return escapeTower;
    }

    public void setEscapeTower(EscapeTower escapeTower) {
        this.escapeTower = escapeTower;
    }

    @Override
    public String toString() {
        return "AirShip{" +
                "orbitalModule=" + orbitalModule +
                ", engine=" + engine +
                ", escapeTower=" + escapeTower +
                '}';
    }
}

class OrbitalModule {
    private String orbitalModule;

    public OrbitalModule() {
    }

    public OrbitalModule(String orbitalModule) {

        this.orbitalModule = orbitalModule;
    }

    public String getOrbitalModule() {

        return orbitalModule;
    }

    public void setOrbitalModule(String orbitalModule) {
        this.orbitalModule = orbitalModule;
    }

    @Override
    public String toString() {
        return "OrbitalModule{" +
                "orbitalModule='" + orbitalModule + '\'' +
                '}';
    }
}

class Engine {
    private String engine;

    public Engine() {
    }

    public Engine(String engine) {

        this.engine = engine;
    }

    public String getEngine() {

        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "engine='" + engine + '\'' +
                '}';
    }
}

class EscapeTower {
    private String escapeTower;

    public String getEscapeTower() {
        return escapeTower;
    }

    public void setEscapeTower(String escapeTower) {
        this.escapeTower = escapeTower;
    }

    public EscapeTower() {

    }

    public EscapeTower(String escapeTower) {

        this.escapeTower = escapeTower;
    }

    @Override
    public String toString() {
        return "EscapeTower{" +
                "escapeTower='" + escapeTower + '\'' +
                '}';
    }
}