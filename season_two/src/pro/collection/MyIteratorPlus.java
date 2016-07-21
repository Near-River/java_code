package pro.collection;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by Near on 2015/11/29.
 */
public class MyIteratorPlus<E> implements Iterable<E> {
    private Object[] elements;

    private int size;

    private MyIteratorPlus() {
        elements = new Object[10];
    }

    public int size() {
        return size;
    }

    public void add(E ele) {
        if (elements.length == size()) {
            elements = Arrays.copyOf(elements, size() * 2);
        }
        elements[size++] = ele;
    }

    private class MyIter<E> implements Iterator<E> {
        private int cursor = -1;

        @Override
        public boolean hasNext() {
            return (cursor + 1) < size();
        }

        @Override
        public E next() {
            return (E) elements[++cursor];
        }

        @Override
        public void remove() {
            System.arraycopy(elements, cursor + 1, elements, cursor, size() - cursor - 1);
            size--;
            // 游标回滚
            cursor--;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
        }
    }

    public Iterator<E> iterator() {
        return new MyIter<E>();
    }

    @Test
    public void test() {
        MyIteratorPlus<String> myIteratorPlus = new MyIteratorPlus<String>();

        myIteratorPlus.add("haha1");
        myIteratorPlus.add("haha2");
        myIteratorPlus.add("haha3");

        Iterator<String> iterator = myIteratorPlus.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // 实现 Iterable 接口可使用 for 增强来遍历
        for (String s : myIteratorPlus) {
            System.out.println(s);
        }

        System.out.println(myIteratorPlus.size());
    }
}
