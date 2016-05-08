package pro.guava;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicate;
import com.google.common.collect.*;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Google: Guava
 * Created by Near on 2015/11/30.
 */
public class TestGuava {
    /**
     * 只读设置 对比：
     * public static <T> List<T> unmodifiableList(List<? extends T> list)
     * public static <E> ImmutableList<E> of(E e1, E e2, E e3)
     */
    @Test
    public void testReadOnly() {
        List<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        List<String> readList = Collections.unmodifiableList(list);
        // readList.add("ddd");
        // 存在不安全性：当向 list 中添加元素时，readList 也同时发生改变
        list.add("eee");
        Iterator<String> iterator = readList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println(list.size() + "  " + readList.size());

        // 对比 guava 对只读的设置，初始化 List
        List<String> immutableList = ImmutableList.of("aaa", "bbb", "ccc");
        // immutableList.add("ddd");
        System.out.println(immutableList.size());
    }

    /**
     * 过滤器的设置
     * public static <E> Collection<E> filter(Collection<E> unfiltered, Predicate<? super E> predicate)
     */
    @Test
    public void testFilter() {
        // 创建 List 并静态初始化
        // Lists 是 Guava 提供的操作 List 的工具类
        List<String> list = Lists.newArrayList("mom", "abc", "aba", "near");
        // 查找出 list 中所有是回文的单词
        Collection<String> collection = Collections2.filter(list, new Predicate<String>() {
                    @Override
                    public boolean apply(String s) {
                        String temp = new StringBuilder(s).reverse().toString();
                        return temp.equals(s);
                    }
                }
        );

        for (String string : collection) {
            System.out.println(string);
        }
    }

    /**
     * 做类型转换
     * public static <F, T> Collection<T> transform(Collection<F> fromCollection, Function<? super F, T> function)
     */
    @Test
    public void tesTransform() {
        Set<Long> set = new HashSet<Long>();
        set.add(1111111111111L);
        set.add(1000000000000L);
        set.add(99999999999L);

        Collection<String> collection = Collections2.transform(set, new Function<Long, String>() {
            @Override
            public String apply(Long aLong) {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(aLong);
            }
        });

        for (String string : collection) {
            System.out.println(string);
        }
    }

