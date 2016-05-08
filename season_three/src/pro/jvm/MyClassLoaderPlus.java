package pro.jvm;

import java.io.*;

/**
 * 自定义加密解密类加载器
 * Created by near on 2015/12/9.
 */
class MyClassLoaderPlus extends ClassLoader {
    private String rootDir = "D:/demo";

    public MyClassLoaderPlus() {
    }

    public MyClassLoaderPlus(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> c = findLoadedClass(name);
        // 判断有没有加载过该类
        if (c != null) {
            return c;
        } else {
            // 采用双亲委托机制
            try {
                c = this.getParent().loadClass(name);
            } catch (ClassNotFoundException e) {
                // ...
            } finally {
                if (c != null) {
                    return c;
                }
            }
            byte[] classDate = new byte[0];
            try {
                classDate = getClassDate(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (classDate == null) {
                throw new ClassNotFoundException();
            } else {
                c = defineClass(name, classDate, 0, classDate.length);
            }
        }
        return c;
    }

    private byte[] getClassDate(String className) throws IOException {
        StringBuilder path = new StringBuilder(rootDir);
        path.append("/").append(className.replace('.', '/')).append(".class");
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(path.toString()));
            byteArrayOutputStream = new ByteArrayOutputStream();

            // 对加密的 class 文件解密
            int temp = -1;
            while ((temp = inputStream.read()) != -1) {
                byteArrayOutputStream.write(temp ^ 0xff);
            }
            byteArrayOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return null;
    }
}
