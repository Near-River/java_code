package pro.collection;

import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by Near on 2015/11/29.
 */
public class MyIterator implements Iterator {
    private static final int SIZE = 10;

    private String[] elements = new String[SIZE];

    private int size = SIZE;

    private int cursor = -1;

    private MyIterator() {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = "NO: " + (i + 1);
        }
    }

    public int size() {
        return size;
    }

    @Override
    public boolean hasNext() {
        return (cursor + 1) < size();
    }

    @Override
    public Object next() {
        return elements[++cursor];
    }

    @Override
    public void remove() {
        System.arraycopy(elements, cursor + 1, elements, cursor, size() - cursor - 1);
        size--;
        // 游标回滚
        cursor--;
    }

    @Override
    public void forEachRemaining(Consumer action) {
    }

    @Test
    public void test() {
        MyIterator myIterator = new MyIterator();
        while (myIterator.hasNext()) {
            System.out.println(myIterator.next());
            myIterator.remove();
        }
        System.out.println(myIterator.size());
    }
}