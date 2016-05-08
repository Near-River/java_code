package pro.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 实现 Comparable 接口
 * Created by Near on 2015/11/30.
 */
public class TestComparable implements Comparable{
    @Test
    public void test(){
        /*Integer a = new Integer(101);
        Integer b = new Integer(21);
        System.out.println(a.compareTo(b));*/

        /*String s1 = new String("haha");
        String s2 = new String("xixi");
        String s3 = new String("haha111");
        System.out.println(s2.compareTo(s1));
        System.out.println(s1.compareTo(s3));*/

        /*String []strs = {"haha", "xixi", "hehe", "gaga"};
        bubbleSortPlus(strs);
        bubbleSortPlus2(strs);*/

        List<String> list = new ArrayList<String>();
        list.add("haha");
        list.add("xixi");
        list.add("gaga");

        list = listSort(list);
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public static void bubbleSortPlus(Object []arr){
        boolean ordered = true;
        out:for(int i=0; i<arr.length-1; i++){
            for(int j=0; j<arr.length-i-1; j++){
                if( ((Comparable)arr[j]).compareTo(arr[j+1]) > 0 ) {
                    Object temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    ordered = false;
                }
            }
            if(ordered){
                break out;
            }
        }
        // System.out.println(Arrays.toString(arr));
    }

    public static <E extends Comparable<? super E>> void bubbleSortPlus2(E []arr){
        boolean ordered = true;
        out:for(int i=0; i<arr.length-1; i++){
            for(int j=0; j<arr.length-i-1; j++){
                if( ((Comparable)arr[j]).compareTo(arr[j+1]) > 0 ) {
                    E temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    ordered = false;
                }
            }
            if(ordered){
                break out;
            }
        }
        // System.out.println(Arrays.toString(arr));
    }

    public static <E extends Comparable<? super E>> List<E> listSort(List<E> list){
        Object[] objects = list.toArray();
        bubbleSortPlus(objects);

        list.clear();
        for(Object o : objects){
            list.add((E)o);
        }
       /* for(int i=0; i<objects.length; i++){
            list.add(i, (E)objects[i]);
        }*/
        return list;
    }
}
