package pro.dynamics.java_compiler;

import org.junit.Test;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by near on 2015/12/7.
 */
public class TestJavaCompiler {
    @Test
    public void test() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        String str = "D:/demo/Hello.java";

        String[] sourceFile = {str};

        int result = compiler.run(null, null, null, sourceFile);
        System.out.println(result == 0 ? "Compiler success!!!" : "Compiler fail...");
    }

    // 开新的进程来运行程序
    @Test
    public void test2() {
        BufferedReader bufferedReader = null;
        try {
            Runtime runtime = Runtime.getRuntime();
            // java -cp  +  dir + JavaFile
            Process process = runtime.exec("java -cp D:/demo Hello");
            InputStream inputStream = process.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 使用反射加载已经编译好的类
    @Test
    public void test3() {
        try {
            URL[] urls = new URL[]{new URL("file:/" + "D:/demo/")};
            URLClassLoader loader = new URLClassLoader(urls);

            Class c = loader.loadClass("Hello");
            System.out.println(c.getClass().getName());
            // 调用加载类的 main 方法
            c.getMethod("main", String[].class).invoke(null, (Object) new String[]{});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}