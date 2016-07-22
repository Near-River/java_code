package pro2.sorm.core;

import pro2.sorm.bean.ColumnInfo;
import pro2.sorm.bean.TableInfo;
import pro2.sorm.util.StringUtils;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 负责获取和管理数据库所有表结构和类结构的关系，并可以根据表结构生成类结构
 * Created by near on 2015/12/14.
 *
 * @author near
 * @version 1.0
 */
public class TableContext {
    /**
     * 键对应表名，值对应表信息
     */
    private static Map<String, TableInfo> tables = new HashMap<>();

    /**
     * 将PO的Class对象和表信息对象关联起来，便于重用
     */
    private static Map<Class, TableInfo> poTables = new HashMap<>();

    public static Map<String, TableInfo> getTables() {
        return tables;
    }

    public static Map<Class, TableInfo> getPoTables() {
        return poTables;
    }

    private TableContext() {
    }

    static {
        try {
            tables.clear();
            // 初始化获得表的信息
            Connection connection = DBManager.getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();

            /*
            ResultSet getTables(String catalog, String schemaPattern,
                        String tableNamePattern, String types[]) throws SQLException;
             */
            ResultSet tableRet = databaseMetaData.getTables(null, "%", "%", new String[]{"TABLE"});

            while (tableRet.next()) {
                String tableName = (String) tableRet.getObject("TABLE_NAME");

                TableInfo tableInfo = new TableInfo(tableName, new ArrayList<>(), new HashMap<>());

                /*
                查询表中的所有字段
                ResultSet getColumns(String catalog, String schemaPattern,
                         String tableNamePattern, String columnNamePattern)
                 */
                ResultSet columnRet = databaseMetaData.getColumns(null, "%", tableName, "%");
                while (columnRet.next()) {
                    ColumnInfo columnInfo = new ColumnInfo(columnRet.getString("COLUMN_NAME"),
                            columnRet.getString("TYPE_NAME"), 0);
                    tableInfo.getColumns().put(columnRet.getString("COLUMN_NAME"), columnInfo);
                }

                /*
                查询表的主键
                ResultSet getPrimaryKeys(String catalog, String schema,
                             String table) throws SQLException;
                 */
                ResultSet keyRet = databaseMetaData.getPrimaryKeys(null, "%", tableName);
                while (keyRet.next()) {
                    ColumnInfo columnInfo = tableInfo.getColumns().get(keyRet.getObject("COLUMN_NAME"));
                    // 设置列类型为主键
                    columnInfo.setKeyType(1);
                    tableInfo.getJoinKeys().add(columnInfo);
                }
                // 取唯一主键
                if (tableInfo.getJoinKeys().size() > 0) {
                    tableInfo.setKey(tableInfo.getJoinKeys().get(0));
                }

                tables.put(tableName, tableInfo);


                /*
                // 生成PO包下的所有PO类
                FileUtils.createPoFile(tableInfo, new MysqlTypeConvertor());
                */
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载PO包下面的所有类
     */
    public static void loadPOTables() {
        poTables.clear();
        for (TableInfo tableInfo : tables.values()) {
            String poName = DBManager.getConfigure().getPoPackage() + "." +
                    StringUtils.upperCaseToFirstChar(tableInfo.getTableName());

            String path = StringUtils.getPoPath(tableInfo.getTableName());
            File file = new File(path);

            if (file.exists()) {
                try {
                    Class clazz = Class.forName(poName);
                    poTables.put(clazz, tableInfo);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
