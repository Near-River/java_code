package pro.gof.create.prototype_pattern;

import org.junit.Test;

import java.io.*;
import java.util.Date;

/**
 * Created by near on 2015/12/11.
 */
public class TestPrototypePattern {
    @Test
    public void test(){
        Sheep sheep = new Sheep("喜羊羊", new Date());
        try {
            Sheep sheep2 = (Sheep) sheep.clone();

            System.out.println(sheep == sheep2);
            System.out.println(sheep);
            System.out.println(sheep2);

            sheep.setBirtyday(new Date(1000000L));
            sheep2.setName("美羊羊");
            System.out.println(sheep);
            System.out.println(sheep2);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        Date date = new Date();
        Sheep sheep = new Sheep("喜羊羊", date);
        try {
            Sheep sheep2 = (Sheep) sheep.clone();
            System.out.println(sheep);
            System.out.println(sheep2);

            date.setTime(10000000000L);
            System.out.println(sheep);
            System.out.println(sheep2);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用序列化反序列化的方式实现深克隆
     */
    @Test
    public void test3(){
        Sheep sheep = new Sheep("喜羊羊", new Date());
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        byte[] bytes = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(sheep);

            bytes = byteArrayOutputStream.toByteArray();

            objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
            Sheep sheep2 = (Sheep) objectInputStream.readObject();

            System.out.println(sheep);
            System.out.println(sheep2);
            sheep.setBirtyday(new Date(999999999L));
            sheep2.setName("懒羊羊");
            System.out.println(sheep);
            System.out.println(sheep2);

            objectInputStream.close();
            objectOutputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
