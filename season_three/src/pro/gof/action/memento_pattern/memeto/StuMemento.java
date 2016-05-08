package pro.gof.action.memento_pattern.memeto;

/**
 * 创建学生备忘录类
 */
public class StuMemento{
    private Student student;

    // 只提供给源发器对象来访问备忘录
    protected Student getStudent() {
        return student;
    }

    public StuMemento(Student student) {
        this.student = new Student(student.getName(), student.getId(), student.getScore());
    }
}
