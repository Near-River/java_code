package pro.generic;

import org.junit.Test;

/**
 * Created by Near on 2015/11/29.
 */
public class TestGeneric {
    @Test
    public void test() {
        // 泛型类型不可以是基本类型
        // Student<int, String> student = new Student<int, String>();

        Student<Integer, String> student = new Student<Integer, String>();

        User user = new User();
        System.out.println(user.fun("haha"));
        System.out.println(user.fun2(new Student<Integer, String>()));

        user.fun3("haha", 123, 13.23, new User());
    }
}

/**
 * 自定义泛型类(泛型不可直接作用于静态属性和方法)
 *
 * @param <E>
 * @param <T>
 */
class Student<E, T> {
    private E id;
    private T name;

    public void setId(E id) {
        this.id = id;
    }

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public Student() {
    }

    public Student(E id, T name) {
        this.id = id;
        this.name = name;
    }

    public E getId() {
        return id;
    }

    // private static E age;

    /*public static E fun(){
        return new E();
    }*/
}

class User<E> {
    public static <E> E fun(E e) {
        return e;
    }

    public E fun2(E e) {
        return e;
    }

    public void fun3(E... e) {
        for (E e1 : e) {
            System.out.println(e1.toString());
            System.out.println(e1.getClass().getName());
        }
    }
}