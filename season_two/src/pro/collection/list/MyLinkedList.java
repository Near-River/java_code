package pro.collection.list;

/**
 * 自定义的一个双向链表
 * <p>
 * Created by Near on 2015/11/28.
 */
public class MyLinkedList {
    private Node first;
    private Node last;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public boolean add(Object obj) {
        if (first == null) {
            first = new Node(obj, null, null);
            last = first;
        } else {
            Node node = new Node(obj, null, last);
            last.next = node;
            node.prev = last;
            last = node;
        }
        size++;
        return true;
    }

    public Object get(int index) {
        if (checkRange(index)) {
            return null;
        }

        Node node = null;
        if (!isEmpty()) {
            node = indexOf(index);
            return node.item;
        }
        return null;
    }

    public Object set(int index, Object element) {
        if (checkRange(index)) {
            return null;
        }

        Node node = first;
        if (!isEmpty()) {
            node = indexOf(index);
        }
        Object oldEmement = node.item;
        node.item = element;
        return oldEmement;
    }

    public void add(int index, Object element) {
        if (index < 0 || index > size) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("IndexOutOfBorderException.");
            }
        }

        if (isEmpty()) {
            first = last = new Node(element, null, null);
        } else {
            Node node = indexOf(index);
            Node insert = new Node(element, null, null);

            if (node == first) {
                insert.next = first;
                first.prev = insert;
                first = insert;
            } else if (node == null) {
                last.next = insert;
                insert.prev = last;
                last = insert;
            } else {
                node.prev.next = insert;
                insert.prev = node.prev;
                node.prev = insert;
                insert.next = node;
            }
        }
        size++;
    }

    public Object remove(int index) {
        if (checkRange(index)) {
            return null;
        }

        Node node = null;
        if (!isEmpty()) {
            node = indexOf(index);
        }
        if (first == last) {
            first = last = null;
        } else if (node == first) {
            first = first.next;
            first.prev = null;
        } else if (node == last) {
            last = last.prev;
            last.next = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        size--;
        return node.item;
    }

    private Node indexOf(int index) {
        // 提高遍历查询的效率
        if (index < (size >> 1)) {
            Node node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
        /*int count = 0;
        Node node = first;
        while (count < index) {
            node = node.next;
            count++;
        }
        return node;*/
    }

    private boolean checkRange(int index) {
        if (index < 0 || index >= size) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("IndexOutOfBorderException.");
            }
        }
        return false;
    }

    private static class Node {
        Object item;
        Node next;
        Node prev;

        Node(Object item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}
