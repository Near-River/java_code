package pro2.jdbc;

import org.junit.Test;

import java.sql.*;

/**
 * JDBC 测试
 * Created by near on 2015/12/14.
 */
public class TestCRUD {
    private static Connection connection = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    private static String url = "jdbc:mysql://localhost:3306/near?useUnicode=true&characterEncoding=UTF-8";
    private static String userName = "root";
    private static String password = "123456";

    /**
     * 封装数据库的连接方法
     * @return
     */
    public static Connection getConnection(){
        try {
            // 加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 获得连接(内部使用 Socket 进行通信)
            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 封装关闭方法
     * @param autoCloseables
     */
    public static void close(AutoCloseable... autoCloseables) {
        for (AutoCloseable autoCloseable : autoCloseables) {
            if (autoCloseable != null) {
                try {
                    autoCloseable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 执行 CRUD 操作
     */
    @Test
    public void test() {
        String sql = null;
        try {
            connection = getConnection();

            // 查询数据
            sql = "select * from user";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }

            // 更新数据
            /*sql = "update user set name=? where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, "小明");
            preparedStatement.setObject(2, 102);
            preparedStatement.executeUpdate();*/

            // 插入数据
            /*sql = "insert into user values(?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, 103);
            preparedStatement.setObject(2, "near");
            preparedStatement.setObject(3, "123456");
            preparedStatement.executeUpdate();*/


            // 删除数据
            /*sql = "delete from user where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, 102);
            preparedStatement.executeUpdate();*/
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }

    /**
     * 执行批处理
     */
    @Test
    public void test2() {
        String sql = null;
        try {
            connection = getConnection();
            // 设置非自动提交
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            sql = "insert into test values(\"hahaha\")";
            for(int i=0; i<20000; i++){
                statement.addBatch(sql);
            }
            // 执行批处理
            statement.executeBatch();
            // 提交事务
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement, connection);
        }
    }
}
