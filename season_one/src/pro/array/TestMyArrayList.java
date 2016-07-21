package pro.array;

import org.junit.Test;

/**
 * Created by Near on 2015/11/24.
 */
public class TestMyArrayList {
    @Test
    public void test() {
        Class c = null;
        MyArrayList myArrayList = null;

        c = MyArrayList.class;

        try {
            myArrayList = (MyArrayList) c.newInstance();
            myArrayList.add("haha");
            myArrayList.add(123);
            myArrayList.add(null);
          /*  myArrayList.add(43);
            myArrayList.add(13);
            myArrayList.add(53);*/

            myArrayList.remove(1);

            myArrayList.set(1, 456);

            System.out.println(myArrayList.get(0));
            System.out.println(myArrayList.get(1));
            // System.out.println(myArrayList.get(-1));

            System.out.println(myArrayList.length());

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
