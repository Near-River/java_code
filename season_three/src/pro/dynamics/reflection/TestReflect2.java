package pro.dynamics.reflection;

import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by near on 2015/12/7.
 */
public class TestReflect2 {
    @Test
    public void test() {
        try {
            Class<?> c = TestReflect2.class;

            Method method = c.getDeclaredMethod("fun", Map.class, List.class);
            Type[] types = method.getGenericParameterTypes();

            for (Type type : types) {
                System.out.println(type.getTypeName());
                if (type instanceof ParameterizedType) {
                    Type[] parameterizedTypes = ((ParameterizedType) type).getActualTypeArguments();
                    for (Type parameterizedType : parameterizedTypes) {
                        // 获取泛型类型
                        System.out.println("泛型类型：" + parameterizedType.getTypeName());
                    }
                }
            }

            Method method2 = c.getDeclaredMethod("fun2", null);
            Type returnType = method2.getGenericReturnType();
            System.out.println(returnType.getTypeName());
            if (returnType instanceof ParameterizedType) {
                Type[] parameterizedTypes = ((ParameterizedType) returnType).getActualTypeArguments();
                for (Type parameterizedType : parameterizedTypes) {
                    // 获取泛型类型
                    System.out.println("返回的泛型类型：" + parameterizedType.getTypeName());
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void fun(Map<String, Student> map, List<Student> list) {
        System.out.println("Call Method fun()");
    }

    public Map<String, Student> fun2() {
        System.out.println("Call Method fun2()");
        return null;
    }
}