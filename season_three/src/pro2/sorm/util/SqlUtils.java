package pro2.sorm.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 封装常用的JDBC操作
 * Created by near on 2015/12/14.
 *
 * @author near
 * @version 1.0
 */
public class SqlUtils {

    /**
     * 封装数据库的相关关闭方法
     *
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
     * 将传入的相关参数依次注入到PreparedStatement对象中
     *
     * @param ps     目标PreparedStatement对象
     * @param params 传入的注入参数
     * @return 注参后的PreparedStatement对象
     * @throws SQLException
     */
    public static PreparedStatement handleParams(PreparedStatement ps, Object[] params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
        }
        return ps;
    }
}
