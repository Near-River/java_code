package pro2.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by near on 2015/12/14.
 */
public class SqlUtils {
    private static Connection connection = null;
    private static String url = null;
    private static String userName = null;
    private static String password = null;
    private static String driver = null;

    private static Properties properties = null;


    /*
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/near?useUnicode=true&characterEncoding=UTF-8";
    private static String userName = "root";
    private static String password = "123456";
    */

    /**
     * 通过配置文件加载数据库连接信息
     */
    static {
        properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("pro2/jdbc/db.properties"));
            driver = properties.getProperty("jdbc.driver");
            url = properties.getProperty("jdbc.url");
            userName = properties.getProperty("jdbc.username");
            password = properties.getProperty("jdbc.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 封装数据库的连接方法
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            // 加载驱动
            Class.forName(driver);
            // 获得连接(内部使用 Socket 进行通信)
            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 封装关闭方法
     *
     * @param autoCloseables
     */
    public static void close(AutoCloseable... autoCloseables) {
        for (AutoCloseable autoCloseable : autoCloseables) {
            try {
                if (autoCloseable != null) {
                    autoCloseable.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
