package pro.jvm;

import java.io.*;

/**
 * 自定义文件系统类加载器
 * Created by near on 2015/12/9.
 */
class MyClassLoader extends ClassLoader {
    private String rootDir = "D:/demo";

    public MyClassLoader() {
    }

    public MyClassLoader(String rootDir) {
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
            } finally {
                if (c != null) {
                    return c;
                }
            }
            byte[] classDate = null;
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
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                byteArrayOutputStream.write(bytes, 0, len);
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
