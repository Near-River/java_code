package pro.collection_plus;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 使用队列模拟堆栈
 * Created by Near on 2015/11/30.
 */
public class TestMyStack {
    @Test
    public void test(){
        MyStack<String> history = new MyStack<String>();
        history.push("www.baidu.com");
        history.push("www.sina.com");
        history.push("www.google.com");

        System.out.println(history.size());
        String item;
        while((item = history.pop()) != null){
            System.out.println(item);
        }
        System.out.println(history.size());
    }
}

class MyStack<E>{
    private Deque<E> deque = new ArrayDeque<E>();
    private int capicity;

    public int size(){
        return deque.size();
    }

    public MyStack() {
        capicity = 10;
    }

    public MyStack(int capicity) {
        this.capicity = capicity;
    }

    public boolean push(E e){
        if(deque.size() >= capicity){
            return false;
        } else{
            return deque.offerLast(e);
        }
    }

    public E pop(){
        return deque.pollLast();
    }

    public E peek(){
        return deque.peekLast();
    }
}

