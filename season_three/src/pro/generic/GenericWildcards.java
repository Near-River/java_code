package pro.generic;

import org.junit.Test;

import java.util.*;
import java.util.Map.Entry;

/**
 * 泛型的通配符：上限和下限的使用
 * Created by Near on 2015/11/29.
 */
public class GenericWildcards {
    @Test
    public void test() {
        // 泛型的下限
        List<? super Integer> list;
        list = new ArrayList<Number>();

        // 泛型的上限
        List<? extends Number> list2;
        list2 = new ArrayList<Integer>();

        // 泛型的嵌套
        Map<String, String> map = new HashMap<String, String>();
        map.put("101", "world");
        map.put("102", "hello");

        Set<Entry<String, String>> set = map.entrySet();
        for(Entry<String, String> entry : set){
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key+"-->"+value);
        }

        /*List []stringList = new ArrayList[10];
        // 泛型没有数组
        List<String> []stringList = new ArrayList<String>[10];*/
    }

    public void put_1(List<? extends A> list){
        // 不可用于数据添加
        /*list.add(new A());
        list.add(new B());*/

        // 可以添加空对象
        list.add(null);
    }

    public void put_2(List<? super B> list){
        // 不可用于添加父类的对象
        // list.add(new A());
        list.add(new B());
    }

    // 泛型不支持多态
    /*public List<A> getList(List<? super B> list){
        return list;
    }*/

    class A{}

    class B extends A{}
}
