package pro2.sorm.bean;

/**
 * 封装表中一个字段的信息
 * Created by near on 2015/12/14.
 *
 * @author near
 * @version 1.0
 */
public class ColumnInfo {
    /**
     * 列的名称
     */
    private String columnName;

    /**
     * 列的类型
     */
    private String columnType;

    /**
     * 列的键类型（无、主键、外键）
     */
    private int keyType;

    public ColumnInfo() {
    }

    public ColumnInfo(String columnName, String columnType, int keyType) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.keyType = keyType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public int getKeyType() {
        return keyType;
    }

    public void setKeyType(int keyType) {
        this.keyType = keyType;
    }
}
