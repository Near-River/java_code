package pro.collection_plus;

import org.junit.Test;

import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Enumeration：已经被 Iterator 所取代
 * 应用：
 *      Vector:  elements()
 *      StringTokenizer 是 Enumeration 的子类, 作用类似于 String.split('char')
 * Created by Near on 2015/11/30.
 */
public class TestEnumeration {
    @Test
    public void test(){
        Vector<String> vector = new Vector<String>();
        vector.add("Java");
        vector.add("C++");
        vector.add("JavaScript");

        Enumeration<String> enumeration = vector.elements();
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }

        String str = "abc/def/ghi";
        StringTokenizer stringTokenizer = new StringTokenizer(str, "/");

         while (stringTokenizer.hasMoreElements()) {
            System.out.println(stringTokenizer.nextElement());
        }
    }
}