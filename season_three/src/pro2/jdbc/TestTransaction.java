package pro2.jdbc;

import org.junit.Test;

import java.io.*;
import java.sql.*;

/**
 * Created by near on 2015/12/14.
 */
public class TestTransaction {
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    @Test
    public void test() {
        String sql = null;
        try {
            connection = SqlUtils.getConnection();
            connection.setAutoCommit(false);

            // 更新数据
            sql = "update user set name=? where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, "hello");
            preparedStatement.setObject(2, 103);
            preparedStatement.executeUpdate();

            // int i = 1 / 0;

            // 插入数据
            sql = "insert into user values(?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, 102);
            preparedStatement.setObject(2, "near");
            preparedStatement.setObject(3, "123456");
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtils.close(preparedStatement, connection);
        }
    }

    /**
     * 测试大文本数据的存取操作
     */
    @Test
    public void test2() {
        String sql = null;
        try {
            connection = SqlUtils.getConnection();

            // 存入大文本数据
            /*sql = "insert into test values(?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setClob(1, new FileReader("D://demo/Dog.java"));
            preparedStatement.executeUpdate();*/

            // 读取大文本数据
            sql = "select * from test";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            Reader reader = null;
            resultSet.next();
            Clob clob = resultSet.getClob(1);
            reader = clob.getCharacterStream();
            int temp = 0;
            while ((temp = reader.read()) != -1) {
                System.out.print((char) temp);
            }
            reader.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            SqlUtils.close(resultSet, preparedStatement, connection);
        }
    }

    /**
     * 测试二进制数据的存取操作
     */
    @Test
    public void test3() {
        String sql = null;
        try {
            connection = SqlUtils.getConnection();

            // 存入二进制数据
            sql = "insert into test2 values(?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBlob(1, new FileInputStream("D://demo/pic.jpg"));
            preparedStatement.executeUpdate();

            // 读取二进制数据
            sql = "select * from test2";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            InputStream inputStream = null;
            OutputStream outputStream = null;
            byte[] flush = new byte[1024];
            int len = 0;
            resultSet.next();
            Blob blob = resultSet.getBlob(1);

            inputStream = blob.getBinaryStream();
            outputStream = new BufferedOutputStream(new FileOutputStream("D://demo/bg.jpg"));
            while ((len = inputStream.read(flush)) != -1) {
                outputStream.write(flush);
            }
            outputStream.flush();

            outputStream.close();
            inputStream.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            SqlUtils.close(resultSet, preparedStatement, connection);
        }
    }
}
