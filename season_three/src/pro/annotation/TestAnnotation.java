package pro.annotation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by near on 2015/12/7.
 */
public class TestAnnotation {
    @Test
    public void test() {
        Date date = new Date();
        date.toLocaleString();

        demo();
        demo2();
    }

    @MyAnnotation()
    public void testMyAnnotation() {
    }

    @Override
    public String toString() {
        return "TestAnnotation{}";
    }

    @Deprecated
    private void demo() {
    }

    @SuppressWarnings(value = {"deprecation", "unchecked"})
    private void demo2() {
        List list = new ArrayList();

        Date date = new Date();
        date.toLocaleString();

        demo();
    }
}