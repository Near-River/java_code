package pro.sort;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * TreeSet 在数据添加过程中实现排序
 * -- 在使用过程中不要修改数据,不然会造成数据的不一致
 * TreeMap 的使用方法和 TreeSet 类似
 * Created by Near on 2015/11/30.
 */
public class TestTreeSet {
    @Test
    public void test() {
        // 调用 TreeSet 的构造方法 public TreeSet(Comparator<? super E> comparator) {}
        TreeSet<Person> treeSet = new TreeSet<Person>(
                new Comparator<Person>() {
                    /**
                     * 按照年龄由大到小进行排序
                     * @param o1
                     * @param o2
                     * @return
                     */
                    @Override
                    public int compare(Person o1, Person o2) {
                        return o2.getAge() - o1.getAge();
                    }
                }
        );

        Person person = new Person("小刚", 16);
        treeSet.add(person);
        treeSet.add(new Person("小明", 21));
        treeSet.add(new Person("小红", 15));

        Iterator<Person> iterator = treeSet.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        /*person.setAge(21);

        iterator = treeSet.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }*/
    }
}

class Person {
    private String name;
    private int age;

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

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}


