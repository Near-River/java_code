package pro2.sorm.util;

import pro2.sorm.core.DBManager;

/**
 * 封装常用的字符串操作
 * Created by near on 2015/12/14.
 *
 * @author near
 * @version 1.0
 */
public class StringUtils {

    /**
     * 将传入的字符串首字母转为大写
     *
     * @param str 传入的字符串
     * @return 返回的字符串
     */
    public static String upperCaseToFirstChar(String str) {
        return str.toUpperCase().substring(0, 1) + str.substring(1);
    }

    /**
     * 根据传入的表名称，获取对应的PO类源文件所在的文件系统路径
     *
     * @param tableName 表名称
     * @return PO类源文件所在的文件系统路径
     */
    public static String getPoPath(String tableName) {
        StringBuilder path = new StringBuilder();
        path.append(DBManager.getConfigure().getSrcPath()).append("/").
                append(DBManager.getConfigure().getPoPackage().replace('.', '/')).
                append("/").append(upperCaseToFirstChar(tableName)).
                append(".java");

        return path.toString();
    }
}
