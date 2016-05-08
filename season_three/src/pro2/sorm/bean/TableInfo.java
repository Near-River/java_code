package pro2.sorm.bean;

import java.util.List;
import java.util.Map;

/**
 * 封装一张表的信息
 * Created by near on 2015/12/14.
 *
 * @author near
 * @version 1.0
 */
public class TableInfo {
    /**
     * 表的名称
     */
    private String tableName;

    /**
     * 唯一主键（有且唯一，暂不支持联合主键）
     */
    private ColumnInfo key;

    /**
     * 联合主键（留作以后扩展）
     */
    private List<ColumnInfo> joinKeys;

    /**
     * 所有字段的信息
     */
    private Map<String, ColumnInfo> columns;

    public TableInfo() {
    }

    public TableInfo(String tableName, ColumnInfo key, Map<String, ColumnInfo> columns) {
        this.tableName = tableName;
        this.key = key;
        this.columns = columns;
    }

    public TableInfo(String tableName, List<ColumnInfo> joinKeys, Map<String, ColumnInfo> columns) {
        this.tableName = tableName;
        this.joinKeys = joinKeys;
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ColumnInfo getKey() {
        return key;
    }

    public void setKey(ColumnInfo key) {
        this.key = key;
    }

    public Map<String, ColumnInfo> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, ColumnInfo> columns) {
        this.columns = columns;
    }

    public List<ColumnInfo> getJoinKeys() {
        return joinKeys;
    }

    public void setJoinKeys(List<ColumnInfo> joinKeys) {
        this.joinKeys = joinKeys;
    }
}
