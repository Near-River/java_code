package pro.gof.action.memento_pattern;

import org.junit.Test;
import pro.gof.action.memento_pattern.memeto.CareTaker;
import pro.gof.action.memento_pattern.memeto.StuMemento;
import pro.gof.action.memento_pattern.memeto.StuOriginator;
import pro.gof.action.memento_pattern.memeto.Student;

/**
 * 观察者模式
 * Created by near on 2015/12/12.
 */
public class Client {
    @Test
    public void test() {
        // 创建备忘录保管者
        CareTaker careTaker = new CareTaker();

        Student student = new Student("near", 101, 99.0);
        System.out.println(student);
        StuOriginator stuOriginator = new StuOriginator(student);

        // 创建备忘录并交由保管者保管
        StuMemento stuMemento = stuOriginator.memento();
        careTaker.setMemento(stuMemento);

        // 修改学生信息
        student.setScore(80.0);
        student.setName("yx");
        System.out.println(student);

        // 恢复学生信息
        student = stuOriginator.recovery(careTaker.getMemento());
        System.out.println(student);
    }
}

