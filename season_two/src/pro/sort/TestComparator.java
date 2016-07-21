package pro.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * 实现 Comparator 接口
 * -- 解耦：独立于实体类
 * <p>
 * Created by Near on 2015/11/30.
 */
public class TestComparator<E> implements Comparator<E> {
    @Test
    public void test() {
        TestComparator<String> testComparator = new TestComparator<String>();
        TestComparator<Integer> testComparator2 = new TestComparator<Integer>();

        /*Integer a = new Integer(101);
        Integer b = new Integer(21);
        System.out.println(testComparator2.compare(a, b));*/

        /*String s1 = new String("haha");
        String s2 = new String("xixi");
        String s3 = new String("haha111");

        System.out.println(testComparator.compare(s1, s2));
        System.out.println(testComparator.compare(s1, s3));*/

       /* String []strs = {"haha", "xixi", "hehe", "gaga"};
        bubbleSortPlus(strs, testComparator);*/

        List<String> list = new ArrayList<String>();
        list.add("haha");
        list.add("xixi");
        list.add("gaga");

        list = listSort(list, testComparator);
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Override
    public int compare(E o1, E o2) {
        // return o1.toString().length() - o2.toString().length();
        return ((Comparable) o1).compareTo(o2);
    }

    private static <E> void bubbleSortPlus(E[] arr, Comparator<? super E> comparator) {
        boolean ordered = true;
        out:
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (comparator.compare((E) arr[j], (E) arr[j + 1]) > 0) {
                    E temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    ordered = false;
                }
            }
            if (ordered) {
                break out;
            }
        }
        // System.out.println(Arrays.toString(arr));
    }

    private static <E> List<E> listSort(List<E> list, Comparator<? super E> comparator) {
        E[] objects = (E[]) list.toArray();
        bubbleSortPlus(objects, comparator);

        list.clear();
        for (Object o : objects) {
            list.add((E) o);
        }
        return list;
    }
}
