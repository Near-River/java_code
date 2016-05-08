package pro.gof.structure.bridge_pattern;

import org.junit.Test;

/**
 * Created by near on 2015/12/11.
 */
public class TestBridgePattern {
    @Test
    public void test(){
        Brand brand = new Dell();
        Computer computer = new DeskTop(brand);
        computer.sale();

        Computer computer2 = new LapTop(new Lenovo());
        computer2.sale();
    }
}
