package pro2.io.stream;

import org.junit.Test;
import pro2.io.Employee;
import pro2.io.FileUtils;

import java.io.*;
import java.util.Arrays;

/**
 * 引用类型处理流:
 * ObjectInputStream readXxx() / ObjectInputStream writeXxx()
 * 输入流（反序列化）    输出流（序列化）
 * transient 使标记的属性不被序列化
 * <p>
 * Created by Near on 2015/12/3.
 */
public class TestIO5 {
    @Test
    public void testWrite() {
        // 获取文件
        File file = new File("E:/Books/a.txt");
        // 选择写入文件流
        ObjectOutputStream objectOutputStream = null;
        Employee employee = new Employee("杨萧", 10000.00);
        String[] strings = {"杨萧", "小明"};
        try {
            objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            // 将对象序列化存储
            objectOutputStream.writeObject(employee);
            objectOutputStream.writeObject(strings);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.close(objectOutputStream);
        }
    }

    @Test
    public void testRead() {
        // 获取文件
        File file = new File("E:/Books/a.txt");
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
            Employee employee = null;
            Object object = objectInputStream.readObject();
            String[] strings = (String[]) objectInputStream.readObject();
            if (object instanceof Employee) {
                employee = (Employee) object;
            }
            assert employee != null;
            System.out.println(employee.toString());
            System.out.println(Arrays.toString(strings));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            FileUtils.close(objectInputStream);
        }
    }
}