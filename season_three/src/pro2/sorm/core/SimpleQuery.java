package pro2.sorm.core;

import pro2.sorm.bean.ColumnInfo;
import pro2.sorm.bean.TableInfo;
import pro2.sorm.util.ReflectUtils;
import pro2.sorm.util.SqlUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 负责查询，对外提供核心方法
 * 查询使用模板方法模式
 * Created by near on 2015/12/14.
 *
 * @author near
 * @version 2.0
 */
public abstract class SimpleQuery implements Cloneable {
    static {
        TableContext.loadPOTables();
    }

    /**
     * 直接执行一条DML语句
     *
     * @param sql    sql语句
     * @param params sql的注参
     * @return 执行sql语句后受影响记录的行数
     */
    public int executeDML(String sql, Object[] params) {
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement = SqlUtils.handleParams(preparedStatement, params);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtils.close(preparedStatement, connection);
            return result;
        }
    }

    /**
     * 将一个对象存储到数据库中
     *
     * @param object
     */
    public void insert(Object object) {
        Class clazz = object.getClass();
        TableInfo tableInfo = TableContext.getPoTables().get(clazz);
        Map<String, Object> map = new HashMap<>();
        List<Object> params = new ArrayList<>();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            map.put(fieldName, ReflectUtils.invokeGet(fieldName, object));
        }

        // 拼装sql语句
        StringBuilder sql = new StringBuilder("insert into " + tableInfo.getTableName() + " (");
        int count = 0;
        for (String key : map.keySet()) {
            Object value;
            if ((value = map.get(key)) != null) {
                sql.append(key + ",");
                params.add(value);
                count++;
            }
        }
        sql.setCharAt(sql.length() - 1, ' ');
        sql.append(") values(");
        while (count-- > 0) {
            sql.append("?,");
        }
        sql.setCharAt(sql.length() - 1, ' ');
        sql.append(")");

        executeDML(sql.toString(), params.toArray());
    }

    /**
     * 根据主键值删除数据库中与对应的类对象结构一致的表中的某行记录
     *
     * @param id    主键值
     * @param clazz 与数据库中具体表结构对应的Class类对象
     * @return 数据库对应的表中受影响记录的行数
     */
    public int delete(int id, Class<?> clazz) {
        // 通过Class对象找到对应的表
        TableInfo tableInfo = TableContext.getPoTables().get(clazz);
        // 获取表的主键
        ColumnInfo columnInfo = tableInfo.getKey();
        String sql = "delete from " + tableInfo.getTableName() + " where " + columnInfo.getColumnName() + "=?";
        Object[] params = new Object[]{id};

        return executeDML(sql, params);
    }

    /**
     * 删除对象在对应的数据库表中的记录
     *
     * @param object 目标对象
     * @return 数据库对应的表中受影响记录的行数
     */
    public int delete(Object object) {
        Class clazz = object.getClass();
        TableInfo tableInfo = TableContext.getPoTables().get(clazz);
        // 获取表的主键
        ColumnInfo columnInfo = tableInfo.getKey();
        Object keyValue = null;

        // 通过反射获取对象的主键值
        keyValue = ReflectUtils.invokeField(columnInfo.getColumnName(), object);
        String sql = "delete from " + tableInfo.getTableName() + " where " + columnInfo.getColumnName() + "=?";
        Object[] params = new Object[]{keyValue};

        return executeDML(sql, params);
    }

    /**
     * 更新对象在对应的数据库表中的记录，并只根据传入的属性字段做相应的修改
     *
     * @param object 目标对象
     * @param fields 对象的(多个)属性字段
     * @return 数据库对应的表中受影响记录的行数
     */
    public int update(Object object, String[] fields) {
        Class clazz = object.getClass();
        TableInfo tableInfo = TableContext.getPoTables().get(clazz);
        List<Object> params = new ArrayList<>();

        StringBuilder sql = new StringBuilder("update " + tableInfo.getTableName() + " set ");
        try {
            for (int i = 0; i < fields.length; i++) {
                Field field = clazz.getDeclaredField(fields[i]);
                String fieldName = field.getName();
                Object fieldValue = ReflectUtils.invokeGet(fieldName, object);
                params.add(fieldValue);
                sql.append(fieldName + "=?,");
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        sql.setCharAt(sql.length() - 1, ' ');
        sql.append("where ");
        // 获取表的主键信息
        ColumnInfo columnInfo = tableInfo.getKey();
        String keyName = columnInfo.getColumnName();
        Object keyValue = ReflectUtils.invokeField(keyName, object);
        sql.append(keyName + "=?");
        params.add(keyValue);

        return executeDML(sql.toString(), params.toArray());
    }

    /**
     * 数据库模板查询方法（采用模板方法模式，便于重用）
     *
     * @param callBack 回调函数接口对象
     * @return 查询结果
     */
    public Object executeQueryTemplate(CallBack callBack) {
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        return callBack.doExecute(connection, preparedStatement, resultSet);
    }

    /**
     * 查询返回多行记录，并将所得记录封装到clazz指定的类对象中
     *
     * @param sql    查询语句
     * @param clazz  封装数据的bean类的Class对象
     * @param params sql的注参
     * @return 查询结果集
     */
    public List queryRows(String sql, Class<?> clazz, Object[] params) {
        return (List) executeQueryTemplate(new CallBack() {
            @Override
            public Object doExecute(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
                List list = new ArrayList<>();
                try {
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement = SqlUtils.handleParams(preparedStatement, params);

                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        Object rowObject = clazz.newInstance();
                        Field[] fields = clazz.getDeclaredFields();
                        for (Field field : fields) {
                            Object fieldValue = null;
                            try {
                                fieldValue = resultSet.getObject(field.getName());
                                // 为属性赋值
                                ReflectUtils.invokeSet(field.getName(), field.getType(), fieldValue, rowObject);
                            } catch (Exception e) {
                                // 对于属性值为空的字段，结果集查询不到将抛出异常（不予处理）
                            }
                        }
                        list.add(rowObject);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    SqlUtils.close(resultSet, preparedStatement, connection);
                    return list;
                }
            }
        });
    }

    /**
     * 查询返回单行记录，并将所得记录封装到clazz指定的类对象中
     *
     * @param sql    查询语句
     * @param clazz  封装数据的bean类的Class对象
     * @param params sql的注参
     * @return 查询结果集
     */
    public Object uniqueQuery(String sql, Class<?> clazz, Object[] params) {
        List list = queryRows(sql, clazz, params);

        return (list != null && list.size() > 0 ? list.get(0) : null);
    }

    /**
     * 查询数据库表中的某个具体字段值
     *
     * @param sql    查询语句
     * @param params sql的注参
     * @return 查询结果
     */
    public Object queryValue(String sql, Object[] params) {
        return executeQueryTemplate(new CallBack() {
            @Override
            public Object doExecute(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
                Object result = null;
                try {
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement = SqlUtils.handleParams(preparedStatement, params);

                    resultSet = preparedStatement.executeQuery();
                    resultSet.next();
                    result = resultSet.getObject(1);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    SqlUtils.close(resultSet, preparedStatement, connection);
                    return result;
                }
            }
        });
    }

    /**
     * 查询数据库表中满足查询条件的记录数
     *
     * @param sql    查询语句
     * @param params sql的注参
     * @return 查询结果
     */
    public Number queryNumber(String sql, Object[] params) {
        return (Number) queryValue(sql, params);
    }

    /**
     * 根据PO类的id属性值获取相应的对象
     *
     * @param clazz PO的Class类对象
     * @param id    id属性值
     * @return
     */
    public Object queryById(Class clazz, Object id) {
        TableInfo tableInfo = TableContext.getPoTables().get(clazz);
        String sql = "select * from " + tableInfo.getTableName() + " where id=?";

        return uniqueQuery(sql, clazz, new Object[]{id});
    }

    /**
     * 回调函数接口
     */
    private interface CallBack {
        /**
         * 回调函数，处理查询并返回结果
         *
         * @param connection        数据库连接对象
         * @param preparedStatement 处理预编译的sql语句的Statement对象
         * @param resultSet         结果集对象
         * @return 查询结果
         */
        Object doExecute(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
