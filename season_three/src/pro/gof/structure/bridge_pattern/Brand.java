package pro.gof.structure.bridge_pattern;

/**
 * Created by near on 2015/12/12.
 */
public interface Brand {
    void info();

    String getBrand();
}

class Lenovo implements Brand {
    @Override
    public void info() {
        System.out.println("联想品牌");
    }

    @Override
    public String getBrand() {
        return "联想品牌";
    }
}

class Dell implements Brand {
    @Override
    public void info() {
        System.out.println("戴尔品牌");
    }

    @Override
    public String getBrand() {
        return "戴尔品牌";
    }
}