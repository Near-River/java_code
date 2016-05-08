package pro.gof.action.memento_pattern.memeto;

/**
 * 创建负责人类
 */
public class CareTaker{
    private StuMemento memento;

    public StuMemento getMemento() {
        return memento;
    }

    public void setMemento(StuMemento memento) {
        this.memento = memento;
    }
}

