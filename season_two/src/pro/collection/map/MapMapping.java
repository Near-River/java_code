package pro.collection.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 分拣存储: 解决一对多的关系
 * Created by Near on 2015/11/29.
 */
public class MapMapping {
    @Test
    public void test() {
        Map<String, Letter> map = new HashMap<String, Letter>();
        String[] arr = "this is a cat and that is a mice and where is the food ?".split(" ");

        for (String name : arr) {
            if (!map.containsKey(name)) {
                map.put(name, new Letter(name, 1));
            } else {
                Letter letter = map.get(name);
                letter.setCount(letter.getCount() + 1);
            }
        }

        for (String name : map.keySet()) {
            System.out.println(name + " --> " + map.get(name).getCount());
        }
    }

    class Letter {
        // 单词名称
        private String name;
        // 出现次数
        private int count;

        public Letter() {
        }

        public Letter(String name) {
            this.name = name;
        }

        public Letter(String name, int count) {
            super();
            this.name = name;
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
