package pro.collection.map;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * MyMap 的升级版，使用 hashcode 可以提高查询效率
 * Created by Near on 2015/11/28.
 */
public class MyMapPlus {
    final int MAX_SIZE = 99;
    LinkedList[] entriesList = new LinkedList[MAX_SIZE];
    int size;

    public void put(Object key, Object value) {
        // 键值不能重复
        Entry entry = new Entry(key, value);

        int site = key.hashCode() % MAX_SIZE;
        if (entriesList[site] == null) {
            // 初始化链表
            LinkedList linkedList = new LinkedList();
            linkedList.add(entry);
            entriesList[site] = linkedList;
        } else {
            LinkedList linkedList = entriesList[site];
            Iterator iterator = linkedList.iterator();
            while (iterator.hasNext()) {
                Entry temp = (Entry) iterator.next();
                if (key.equals(temp.key)) {
                    temp.value = value;
                    return;
                }
            }
            linkedList.add(entry);
        }
    }

    public Object get(Object key) {
        int site = key.hashCode() % MAX_SIZE;

        if (entriesList[site] != null) {
            LinkedList linkedList = entriesList[site];
            Iterator iterator = linkedList.iterator();
            while (iterator.hasNext()) {
                Entry entry = (Entry) iterator.next();
                if (key.equals(entry.key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    class Entry {
        Object key;
        Object value;

        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}