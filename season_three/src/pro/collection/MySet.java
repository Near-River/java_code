package pro.collection;

import java.util.HashMap;

/**
 * Created by Near on 2015/11/28.
 */
public class MySet {
    private HashMap hashMap;
    private static final Object PRESENT = new Object();

    public MySet() {
        hashMap = new HashMap();
    }

    public int size(){
        return hashMap.size();
    }

    public void add(Object object){
        hashMap.put(object, PRESENT);
    }

    public boolean remove(Object object){
       return hashMap.remove(object) == PRESENT;
    }

    public boolean contains(Object object) {
        return hashMap.containsKey(object);
    }

    public boolean isEmpty(){
        return hashMap.isEmpty();
    }
}
