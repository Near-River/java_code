package pro.gof.action.observer_pattern;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 * Created by near on 2015/12/12.
 */
public class Client {
    @Test
    public void test() {
        ConcreteSubject subject = new ConcreteSubject();
        subject.registerObserver(new ObserverA());
        subject.registerObserver(new ObserverA());
        subject.registerObserver(new ObserverA());

        // 目标对象改变状态并及时发出广播
        subject.setState(State.DOWN);
    }
}

/**
 * 目标类
 */
class Subject {
    protected List<Observer> observerList = new ArrayList<>();

    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observerList) {
            // 传递目标对象
            observer.update(this);
        }
    }
}

class ConcreteSubject extends Subject {
    private State state;

    public ConcreteSubject() {
    }

    public State getState() {
        return state;
    }

    // 状态一旦改变，立即通知所有的观察者
    public void setState(State state) {
        this.state = state;
        this.notifyAllObservers();
    }
}

/**
 * 抽象观察者类
 */
abstract class Observer {
    public abstract void update(Subject subject);
}

class ObserverA extends Observer {
    private State state;

    @Override
    public void update(Subject subject) {
        state = ((ConcreteSubject) subject).getState();
        System.out.println("观察者状态更新："+state);
    }
}


enum State {
    UP,
    DOWN,
    LEFT,
    RIGHT
}