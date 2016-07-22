package pro.gof.action.observer_pattern.test;

import org.junit.Test;

import java.util.Observable;
import java.util.Observer;

/**
 * JDK 自带的观察者模式实现
 * Created by near on 2015/12/12.
 */
public class TestObserverPattern {
    @Test
    public void test() {
        MySubject mySubject = new MySubject();
        Observer observer = new MyObserver();
        Observer observer2 = new MyObserver();
        mySubject.addObserver(observer);
        mySubject.addObserver(observer2);

        mySubject.setState(State.UP);
        mySubject.setState(State.RIGHT);

        mySubject.deleteObserver(observer);
        mySubject.setState(State.DOWN);
    }
}

class MySubject extends Observable {
    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        // 表示目标对象已经做了更改
        setChanged();
        // 通知所有的观察者
        notifyObservers(state);
    }
}

class MyObserver implements Observer {
    private State state;

    @Override
    public void update(Observable o, Object arg) {
        // state = ((MySubject) o).getState();
        // 更新状态
        state = (State) arg;
        System.out.println(state);
    }
}

enum State {
    UP,
    DOWN,
    LEFT,
    RIGHT
}