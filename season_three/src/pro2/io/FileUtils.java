package pro2.io;

import java.io.*;

/**
 * 文件操作的工具类
 * Created by Near on 2015/12/2.
 */
public class FileUtils {
    public static void copyFile(String srcName, String destName) {
        File file = new File(srcName);
        File newFile = new File(destName);
        copyFile(file, newFile);
    }

    /**
     * 文件拷贝
     *
     * @param srcFile
     * @param destFile
     */
    public static void copyFile(File srcFile, File destFile) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(srcFile);
            outputStream = new FileOutputStream(destFile);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, bytes.length);
            }
            outputStream.flush();
            System.out.println("拷贝成功!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(outputStream, inputStream);
        }
    }

    public static void copyDir(String srcName, String destName) {
        File srcDir = new File(srcName);
        File destDir = new File(destName);
        // 避免目标文件夹的路径是源文件夹的子目录
        if (srcDir.isDirectory()) {
            if (destDir.getAbsolutePath().contains(srcDir.getAbsolutePath())) {
                System.out.println("目标文件夹路径不能为源文件夹的子目录路径");
                return;
            }
        }
        copyDir(srcDir, destDir);
    }

    /**
     * 文件夹拷贝
     *
     * @param srcDir
     * @param destDir
     */
    public static void copyDir(File srcDir, File destDir) {
        if (srcDir.isDirectory()) {
            // 生成目标文件夹
            destDir.mkdirs();
            File[] files = srcDir.listFiles();
            // System.out.println(Arrays.toString(files));
            for (File file : files) {
                copyDir(file, new File(destDir, file.getName()));
            }
        } else if (srcDir.isFile()) {
            copyFile(srcDir, destDir);
        }
    }

    /**
     * 文件关闭
     * 可变参数：... // 只能放在形参的最后一个位置
     */
    public static <T extends Closeable> void close(T... io) {
        for (Closeable closeable : io) {
            try {
                if (closeable != null) {
                    closeable.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
