package pro.collection.list;

import org.junit.Test;

/**
 * Created by Near on 2015/11/28.
 */
public class TestMyLinkedList {
    @Test
    public void test(){
        Class c = null;
        c = MyLinkedList.class;
        MyLinkedList myLinkedList = null;

        try {
            myLinkedList = (MyLinkedList) c.newInstance();

            myLinkedList.add("haha");
            myLinkedList.add("hehe");
            String str1 = (String) myLinkedList.get(0);

            // myLinkedList.remove(-1);
            // myLinkedList.remove(0);

            myLinkedList.add(1, "wahaha");
            myLinkedList.set(1, "near");

            String str3 = (String) myLinkedList.get(1);

            System.out.println(str1);
            // System.out.println(str2);
            System.out.println(str3);
            System.out.println(myLinkedList.size());

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
