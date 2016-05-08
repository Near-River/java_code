package pro.gof.structure.composite_pattern;

/**
 * 典型应用：Junit 单元测试框架
 * TestCase（叶子构件）
 * TestUnite（容器构建）
 * Test 接口（抽象构件）
 * Created by near on 2015/12/12.
 */
// 抽象构件
public interface Component {
    void operation();
}

// 叶子构件
interface Leaf extends Component {

}

// 容器构建
interface  Composite extends Component {
    void add(Composite composite);
    void remove(Composite composite);
    Component getChild(int index);
}