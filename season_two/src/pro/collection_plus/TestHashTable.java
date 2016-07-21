package pro.collection_plus;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * HashTable：
 * 子类：Properties：只能存储字符串
 * <p>
 * Created by Near on 2015/11/30.
 */
public class TestHashTable {
    @Test
    public void test() {
        Properties properties = new Properties();

        /*
        properties.setProperty("username", "root");
        properties.setProperty("password", "123456");

        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        System.out.println(username+": "+password);
        */

        // 生成资源文件
        /*
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("src/pro/collection_plus/db.properties"));
            try {
                properties.store(fileOutputStream, "数据库配置");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        */

        // 根据当前项目/工程的路径读取资源文件
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("src/pro/collection_plus/db.properties"));
            try {
                properties.load(fileInputStream);

                System.out.println(properties.getProperty("username"));
                System.out.println(properties.getProperty("password"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用类相对路径读取资源文件
     */
    @Test
    public void test2() {
        Properties properties = new Properties();
        try {
            // 当前根路径为 bin, 使用 "/" 表示
            // properties.load(this.getClass().getResourceAsStream("/pro/collection_plus/db.properties"));

            // 当前根路径为 bin, 使用 "" 表示，根据当前线程的上下文类加载器来读取资源文件路径
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("pro/collection_plus/db.properties"));

            System.out.println(properties.getProperty("username"));
            System.out.println(properties.getProperty("password"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

