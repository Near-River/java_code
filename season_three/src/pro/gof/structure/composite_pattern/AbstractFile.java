package pro.gof.structure.composite_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟杀毒软件
 * Created by near on 2015/12/12.
 */
// 抽象构件
public interface AbstractFile {
    // 杀毒
    void killVirus();
}

// 叶子构件
class ImageFile implements AbstractFile {
    private String fileName;

    public ImageFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void killVirus() {
        System.out.println("查杀图像文件：" + fileName);
    }
}

// 叶子构件
class TextFile implements AbstractFile {
    private String fileName;

    public TextFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void killVirus() {
        System.out.println("查杀文本文件：" + fileName);
    }
}

// 容器构建
class Folder implements AbstractFile {
    private String dirName;
    private List<AbstractFile> list = new ArrayList<>();

    public Folder(String dirName) {
        this.dirName = dirName;
    }

    public List<AbstractFile> getList() {
        return list;
    }

    public void setList(List<AbstractFile> list) {
        this.list = list;
    }

    @Override
    public void killVirus() {
        System.out.println("查杀文件夹：" + dirName);
        for(AbstractFile file : list){
            file.killVirus();
        }
    }

    void add(AbstractFile file) {
        list.add(file);
    }

    void remove(AbstractFile file) {
        list.remove(file);
    }

    AbstractFile getChild(int index) {
        return list.get(index);
    }
}