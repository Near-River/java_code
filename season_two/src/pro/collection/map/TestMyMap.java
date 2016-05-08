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

            myMapPlus.put("near", new String("game"));
            myMapPlus.put("near", new String("computer"));

            System.out.println(myMapPlus.get("near"));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
