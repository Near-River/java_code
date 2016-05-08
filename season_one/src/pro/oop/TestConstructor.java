package pro.oop;

import org.junit.Test;

/**
 * Created by Near on 2015/11/26.
 */
public class TestConstructor {
    @Test
    public void test() {
        Class clazz = null;
        User user = null;
        clazz = User.class;

        try {
            user = (User) clazz.newInstance();
            System.out.println(user.toString());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

class Person {
    public Person() {
        System.out.println("Person Constructor");
    }

    @Override
    public String toString() {
        System.out.println(this.getClass().getName());

        System.out.println(super.getClass().getName());
        return "Person{}";
    }
}

class User extends Person {
    private String userName;
    private Integer age;

    public User() {
        System.out.println("User Constructor");
    }

    public User(String userName) {
        this.userName = userName;
    }

    public User(String userName, Integer age) {
        this(userName);
        this.age = age;
    }

    @Override
    public String toString() {
        System.out.println(super.toString());

        System.out.println(this.getClass().getName());
        System.out.println(this.hashCode());
        System.out.println(super.getClass().getName());
        System.out.println(super.hashCode());

        return "User{}";
    }

}
