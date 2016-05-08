package pro.gof.action.state_pattern;

import org.junit.Test;

/**
 * 状态模式
 * Created by near on 2015/12/12.
 */
public class Client {
    @Test
    public void test() {
        Context context = new Context();
        context.setState(new FreeState());
    }
}

/**
 * 环境类：维护一个 State 对象
 * 定义了当前对象
 */
class Context {
    private State state;

    public Context() {
    }

    public void setState(State state) {
        this.state = state;
        state.handle();
    }
}

/**
 * 抽象状态类(接口)
 */
interface State {
    void handle();
}

/**
 * 具体状态类：
 * 每一个类封装一个状态对应的行为
 */
class BookedState implements State {
    @Override
    public void handle() {
        System.out.println("房间已被预订");
    }
}

class CheckedState implements State {
    @Override
    public void handle() {
        System.out.println("房间已被入住");
    }
}

class FreeState implements State {
    @Override
    public void handle() {
        System.out.println("房间空闲");
    }
}