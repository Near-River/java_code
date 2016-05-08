package pro.gof.structure.composite_pattern;

import org.junit.Test;

/**
 * Created by near on 2015/12/11.
 */
public class TestCompositePattern {
    @Test
    public void test(){
        Folder dir, dir2;
        AbstractFile file1, file2, file3, file4;
        dir = new Folder("我的收藏");
        dir2 = new Folder("我的音乐");
        file1 = new ImageFile("a.jpg");
        file2 = new ImageFile("b.png");
        file3 = new TextFile("c.txt");
        file4 = new TextFile("d.java");

        dir.getList().add(file3);
        dir.getList().add(file4);
        dir.getList().add(dir2);
        dir2.getList().add(file1);
        dir2.getList().add(file2);
        dir.killVirus();
    }
}
