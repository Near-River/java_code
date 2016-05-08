package pro.annotation.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 使用反射机制处理注解
 * Created by near on 2015/12/7.
 */
public class ReflectDemo {
    public static void main(String[] args) {
        try {
            Class c = Class.forName("pro.annotation.test.Student");

            Annotation[] annotations = c.getAnnotations();
            // Annotation annotation = c.getAnnotation(MyTable.class);
            for (Annotation annotation : annotations) {
                System.out.println(annotation);
            }

            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                System.out.println(field);
                // System.out.println(field.getAnnotation(MyField.class));
                MyField myField = field.getAnnotation(MyField.class);
                if (myField != null) {
                    System.out.println(myField.columnName() + "\t" + myField.type() + "\t" + myField.length());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
