package pro.gof.action.memento_pattern.memeto;

/**
 * 创建学生的源发器类
 */
public class StuOriginator{
    private Student student;

    public StuOriginator(Student student) {
        this.student = student;
    }

    // 创建一个备忘录，并记录当前时刻它的内部状态
    public StuMemento memento(){
        return new StuMemento(student);
    }

    // 使用备忘录恢复内部状态
    public Student recovery(StuMemento stuMemento){
        return stuMemento.getStudent();
    }
}
