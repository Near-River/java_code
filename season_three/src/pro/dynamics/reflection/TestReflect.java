package pro.dynamics.reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by near on 2015/12/7.
 */
public class TestReflect {
    @Test
    public void test() {
        try {
            Class<?> c = Class.forName("pro.dynamics.reflection.Student");

            System.out.println(c);
            System.out.println(c.getClass());

            Class<?> c1 = "".getClass();
            Class<?> c2 = String.class;
            System.out.println(c1 == c2);

            // 数组的数据类型和数组维度不同，则对应的 Class 对象不同
            int[] arr = new int[10];
            int[] arr2 = new int[20];
            int[][] arr3 = new int[10][];
            // System.out.println(arr.getClass() == arr2.getClass());
            System.out.println(arr.getClass().hashCode() == arr2.getClass().hashCode());
            System.out.println(arr2.getClass().hashCode() == arr3.getClass().hashCode());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            Class<?> c = Class.forName("pro.dynamics.reflection.Student");

            // 获取类的信息
            System.out.println(c.getName());
            System.out.println(c.getSimpleName());

            // 获取属性的信息
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                System.out.println(field.getName());
                System.out.println(field.getType());
            }
            System.out.println(c.getDeclaredField("name"));

            // 获取方法信息
            Method[] methods = c.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println(method.getName());
                System.out.println(method.getReturnType());
            }
            System.out.println(c.getDeclaredMethod("setAge", int.class));

            // 获取构造方法的信息
            Constructor[] constructors = c.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                System.out.println(constructor);
            }
            System.out.println(c.getDeclaredConstructor(String.class, String.class, int.class));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        Student student = null;
        try {
            Class<?> c = Class.forName("pro.dynamics.reflection.Student");
            student = (Student) c.newInstance();
            System.out.println(student);

            // 使用反射调用构造方法
            Constructor constructor = c.getDeclaredConstructor(String.class, String.class, int.class);
            student = (Student) constructor.newInstance("101", "near", 18);
            System.out.println(student);

            // 通过反射调用方法
            Method method = c.getDeclaredMethod("setName", String.class);
            // Object invoke(Object obj, Object... args)
            method.invoke(student, "jack");
            System.out.println(student);

            // 通过反射操作(读写)属性
            Field field = c.getDeclaredField("id");
            // 私有属性不可以直接修改
            // field.set(student, "999");
            // 禁用 Java 语言的安全检查, 允许修改私有属性
            field.setAccessible(true);
            field.set(student, "999");
            System.out.println(field.get(student));
            System.out.println(student);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}