package pro.collection.map;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用 Map 来存放数据记录
 * Created by Near on 2015/11/28.
 */
public class MapData {
    List<Map> list = new ArrayList<Map>();

    @Test
    public void test(){
        Map map1 = new HashMap();
        map1.put("id", 101);
        map1.put("name", "near");
        map1.put("age", 21);

        Map map2 = new HashMap();
        map2.put("id", 102);
        map2.put("name", "kala");
        map2.put("age", 22);

        list.add(map1);
        list.add(map2);

        printInfo(list);
    }

    public void printInfo(List<Map> list){
        for(Map map : list){
            System.out.println(map.get("id")+"-->"+map.get("name")+"-->"+map.get("age"));
        }
    }
}

/**
 * 使用 JavaBean 来对应表结构，使用类对象来存放表记录
 */
class User{
    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User() {
    }

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}