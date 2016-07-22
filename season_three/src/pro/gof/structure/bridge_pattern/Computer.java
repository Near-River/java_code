package pro.gof.structure.bridge_pattern;

/**
 * Created by near on 2015/12/12.
 */
public abstract class Computer {
    // 建立桥接关系(在抽象层建立关联)
    protected Brand brand;

    public Computer(Brand brand) {
        this.brand = brand;
    }

    public void sale() {
        // brand.info();
    }
}

class DeskTop extends Computer {
    public DeskTop(Brand brand) {
        super(brand);
    }

    @Override
    public void sale() {
        super.sale();
        System.out.println("销售" + brand.getBrand() + "台式机");
    }
}

class LapTop extends Computer {
    public LapTop(Brand brand) {
        super(brand);
    }

    @Override
    public void sale() {
        super.sale();
        System.out.println("销售" + brand.getBrand() + "笔记本电脑");
    }
}