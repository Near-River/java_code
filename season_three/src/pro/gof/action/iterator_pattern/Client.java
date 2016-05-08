package pro.gof.action.iterator_pattern;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by near on 2015/12/12.
 */
public class Client {
    @Test
    public void test() {
        MyAggregate myAggregate = new MyAggregate();
        myAggregate.add("aaa");
        myAggregate.add("bbb");
        myAggregate.add("ccc");

        MyIterator iterator = myAggregate.iterator();
        while(iterator.hasNext()){
            iterator.next();
            System.out.println(iterator.getCurrentItem());
        }
    }
}

/**
 * 自定义聚合类
 */
class MyAggregate {
    private List<Object> list = new ArrayList<>();

    public void add(Object object) {
        list.add(object);
    }

    public void remove(Object object) {
        list.remove(object);
    }

    public MyIterator iterator() {
        return new MyIterator() {
            private int cursor = -1;

            @Override
            public void first() {
                cursor = 0;
            }

            @Override
            public Object next() {
                cursor++;
                Object object = list.get(cursor);
                return object;
            }

            @Override
            public boolean hasNext() {
                if (cursor + 1 < list.size()) {
                    return true;
                }
                return false;
            }

            @Override
            public Object getCurrentItem() {
                return list.get(cursor);
            }
        };
    }
}

/**
 * 自定义迭代器
 */
interface MyIterator {
    void first();

    Object next();

    boolean hasNext();

    // 获取当前游标对象指向的对象
    Object getCurrentItem();
}