package pro2.sorm.core;

/**
 * 负责Mysql数据库类型和 java 类型之间的转化
 * Created by near on 2015/12/15.
 *
 * @author near
 * @version 1.0
 */
public class MysqlTypeConvertor implements TypeConvertor {

    @Override
    public String dbType2JavaType(String columnType) {
        if ("varchar".equalsIgnoreCase(columnType)) {
            return "String";
        } else if ("int".equalsIgnoreCase(columnType)
                || "tinyint".equalsIgnoreCase(columnType)
                || "smallint".equalsIgnoreCase(columnType)
                || "integer".equalsIgnoreCase(columnType)) {
            return "Integer";
        } else if ("bigint".equalsIgnoreCase(columnType)) {
            return "Long";
        } else if ("double".equalsIgnoreCase(columnType)
                || "float".equalsIgnoreCase(columnType)) {
            return "Double";
        } else if ("clob".equalsIgnoreCase(columnType)) {
            return "java.sql.CLob";
        } else if ("blob".equalsIgnoreCase(columnType)) {
            return "java.sql.BLob";
        } else if ("datetime".equalsIgnoreCase(columnType)) {
            return "java.util.Date";
        } else if ("date".equalsIgnoreCase(columnType)) {
            return "java.sql.Date";
        } else if ("time".equalsIgnoreCase(columnType)) {
            return "java.sql.Time";
        } else if ("timestamp".equalsIgnoreCase(columnType)) {
            return "java.sql.Timestamp";
        }
        return null;
    }

    @Override
    public String javaType2DbType(String columnType) {
        return null;
    }
}
