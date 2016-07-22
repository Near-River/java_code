package pro2.sorm.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 封装常用的反射操作
 * Created by near on 2015/12/14.
 *
 * @author near
 * @version 1.0
 */
public class ReflectUtils {

    /**
     * 根据目标对象以及主键的字段名称来获取响应主键的字段值
     *
     * @param fieldName 目标对象的主键的字段名称
     * @param object    目标对象
     * @return 目标对象的主键的字段值
     */
    public static Object invokeGet(String fieldName, Object object) {
        Object keyValue = null;
        Class clazz = null;
        Method method = null;
        try {
            clazz = object.getClass();
            method = clazz.getDeclaredMethod("get" + StringUtils.upperCaseToFirstChar(fieldName), null);
            keyValue = method.invoke(object, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return keyValue;
        }
    }

    /**
     * 根据目标对象以及主键的字段名称来获取响应主键的字段值
     *
     * @param fieldName 目标对象的主键的字段名称
     * @param object    目标对象
     * @return 目标对象的主键的字段值
     */
    public static Object invokeField(String fieldName, Object object) {
        Object keyValue = null;
        Class clazz = null;
        Field field = null;
        try {
            clazz = object.getClass();
            field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            keyValue = field.get(object);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            return keyValue;
        }
    }

    /**
     * 根据传入的字段名称、字段类型、字段值以及目标对象本身为目标对象赋值
     *
     * @param fieldName  字段名称
     * @param fieldType  字段类型
     * @param fieldValue 字段值
     * @param object     目标对象
     */
    public static void invokeSet(String fieldName, Class fieldType, Object fieldValue, Object object) {
        Class clazz = null;
        Method method = null;
        try {
            clazz = object.getClass();
            method = clazz.getDeclaredMethod("set" + StringUtils.upperCaseToFirstChar(fieldName), fieldType);

            method.invoke(object, fieldValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据传入的字段名称、字段值以及目标对象本身为目标对象的属性赋值
     *
     * @param fieldName  字段名称
     * @param fieldValue 字段值
     * @param object     目标对象
     */
    @Deprecated // fieldValue.getClass() 对于日期类型的获取有问题
    public static void invokeSet2(String fieldName, Object fieldValue, Object object) {
        Class clazz = null;
        Method method = null;
        // System.out.println(fieldValue.getClass());
        try {
            clazz = object.getClass();
            method = clazz.getDeclaredMethod("set" + StringUtils.upperCaseToFirstChar(fieldName), fieldValue.getClass());

            method.invoke(object, fieldValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
