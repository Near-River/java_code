package pro2.sorm.util;

import pro2.sorm.bean.ColumnInfo;
import pro2.sorm.bean.FieldGetSetInfo;
import pro2.sorm.bean.TableInfo;
import pro2.sorm.core.DBManager;
import pro2.sorm.core.TypeConvertor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 封装文件操作（生成Java源文件等）
 * Created by near on 2015/12/14.
 *
 * @author near
 * @version 1.0
 */
public class FileUtils {

    /**
     * 根据字段信息和类型转换器生成相应的PO类的属性及其访问方法的源码
     *
     * @param columnInfo    字段信息
     * @param typeConvertor 类型转换器
     * @return 封装了PO类的属性源码信息
     */
    public static FieldGetSetInfo createFieleGetSetInfo(ColumnInfo columnInfo, TypeConvertor typeConvertor) {
        FieldGetSetInfo fieldGetSetInfo = new FieldGetSetInfo();
        String fieldName = columnInfo.getColumnName();
        String fieldType = typeConvertor.dbType2JavaType(columnInfo.getColumnType());

        fieldGetSetInfo.setFieldInfo("private " + fieldType + " " + fieldName + ";");

        StringBuilder setBuilder = new StringBuilder();
        setBuilder.append("public void set").append(StringUtils.upperCaseToFirstChar(fieldName)).
                append("(" + fieldType).append(" " + fieldName + "){ this.").
                append(fieldName + "=" + fieldName + "; }");
        fieldGetSetInfo.setSetterInfo(setBuilder.toString());

        StringBuilder getBuilder = new StringBuilder();
        getBuilder.append("public ").append(fieldType).append(" get").
                append(StringUtils.upperCaseToFirstChar(fieldName)).
                append("(){ return ").append(fieldName + "; }");
        fieldGetSetInfo.setGetterInfo(getBuilder.toString());

        return fieldGetSetInfo;
    }

    /**
     * 根据传入的表信息和类型转换器生成对应的PO对象的源码
     *
     * @param tableInfo     表信息
     * @param typeConvertor 类型转换器
     * @return PO对象的源码信息
     */
    public static String createPoCode(TableInfo tableInfo, TypeConvertor typeConvertor) {
        StringBuilder src = new StringBuilder();

        Map<String, ColumnInfo> map = tableInfo.getColumns();
        List<FieldGetSetInfo> list = new ArrayList<>();

        for (ColumnInfo columnInfo : map.values()) {
            list.add(createFieleGetSetInfo(columnInfo, typeConvertor));
        }

        // 生成package语句
        src.append("package " + DBManager.getConfigure().getPoPackage() + ";\n\n");
        // 生成import语句
        src.append("import java.sql.*;\n");
        src.append("import java.util.*;\n\n");
        // 生成类声明语句
        src.append("public class " + StringUtils.upperCaseToFirstChar(tableInfo.getTableName()) + " {\n");

        // 生成属性列表
        for (FieldGetSetInfo fieldInfo : list) {
            src.append("\t" + fieldInfo.getFieldInfo() + "\n\n");
        }
        // 生成getter方法列表
        for (FieldGetSetInfo fieldInfo : list) {
            src.append("\t" + fieldInfo.getGetterInfo() + "\n\n");
        }
        // 生成setter方法列表
        for (FieldGetSetInfo fieldInfo : list) {
            src.append("\t" + fieldInfo.getSetterInfo() + "\n\n");
        }
        src.append("}");

        return src.toString();
    }

    /**
     * 根据传入的表信息和类型转换器生成对应的PO对象的源文件
     *
     * @param tableInfo     表信息
     * @param typeConvertor 类型转换器
     */
    public static void createPoFile(TableInfo tableInfo, TypeConvertor typeConvertor) {
        String src = createPoCode(tableInfo, typeConvertor);
        BufferedWriter writer = null;

        String path = StringUtils.getPoPath(tableInfo.getTableName());
        File file = new File(path);
        if (file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(src);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(writer);
        }
    }

    /**
     * 文件流的关闭
     *
     * @param io  欲关闭的文件流
     * @param <T> 继承Closeable类的泛型类型
     */
    public static <T extends Closeable> void close(T... io) {
        for (Closeable closeable : io) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}