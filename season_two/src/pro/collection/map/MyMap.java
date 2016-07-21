package pro.collection.map;

/**
 * Created by Near on 2015/11/28.
 */
public class MyMap {

    Entry[] entries = new Entry[100];
    int size;

    public void put(Object key, Object value) {
        // 键值不能重复
        for (int i = 0; i < size; i++) {
            if (key.equals(entries[i].key)) {
                entries[i].value = value;
                return;
            }
        }
        Entry entry = new Entry(key, value);
        entries[size++] = entry;
    }

    public Object get(Object key) {
        for (int i = 0; i < size; i++) {
            if (key.equals(entries[i].key)) {
                return entries[i].value;
            }
        }
        return null;
    }

    public boolean containsKey(Object key) {
        for (int i = 0; i < size; i++) {
            if (key.equals(entries[i].key)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(Object value) {
        for (int i = 0; i < size; i++) {
            if (value.equals(entries[i].value)) {
                return true;
            }
        }
        return false;
    }

    private class Entry {
        Object key;
        Object value;

        Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}
