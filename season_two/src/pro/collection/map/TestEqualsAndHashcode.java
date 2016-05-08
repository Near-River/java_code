package pro.collection.map;

import org.junit.Test;

/**
 * Created by Near on 2015/11/28.
 */
public class TestEqualsAndHashcode {
    @Test
    public void test() {
        Person person1 = new Person();
        Person person2 = new Person(1, "near", 21);

        System.out.println(person1.equals(person2));
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());

    }
}

class Person{
    // 可以将具有唯一性质的属性作为 hashcode 的主要参照
    private int id;
    private String name;
    private int age;

    public Person() {
    }

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode() {
        // 定义一个素数
        final int prime = 31;
        int result = 1;
        return prime*result + id;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(this.getClass() != obj.getClass()){
            return false;
        }
        Person person = (Person) obj;
        if(id != person.id){
            return false;
        }
        return true;
    }
}