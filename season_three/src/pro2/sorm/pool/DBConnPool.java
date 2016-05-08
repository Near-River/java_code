package pro2.sorm.pool;

import pro2.jdbc.SqlUtils;
import pro2.sorm.core.DBManager;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


/**
 * 连接池的类
 * Created by near on 2015/12/14.
 *
 * @author near
 * @version 2.0
 */
public class DBConnPool {
    /**
     * 连接池
     */
    private static List<Connection> pool;

    /**
     * 最大连接数
     */
    private static final int POOL_MAX_SIZE = Integer.parseInt(DBManager.getConfigure().getPoolMaxSize());

    /**
     * 最小连接数
     */
    private static final int POOL_MIN_SIZE = Integer.parseInt(DBManager.getConfigure().getPoolMinSize());

    public DBConnPool() {
        init();
    }

    /**
     * 初始化连接池
     */
    public void init() {
        if (pool == null) {
            pool = new ArrayList<>();
        }
        while (pool.size() < POOL_MIN_SIZE) {
            pool.add(DBManager.getConnection());
        }
    }

    public synchronized Connection getConnection() {
        if (pool.size() == 0) {
            init();
        }
        int last_index = pool.size() - 1;
        Connection connection = pool.get(last_index);
        pool.remove(last_index);
        return connection;
    }

    public synchronized void closeConnection(Connection connection) {
        if (pool.size() >= POOL_MAX_SIZE) {
            SqlUtils.close(connection);
        }
        // 将数据库连接对象放回连接池
        pool.add(connection);
    }
}
