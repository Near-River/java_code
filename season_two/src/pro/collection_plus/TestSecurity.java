package pro.collection_plus;

import org.junit.Test;

import java.util.*;

/**
 * 做同步控制与只读设置
 * Created by Near on 2015/11/30.
 */
public class TestSecurity {
    @Test
    public void test() {
        List<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        // 将 ArrayList 对象包装成线程安全的 ArrayList 对象
        List<String> synList = Collections.synchronizedList(list);

        Map<String, String> map = new HashMap<String, String>();
        map.put("101", "haha");
        map.put("102", "haha");
        map.put("103", "haha");
        // 将 map2 设置为只读对象
        Map<String, String> map2 = Collections.unmodifiableMap(map);
        // map2.put("103", "haha");
        System.out.println(map2.size());


        // 只可存放一个元素的容器
        List<String> list2 = Collections.singletonList(new String("haha"));
        // list2.add("xixi");
        System.out.println(list2.size());

        Set<String> set = null;
        System.out.println(oper(set));
    }

    private static Set<String> oper(Set<String> set) {
        // 避免空指针异常的发生
        if (set == null) {
            return Collections.EMPTY_SET;
        }
        // ...
        return set;
    }
}
