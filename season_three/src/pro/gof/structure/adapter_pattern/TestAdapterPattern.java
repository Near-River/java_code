package pro.gof.structure.adapter_pattern;

import org.junit.Test;

/**
 * Created by near on 2015/12/11.
 */
public class TestAdapterPattern {
    @Test
    public void test() {
        Out out = new Keyboard();
        USB usb = new Adapter(out);
        Computer computer = new Computer(usb);
        computer.run();
    }
}
