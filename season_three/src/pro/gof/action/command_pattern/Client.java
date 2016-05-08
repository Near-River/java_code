package pro.gof.action.command_pattern;

import org.junit.Test;

/**
 * 命令模式
 * Created by near on 2015/12/12.
 */
public class Client {
    @Test
    public void test() {
        Receive receive = new Receive();
        Command command = new ConcreteCommand(receive);
        Invoker invoker = new Invoker(command);

        invoker.invoke();
    }
}

/**
 * 抽象命令类
 */
interface Command {
    void execute();
}

/**
 * 具体命令类
 */
class ConcreteCommand implements Command {
    // 关联接收者
    private Receive receive;

    public ConcreteCommand(Receive receive) {
        this.receive = receive;
    }

    @Override
    public void execute() {
        // 可以在发送命令前后做日志记录
        System.out.println("--开始发送命令");
        receive.action();
        System.out.println("--结束发送命令");
    }
}

class Invoker {
    /**
     * 可以用容器存放多个命令对象，进行批处理
     * 应用场景：数据库底层的事物处理机制（撤销和恢复）
     */
    // 关联命令类
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void invoke() {
        command.execute();
    }
}

class Receive {
    public void action() {
        System.out.println("接收者开始行动");
    }
}