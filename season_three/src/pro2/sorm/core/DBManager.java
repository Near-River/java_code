package pro2.sorm.core;

import pro2.sorm.bean.Configuration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * 根据配置信息，维持连接对象的管理（增加连接池功能）
 * Created by near on 2015/12/14.
 *
 * @author near
 * @version 2.0
 */
public class DBManager {

    private static Configuration configure;
    private static Connection connection;
    // private static DBConnPool pool;

    /**
     * 通过配置文件加载数据库驱动和相关连接信息
     * 并获取所使用的数据库类型、项目的源码路径以及扫描生成PO对象的包
     */
    static {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().
                    getResourceAsStream("pro2/test_orm/db.properties"));

            configure = new Configuration();
            configure.setDriver(properties.getProperty("jdbc.driver"));
            configure.setUrl(properties.getProperty("jdbc.url"));
            configure.setUserName(properties.getProperty("jdbc.username"));
            configure.setPassword(properties.getProperty("jdbc.password"));
            configure.setDbType(properties.getProperty("dbType"));
            configure.setSrcPath(properties.getProperty("srcPath"));
            configure.setPoPackage(properties.getProperty("poPackage"));
            configure.setPoolMaxSize(properties.getProperty("poolMaxSize"));
            configure.setPoolMinSize(properties.getProperty("poolMinSize"));

        } catch (IOException e) {
            System.out.println("资源文件加载发生异常");
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库的连接
     *
     * @return 返回数据库的连接对象
     */
    public static Connection getConnection() {
        try {
            // 加载驱动
            Class.forName(configure.getDriver());
            // 获得连接
            connection = DriverManager.getConnection(configure.getUrl(),
                    configure.getUserName(), configure.getPassword());
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回配置信息对象
     *
     * @return 配置信息对象
     */
    public static Configuration getConfigure() {
        return configure;
    }
}
