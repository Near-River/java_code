package pro2.sorm.core;

import java.util.List;

/**
 * 负责查询，对外提供核心方法
 * Created by near on 2015/12/14.
 *
 * @author near
 * @version 1.0
 */
public interface Query {
    /**
     * 直接执行一条DML语句
     *
     * @param sql    sql语句
     * @param params sql的注参
     * @return 执行sql语句后受影响记录的行数
     */
    public int executeDML(String sql, Object[] params);

    /**
     * 将一个对象存储到数据库中
     *
     * @param object
     */
    public void insert(Object object);

    /**
     * 根据主键值删除数据库中与对应的类对象结构一致的表中的某行记录
     *
     * @param id    主键值
     * @param clazz 与数据库中具体表结构对应的Class类对象
     * @return 数据库对应的表中受影响记录的行数
     */
    public int delete(int id, Class<?> clazz);

    /**
     * 删除对象在对应的数据库表中的记录
     *
     * @param object 目标对象
     * @return 数据库对应的表中受影响记录的行数
     */
    public int delete(Object object);

    /**
     * 更新对象在对应的数据库表中的记录，并只根据传入的属性字段做相应的修改
     *
     * @param object 目标对象
     * @param fields 对象的(多个)属性字段
     * @return 数据库对应的表中受影响记录的行数
     */
    public int update(Object object, String[] fields);

    /**
     * 查询返回多行记录，并将所得记录封装到clazz指定的类对象中
     *
     * @param sql    查询语句
     * @param clazz  封装数据的bean类的Class对象
     * @param params sql的注参
     * @return 查询结果集
     */
    public List queryRows(String sql, Class<?> clazz, Object[] params);

    /**
     * 查询返回单行记录，并将所得记录封装到clazz指定的类对象中
     *
     * @param sql    查询语句
     * @param clazz  封装数据的bean类的Class对象
     * @param params sql的注参
     * @return 查询结果集
     */
    public Object uniqueQuery(String sql, Class<?> clazz, Object[] params);

    /**
     * 查询数据库表中的某个具体字段值
     *
     * @param sql    查询语句
     * @param params sql的注参
     * @return 查询结果
     */
    public Object queryValue(String sql, Object[] params);

    /**
     * 查询数据库表中满足查询条件的数值
     *
     * @param sql    查询语句
     * @param params sql的注参
     * @return 查询结果
     */
    public Number queryNumber(String sql, Object[] params);
}
