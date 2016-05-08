package pro.gof.create.factory_pattern.abstract_factory_pattern;

// 工厂方法模式
interface CarFactory {
    Engine createEngine();

    Tyre createTyre();

    Seat createSeat();
}

class LuxureCarFactory implements CarFactory {
    @Override
    public Engine createEngine() {
        return new LuxureEnigne();
    }

    @Override
    public Tyre createTyre() {
        return new LuxuryTyre();
    }

    @Override
    public Seat createSeat() {
        return new LuxurySeat();
    }
}

class LowerFactory implements CarFactory {
    @Override
    public Engine createEngine() {
        return new LowerEngine();
    }

    @Override
    public Tyre createTyre() {
        return new LowerTyre();
    }

    @Override
    public Seat createSeat() {
        return new LowerSeat();
    }
}