    /**
     * 组合式函数编程：便于解耦,功能分解
     * public static <A, B, C> Function<A, C> compose(Function<B, C> g, Function<A, ? extends B> f)
     */
    @Test
    public void tesCompose() {
        List<String> list = Lists.newArrayList("mother", "abc", "abababab", "near");

        // function1 ：如果字符串的长度大于五，则截取其前五个字符
        Function<String, String> function1 = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return (s.length()>5) ? s.substring(0,5):s;
            }
        };
        // function2 ：将字符串转为大写字符串
        Function<String, String> function2 = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        };

        Function<String, String> function = Functions.compose(function1, function2);
        Collection<String> collection = Collections2.transform(list, function);

        for (String string : collection) {
            System.out.println(string);
        }
    }

    /**
     * Constraint：约束（18 版本已经不支持）
     */
    @Test
    public void testConstraint(){
       /* List<String> list = Lists.newArrayList("mother", "abc", "abababab", "near");
        Constraint<String> constraint =new Constraint<String>() {
            @Override
            public String checkElement(String s) {
                Preconditions.checkNotNull(s);
                //长度验证 5-10为字符串
                Preconditions.checkArgument(s.length() >= 5 && s.length() <= 10);
                return s;
            }
        };
        list = Constraints.constrainedList(list, constraint);*/
    }

    /**
     * 集合的操作：交集 差集 并集
     */
    @Test
    public void testCollection() {
        Set<Integer> set = Sets.newHashSet(1, 2, 3, 4, 5);
        Set<Integer> set2 = Sets.newHashSet(1, 3, 5, 7, 9);

        // 求交集
        Sets.SetView<Integer> setView = Sets.intersection(set, set2);
        for (Integer integer : setView) {
            System.out.println(integer);
        }

        // 求差集
        // Sets.SetView<Integer> setView2 = Sets.difference(set, set2);

        // 求并集
        // Sets.SetView<Integer> setView3 = Sets.union(set, set2);
    }

    /**
     * Multiset：无序 + 键值对可重复
     * 应用于统计相同元素的出现次数   elementSet() + count(Object)
     */
    @Test
    public void testMultiset(){
        String[] arr = "this is a cat and that is a mice and where is the food".split(" ");

        Multiset<String> multiset = HashMultiset.create();
        for(String s : arr){
            multiset.add(s);
        }
        Set<String> set = multiset.elementSet();
        for(String s : set){
            System.out.println(s + " --> " + multiset.count(s));
        }
    }

    /**
     * Multimap：key 值可以重复
     *      keySet() + get()
     */
    @Test
    public void testMultimap(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("小红", "一班");
        map.put("小刚", "二班");
        map.put("小明", "三班");
        map.put("小李", "一班");
        // System.out.println(map.size());

        Multimap<String, String> multimap = ArrayListMultimap.create();

        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            // System.out.println(iterator.next());
            Map.Entry<String, String> entry = iterator.next();
            multimap.put(entry.getValue(), entry.getKey());
        }
        Set<String> set = multimap.keySet();
        for(String s : set){
            // System.out.println(s);
            Collection<String> collection = multimap.get(s);
            System.out.println(collection);
        }
    }

    /**
     * BiMap：键和值都唯一，不可重复
     *      inverse() + get()
     */
    @Test
    public void testBiMap(){
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("near", "123@gmail.com");
        biMap.put("game", "whoiam@sina.com");
        biMap.put("jack", "hello@gmail.com");

        String username = biMap.inverse().get("123@gmail.com");
        System.out.println(username);
        String email = biMap.get("jack");
        System.out.println(email);
        System.out.println(biMap.inverse().inverse() == biMap);
    }

    /**
     * 双键的 Map --> rowKey+columnKey+value
     * 以学生信息为例：Table
     * 获取所有行数据：cellSet()
     * 获取所有的学生：rowKeySet()
     * 获取所有的课程：columnKeySet(）
     * 获取所有的成绩：values()
     * 获取学生对应的课程：rowMap().get()/row(keyObject)
     * 获取课程对应的学生：columnMap().get()/column()
     * Tables.transpose() 将 Table 的 rowKey 列和 columnKey 列互换
     */
    @Test
    public void testTable(){
        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("小明", "java", 80);
        table.put("小丽", "java", 90);
        table.put("小刚", "prel", 70);
        table.put("小丽", "python", 100);

       /* System.out.println("**************************************************************");
        // 获取所有行数据
        Set<Table.Cell<String, String, Integer>> set = table.cellSet();
        for(Table.Cell<String, String, Integer> cell : set){
            System.out.println(cell.getRowKey() + "->" + cell.getColumnKey() + "->" + cell.getValue());
        }*/

       /* System.out.println("**************************************************************");
        // 获取所有课程
        Set<String> courses = table.columnKeySet();
        for(String s : courses){
            System.out.println(s);
        }

        System.out.println("**************************************************************");
        // 获取所有学生及对应的成绩信息
        Set<String> students = table.rowKeySet();
        for(String s : students){
            System.out.println(s + ":");
            Map<String, Integer> map = table.row(s);

            Set<String> exam = map.keySet();
            for(String e : exam){
                System.out.println(e + "\t\t" + map.get(e));
            }
            for(String c : courses){
                System.out.print(c + "\t");
            }
            System.out.println();
            for(String c : courses){
                System.out.print(map.get(c) + "\t");
            }
            System.out.println();
        }*/

        /*System.out.println("**************************************************************");
        // 获取所有成绩
        Collection<Integer> collection = table.values();
        for(Integer integer : collection){
            System.out.println(integer);
        }*/

       /* System.out.println("**************************************************************");
        // 获取所有学生的考试成绩信息
        Map<String, Map<String, Integer>> map = table.rowMap();
        Set<Map.Entry<String, Map<String, Integer>>> entrySet = map.entrySet();
        for(Map.Entry<String, Map<String, Integer>> entry : entrySet){
            String name = entry.getKey();
            Map<String, Integer> info = entry.getValue();
            System.out.print(name + "\t");
            for(Map.Entry<String, Integer> scoreMap : info.entrySet()){
                System.out.print(scoreMap.getKey()+"\t"+scoreMap.getValue()+"\t");
            }
            System.out.println();
        }*/

        System.out.println("**************************************************************");
        // 获取所有课程的学生成绩信息
        Map<String, Map<String, Integer>> map2 = table.columnMap();
        Set<Map.Entry<String, Map<String, Integer>>> entrySet2 = map2.entrySet();
        for(Map.Entry<String, Map<String, Integer>> entry : entrySet2){
            String name = entry.getKey();
            Map<String, Integer> info = entry.getValue();
            System.out.print(name + "\t");
            for(Map.Entry<String, Integer> scoreMap : info.entrySet()){
                System.out.print(scoreMap.getKey()+"\t"+scoreMap.getValue()+"\t");
            }
            System.out.println();
        }
    }
}
