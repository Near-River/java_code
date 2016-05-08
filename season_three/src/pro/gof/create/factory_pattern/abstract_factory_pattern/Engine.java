package pro.gof.create.factory_pattern.abstract_factory_pattern;

interface Engine {
    void run();
    void start();
}

class LuxureEnigne implements Engine {
    @Override
    public void run() {
        System.out.println("LuxureEnigne:run()");
    }

    @Override
    public void start() {
        System.out.println("LuxureEnigne:start()");
    }
}

class LowerEngine implements Engine {
    @Override
    public void run() {
        System.out.println("LowerEngine:run()");
    }

    @Override
    public void start() {
        System.out.println("LowerEngine:start()");
    }
}