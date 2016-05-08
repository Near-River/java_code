package pro2.sorm.core;

/**
 * 负责根据配置信息创建Query对象
 * 工厂类使用单例模式
 * Created by near on 2015/12/14.
 *
 * @author near
 * @version 2.0
 */
public class QueryFactory {
    private static QueryFactory factory = new QueryFactory();
    private static SimpleQuery query;

    static {
        String db_query = DBManager.getConfigure().getDbType();
        if ("mysql".equals(db_query)) {
            query = new MysqlQueryPlus();
        }
    }

    private QueryFactory() {
    }

    public static QueryFactory getInstance() {
        return factory;
    }

    /**
     * 创建Query对象
     *
     * @return 返回Query对象
     */
    public SimpleQuery createQuery() {
        try {
            return (SimpleQuery) query.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
