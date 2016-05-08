package pro.gof.structure.adapter_pattern;

/**
 * Created by near on 2015/12/11.
 */
public class Computer {
    USB usb;

    public Computer(USB usb) {
        this.usb = usb;
    }

    public void run(){
        usb.handleRequest();
    }
}

/**
 * 源接口
 */
interface USB {
    void handleRequest();
}

/**
 * 适配器类
 * 实现源接口
 */
class Adapter implements USB {
    Out out;

    public Adapter(Out out) {
        this.out = out;
    }

    @Override
    public void handleRequest() {
        out.request();
    }

}

/**
 * 目标接口
 */
interface Out{
    void request();
}

/**
 * 被适配的类
 */
class Keyboard implements Out {
    @Override
    public void request() {
        System.out.println("发送打字请求");
    }
}