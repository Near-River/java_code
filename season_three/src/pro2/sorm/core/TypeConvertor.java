package pro2.sorm.core;

/**
 * 负责类型转换（数据库类型和 java 类型之间的转化）
 * Created by near on 2015/12/14.
 *
 * @author near
 * @version 1.0
 */
public interface TypeConvertor {
    /**
     * 将数据库数据类型转换为Java数据类型
     *
     * @param typeName 数据库数据类型
     * @return Java数据类型
     */
    public String dbType2JavaType(String typeName);

    /**
     * 将Java数据类型转换为数据库数据类型
     *
     * @param typeName Java数据类型
     * @return 数据库数据类型
     */
    public String javaType2DbType(String typeName);
}
