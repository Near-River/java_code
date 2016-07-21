package pro.array;


/**
 * 模拟 ArrayList 容器类的实现
 * Created by Near on 2015/11/24.
 */
public class MyArrayList {
    //存放对象的数组容器
    Object[] elements;

    //容器的当前容量大小
    int count;

    public MyArrayList() {
        // elements = new Object[16];
        this(10);
    }

    public MyArrayList(int size) {
        elements = new Object[size];
    }

    public void add(Object object) {
        ensureCapacity();

        elements[count++] = object;
    }

    public void add(int index, Object object) {
        rangeCheck(index);
        ensureCapacity();

        System.arraycopy(elements, index, elements, index + 1, count - index);
        elements[index] = object;
        count++;
    }

    private void ensureCapacity() {
        if (count >= elements.length) {
            int newCount = count * 2;
            Object[] newElements = new Object[newCount];
            System.arraycopy(elements, 0, newElements, 0, Math.min(count, newCount));
            elements = newElements;
        }
    }

    public void remove(int index) {
        rangeCheck(index);

        /*for(int i=index; i<count-1; i++){
            elements[i] = elements[i+1];
        }
        elements[count-1] = null;*/

        int numMoved = count - index - 1;
        if (numMoved > 0)
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        elements[--count] = null;
    }

    public Object get(int index) {
        rangeCheck(index);

        return elements[index];
    }

    public int length() {
        return count;
    }

    public int indexOf(Object object) {
        if (object == null) {
            return -1;
        }
        for (int i = 0; i < count; i++) {
            if (object.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    public Object set(int index, Object object) {
        rangeCheck(index);

        Object oldObject = elements[index];
        elements[index] = object;

        return oldObject;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index > count - 1) {
            try {
                throw new ArrayIndexOutOfBoundsException();
            } catch (Exception e) {
                System.out.println("ArrayIndexOutOfBoundsException");
            }
        }
    }
}