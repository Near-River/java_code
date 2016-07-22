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
 * 负责针对Mysql数据库的查询，对外提供核心方法
 * Created by near on 2015/12/14.
 *
 * @author near
 * @version 1.0
 */
public class MysqlQuery implements Query {
    static {
        TableContext.loadPOTables();
    }

    @Override
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

    @Override
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

    @Override
    public int delete(int id, Class<?> clazz) {
        // 通过Class对象找到对应的表
        TableInfo tableInfo = TableContext.getPoTables().get(clazz);
        // 获取表的主键
        ColumnInfo columnInfo = tableInfo.getKey();
        String sql = "delete from " + tableInfo.getTableName() + " where " + columnInfo.getColumnName() + "=?";
        Object[] params = new Object[]{id};

        return executeDML(sql, params);
    }

    @Override
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

    @Override
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

    @Override
    public List queryRows(String sql, Class<?> clazz, Object[] params) {
        List list = new ArrayList<>();
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement = SqlUtils.handleParams(preparedStatement, params);

            resultSet = preparedStatement.executeQuery();
            // 方法一
            while (resultSet.next()) {
                Object rowObject = clazz.newInstance();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    Object fieldValue;
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

            // 方法二
            /*ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()) {
                Object rowObject = clazz.newInstance();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    String columnName = metaData.getColumnLabel(i + 1);
                    Object columnValue = resultSet.getObject(i + 1);

                    ReflectUtils.invokeSet2(columnName, columnValue, rowObject);
                }
                list.add(rowObject);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlUtils.close(resultSet, preparedStatement, connection);
            return list;
        }
    }

    @Override
    public Object uniqueQuery(String sql, Class<?> clazz, Object[] params) {
        List list = queryRows(sql, clazz, params);

        return (list != null && list.size() > 0 ? list.get(0) : null);
    }

    @Override
    public Object queryValue(String sql, Object[] params) {
        Object result = null;
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
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

    @Override
    public Number queryNumber(String sql, Object[] params) {
        return (Number) queryValue(sql, params);
    }
}
