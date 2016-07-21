package pro.collection;

import org.junit.Test;

import java.util.*;

/**
 * Created by Near on 2015/11/28.
 */
public class TestCollection {
    @Test
    public void testList() {
        List list1 = new ArrayList();
        List list2 = new ArrayList();

        // 测试一
        /*list1.add("haha");
        list1.add(null);
        list2.add(123);
        list2.add(new Date());
        list1.add(list2);

        System.out.println(list1.size());
        System.out.println(list1.get(2));*/

        // 测试二
        list1.add(new Date());
        list1.remove(new Date());

        System.out.println(list1.size());
    }

    @Test
    public void testMap() {
        Map map = new HashMap();

        map.put(new Integer(101), "haha");
        map.put(new Integer(102), new Dog("dog"));
        map.put(new Integer(103), "xixi");

        // map.remove(102);
        Dog dog = (Dog) map.get(102);
        map.remove(102);
        System.out.println(dog.name);

        Set keySet = map.keySet();
        Iterator iterator = keySet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    class Dog {
        String name;

        public Dog(String name) {
            this.name = name;
        }
    }

    @Test
    public void testSet() {
        Set set = new HashSet();
        set.add("haha");
        set.add("xixi");
        set.add("gaga");
        set.add("hehe");
        set.add(new String("xixi"));

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "-->");
        }
        System.out.println("" + '\n' + set.size());
        System.out.println(set.contains("haha"));
    }
}
