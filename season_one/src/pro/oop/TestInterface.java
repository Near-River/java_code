package pro.oop;

import org.junit.Test;

/**
 * Created by Near on 2015/11/25.
 */
public class TestInterface {
    @Test
    public void test() {
        int height = Ifly.Height;
        System.out.println(Ifly.Height);
        System.out.println(height);
    }
}

interface Ifly {
    int Height = 10;

    void fly();
}