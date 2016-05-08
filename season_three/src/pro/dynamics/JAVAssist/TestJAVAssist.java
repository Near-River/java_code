package pro.dynamics.JAVAssist;

import javassist.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by near on 2015/12/8.
 */
public class TestJAVAssist {
    private ClassPool classPool = null;
    private CtClass ctClass = null;

    @Before
    public void before() {
        classPool = ClassPool.getDefault();
    }

    /**
     * 使用 JAVAssist 生成一个类的字节码文件(.class 文件)
     */
    @Test
    public void test() {
        ctClass = classPool.makeClass("pro/dynamics/JAVAssist/Student");
        try {
            // 创建属性
            CtField ctField = CtField.make("private String sex;", ctClass);
            ctClass.addField(ctField);

            // 创建方法
            CtMethod ctMethod = CtMethod.make("public void setSex(String arg1){sex = arg1;}", ctClass);
            ctClass.addMethod(ctMethod);

            // 创建构造器
            CtConstructor ctConstructor = new CtConstructor(
                    new CtClass[]{classPool.get("java.lang.String")}, ctClass
            );
            ctConstructor.setBody("{sex = $1;}");
            ctClass.addConstructor(ctConstructor);

            // 将创建好的类写到磁盘目录下
            ctClass.writeFile("D:/IdeaProjects/Java_Code/Season_three/src/");
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理类的基本用法
     * 当 CtClass 对象通过 writeFile()、toClass()、toBytecode()转化为 Class 后，
     * Javassist 冻结了 CtClass 对象，因此，JVM 不允许再次加载 Class 文件
     */
    @Test
    public void test2() {
        try {
            ctClass = classPool.get("pro.dynamics.JAVAssist.Student");
            byte[] bytes = ctClass.toBytecode();
            // 对 CtClass 对象进行解冻
            ctClass.defrost();
            System.out.println(ctClass.getSimpleName());
            System.out.println(ctClass.getPackageName());
            System.out.println(ctClass.getSuperclass());
            System.out.println(ctClass.getInterfaces());

            // 生成新的方法
            // CtMethod ctMethod = CtMethod.make("public void add(int a, int b){return $1+$2;}", ctClass);
            CtMethod ctMethod = new CtMethod(CtClass.intType, "add",
                    new CtClass[]{CtClass.intType, CtClass.intType}, ctClass);
            // 设置访问权限
            ctMethod.setModifiers(Modifier.PUBLIC);
            ctMethod.setBody("{return $1+$2;}");
            ctClass.addMethod(ctMethod);

            // 通过反射生成新方法
            Class c = ctClass.toClass();
            Object object = c.newInstance();
            Method method = c.getDeclaredMethod("add", int.class, int.class);

            Object result = method.invoke(object, 10, 20);
            System.out.println(result);
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        try {
            ctClass = classPool.get("pro.dynamics.JAVAssist.Student");
            CtMethod ctMethod = ctClass.getDeclaredMethod("sayHello");

            ctMethod.insertBefore("System.out.println(\"begin\");");
            ctMethod.insertAfter("System.out.println(\"end\");");
            Class c = ctClass.toClass();
            Object object = c.newInstance();
            Method method = c.getDeclaredMethod("sayHello", null);

            method.invoke(object, null);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {
        try {
            ctClass = classPool.get("pro.dynamics.JAVAssist.Student");
            CtField ctField = new CtField(CtClass.intType, "sex", ctClass);

            ctField.setModifiers(Modifier.PUBLIC);
            ctClass.addField(ctField);

            Class c = ctClass.toClass();
            Object object = c.newInstance();
            Field field = c.getDeclaredField("sex");
            field.set(object, new Integer(21));
            System.out.println(field.get(object));
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5() {
        try {
            ctClass = classPool.get("pro.dynamics.JAVAssist.Student");
            CtConstructor constructor = ctClass.getConstructors()[0];

            constructor.setBody("{ System.out.println(\"hahaha...\"); }");
            constructor.insertBefore("System.out.println(\"begin\");");
            constructor.insertAfter("System.out.println(\"end\");");
            System.out.println(constructor.getLongName());

            Class c = ctClass.toClass();
            Constructor con = c.getDeclaredConstructor();
            Object object = con.newInstance();
            System.out.println((Student) object);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
