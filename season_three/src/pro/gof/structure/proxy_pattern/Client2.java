package pro.gof.structure.proxy_pattern;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理：
 * JDK 自带的动态代理
 * java.lang.reflect.Proxy
 * --动态生成代理类或对象
 * java.lang.reflect.InvocationHandler（处理器接口）
 * --通过 invoke 方法实现对真实角色的代理访问
 * 每次通过 Proxy 生成代理类对象时都要指定对应的处理器对象
 * <p>
 * javassist 字节码操作库实现
 * CGLIB
 * ASM（底层使用指令，可维护性较差）
 * <p>
 * 抽象角色中声明的所有方法都被转移到调用处理器(handler)的一个集中的方法中做处理
 * 实现更加灵活和统一的处理众多的方法
 * Created by near on 2015/12/11.
 */
public class Client2 {
    @Test
    public void test() {
        Star realStar = new RealStar();
        StarHandler starHandler = new StarHandler(realStar);

        /**
         * 动态生成代理类对象
         * public static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
         *
         * 动态代理对象和原对象的类加载器要一致
         */
        Star proxyStar = (Star) Proxy.newProxyInstance(realStar.getClass().getClassLoader(),
                realStar.getClass().getInterfaces(), starHandler);

        /*Star proxyStar = (Star) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{Star.class}, starHandler);*/

        proxyStar.signContract();
        proxyStar.sing();
    }
}

/**
 * 抽象角色的处理器类(包装真实角色)
 */
class StarHandler implements InvocationHandler {
    Star realStar;

    public StarHandler(Star realStar) {
        this.realStar = realStar;
    }

    /**
     * 抽象角色中声明的所有方法都被转移到处理器（Handler）的一个集中的方法中做处理
     * 实现更加灵活和统一的处理众多的方法
     *
     * @param proxy  代理对象
     * @param method 代理对象调用的方法
     * @param args   方法传递的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 添加前置方法处理
        if (method.getName().equals("signContract")) {
            System.out.println("lalala...");
            System.out.println("ProxyStar: sign contract");
        }

        if (method.getName().equals("sing")) {
            method.invoke(realStar, args);
        }
        // 添加后置方法处理
        return null;
    }
}