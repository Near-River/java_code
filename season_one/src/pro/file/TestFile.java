package pro.file;

import org.junit.Test;

import java.io.File;

/**
 * Created by Near on 2015/11/26.
 */
public class TestFile {
    @Test
    public void test() {
        File f = new File("D:\\demo\\Image");

        System.out.println(f.getName());

        // 创建新文件
        /*File newFile = new File("E:\\pictures\\hahaha.txt");
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        // 遍历文件目录下面的所有文件
        String[] fileNames = f.list();
        for (String fileName : fileNames) {
            // System.out.println(fileName);
        }

        File testFile = new File("D:\\util\\课件\\物联网");
        filesTree(testFile, 0);
    }

    /**
     * 递归列出目录或文件路径下的所有文件
     *
     * @param file
     */
    public void filesTree(File file, int level) {
        File temp = null;

        for (int i = 0; i < level; i++) {
            System.out.print("——");
        }

        if (file.isFile()) {
            System.out.println(file.getName());
        } else {
            System.out.println(file.getName());
            File[] files = file.listFiles();
            for (File f : files) {
                filesTree(f, level + 1);
            }
        }
    }
}
