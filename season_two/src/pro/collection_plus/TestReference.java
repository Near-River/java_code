package pro.collection_plus;

import org.junit.Test;

import java.lang.ref.WeakReference;
import java.util.EnumMap;
import java.util.IdentityHashMap;
import java.util.WeakHashMap;

/**
 * 强引用：StrongReference：引用指向对象，gc 运行时不回收
 * 软引用：SoftReference：gc 运行时可能回收
 * 弱引用：WeakReference：gc 运行时立即回收
 * 虚引用：PhantomReference：类似于无引用，主要跟踪对象被回收状态，不能单独使用，必须和引用队列(ReferenceQueue)联合使用
 * <p>
 * Created by Near on 2015/11/30.
 */
public class TestReference {
    @Test
    public void test() {
        // 字符串在常量池 共享（不能回收）
        String str = "hello world";
        WeakReference<String> weakReference = new WeakReference<String>(str);
        System.out.println("gc 运行前：" + weakReference.get());
        // 断开引用
        str = null;
        // 通知回收
        System.gc();
        System.runFinalization();
        System.out.println("gc 运行后：" + weakReference.get());

        String str2 = new String("hello world");
        WeakReference<String> weakReference2 = new WeakReference<String>(str2);
        System.out.println("gc 运行前：" + weakReference2.get());
        // 断开引用
        str2 = null;
        // 通知回收
        System.gc();
        System.runFinalization();
        System.out.println("gc 运行后：" + weakReference2.get());
    }

    /**
     * WeakHashMap: 键为弱引用
     */
    @Test
    public void testWeakHashMap() {
        WeakHashMap<String, String> map = new WeakHashMap<String, String>();

        map.put("101", "haha");
        map.put("102", "haha");
        map.put(new String("103"), "haha");
        map.put(new String("104"), "haha");
        System.gc();
        System.runFinalization();
        System.out.println(map.size());
    }

    /**
     * IdentityHashMap：以地址为键进行比较
     */
    @Test
    public void testIdentityHashMap() {
        IdentityHashMap<String, String> map = new IdentityHashMap<String, String>();

        map.put("aaa", "haha");
        map.put("aaa", "haha");
        map.put("bbb", "haha");
        map.put(new String("bbb"), "haha");

        System.out.println(map.size());
    }

    /**
     * EnumMap: 键必须为枚举值
     */
    @Test
    public void testEnumMap() {
        // EnumMap(Class<K> keyType)
        EnumMap<Season, String> map = new EnumMap<Season, String>(Season.class);

        map.put(Season.SPRING, "haha1");
        map.put(Season.SUMMER, "haha2");
        map.put(Season.AUTUMN, "haha3");
        map.put(Season.WINTER, "haha4");

        System.out.println(map.size());
    }

    private enum Season {
        SPRING, SUMMER, AUTUMN, WINTER
    }
}
