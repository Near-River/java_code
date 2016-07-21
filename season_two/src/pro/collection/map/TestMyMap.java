package pro.collection.map;

import org.junit.Test;

/**
 * Created by Near on 2015/11/28.
 */
public class TestMyMap {
    @Test
    public void test() {
        Class c = null;
        c = MyMapPlus.class;
        MyMapPlus myMapPlus = null;

        try {
            myMapPlus = (MyMapPlus) c.newInstance();

            myMapPlus.put("near", new String("computer"));
            myMapPlus.put("near", new String("game"));

            System.out.println(myMapPlus.get("near"));
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
