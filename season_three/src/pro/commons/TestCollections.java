package pro.commons;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.*;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.bidimap.DualTreeBidiMap;
import org.apache.commons.collections4.functors.*;
import org.apache.commons.collections4.iterators.ArrayListIterator;
import org.apache.commons.collections4.iterators.FilterIterator;
import org.apache.commons.collections4.iterators.LoopingIterator;
import org.apache.commons.collections4.iterators.UniqueFilterIterator;
import org.apache.commons.collections4.list.PredicatedList;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.apache.commons.collections4.queue.PredicatedQueue;
import org.apache.commons.collections4.queue.UnmodifiableQueue;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Apache：commons-collections
 * 函数式编程：实现解耦
 * Predicate: 封装条件/判别式  替代 if/else
 * Transformer: 转换器
 * Closure: 闭包，封装特定的应用功能
 * 集合操作
 * Created by Near on 2015/12/1.
 */
public class TestCollections {
    @Test
    public void testPredicate() {
        //===============================================================
        // 判断相等
        Predicate<String> predicate = EqualPredicate.equalPredicate("Near");
        // boolean isEquals = predicate.evaluate("nate");
        boolean isEquals = predicate.evaluate(new String("Near"));
        // boolean isEquals = predicate.evaluate("Near");

        System.out.println(isEquals);

        //===============================================================
        // 判断非空
        // Predicate predicate2 = NotNullPredicate.INSTANCE;
        Predicate notNullPredicate = NotNullPredicate.notNullPredicate();
        String str = "";
        // System.out.println(predicate2.evaluate(str));
        System.out.println(notNullPredicate.evaluate(null));

        //===============================================================
        // 容器值非空添加的判断
        List<Integer> list = PredicatedList.predicatedList(new ArrayList<Integer>(), notNullPredicate);
        list.add(123);
        // list.add(null);

        //===============================================================
        // 唯一性判断
        Predicate<Integer> uniquePredicate = UniquePredicate.uniquePredicate();
        List<Integer> list2 = PredicatedList.predicatedList(new ArrayList<Integer>(), uniquePredicate);
        list2.add(123);
        // list2.add(123);

        //===============================================================
        // 自定义判断
        Predicate<String> selfPredicate = new Predicate<String>() {
            @Override
            public boolean evaluate(String s) {
                return s.length() >= 5 && s.length() <= 10;
            }
        };
        // 断言结合
        Predicate<String> allPredicate = PredicateUtils.allPredicate(selfPredicate, notNullPredicate);
        System.out.println(allPredicate.evaluate("abcdefghijklmn"));
        System.out.println(allPredicate.evaluate("abcdefg"));
    }

    @Test
    public void testTransformer() {
        //===============================================================
        // 自定义类型转换器
        Transformer<Long, String> selfTransform = new Transformer<Long, String>() {
            @Override
            public String transform(Long aLong) {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(aLong);
            }
        };
        List<Long> list = new ArrayList<Long>();
        list.add(9999999999999L);
        list.add(11111111111111L);
        Collection<String> collection = CollectionUtils.collect(list, selfTransform);
        for (String s : collection) {
            System.out.println(s);
        }
    }

    @Test
    public void testTransformer2() {
        //===============================================================
        Predicate<Employee> lowSalary = new Predicate<Employee>() {
            @Override
            public boolean evaluate(Employee employee) {
                return employee.getSalary() < 10000;
            }
        };

        Predicate<Employee> highSalary = new Predicate<Employee>() {
            @Override
            public boolean evaluate(Employee employee) {
                return employee.getSalary() >= 10000;
            }
        };

        Transformer<Employee, Level> transformer = new Transformer<Employee, Level>() {
            @Override
            public Level transform(Employee employee) {
                return new Level(employee.getName(), "程序猿");
            }
        };

        Transformer<Employee, Level> transformer2 = new Transformer<Employee, Level>() {
            @Override
            public Level transform(Employee employee) {
                return new Level(employee.getName(), "攻城狮");
            }
        };

        Predicate[] predicates = {lowSalary, highSalary};
        Transformer[] transformers = {transformer, transformer2};

        // 关联判别式和转换器
        Transformer switchTransformer = new SwitchTransformer(predicates, transformers, null);
        List<Employee> list = new ArrayList<Employee>();
        list.add(new Employee("小明", 1000.0));
        list.add(new Employee("小霞", 6000.0));
        list.add(new Employee("小红", 100000.0));
        list.add(new Employee("小军", 20000.0));

        Collection<Level> collection = CollectionUtils.collect(list, switchTransformer);
        for (Level level : collection) {
            System.out.println(level);
        }
    }

    /**
     * Closure 闭包：封装特定的业务功能
     */
    @Test
    public void testClosure() {
        //===============================================================
        List<Employee> list = new ArrayList<Employee>();
        list.add(new Employee("小明", 1000.0));
        list.add(new Employee("小霞", 6000.0));
        list.add(new Employee("小红", 100000.0));
        list.add(new Employee("小军", 20000000.0));

        Closure<Employee> closure = new Closure<Employee>() {
            @Override
            public void execute(Employee employee) {
                employee.setSalary(employee.getSalary() * 1.2);
            }
        };

        CollectionUtils.forAllDo(list, closure);
        Iterator<Employee> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * 用 Closure 做选择业务逻辑处理
     */
    @Test
    public void testIfClosure() {
        List<Goods> list = new ArrayList<Goods>();
        list.add(new Goods("aaa", 100.0, true));
        list.add(new Goods("bbb", 600.0, false));
        list.add(new Goods("ccc", 120.0, true));
        list.add(new Goods("ddd", 200.0, false));

        // 打折商品打八折
        Closure<Goods> closure = new Closure<Goods>() {
            @Override
            public void execute(Goods goods) {
                goods.setPrice(goods.getPrice() * 0.8);
            }
        };
        // 不打折商品，商品价格大于一百则减去二十
        Closure<Goods> closure2 = new Closure<Goods>() {
            @Override
            public void execute(Goods goods) {
                if (goods.getPrice() > 100) {
                    goods.setPrice(goods.getPrice() - 20);
                }
            }
        };

        Predicate<Goods> predicate = new Predicate<Goods>() {
            @Override
            public boolean evaluate(Goods goods) {
                return goods.isDiscount();
            }
        };

        Closure<Goods> choiceClosure = IfClosure.ifClosure(predicate, closure, closure2);
        CollectionUtils.forAllDo(list, choiceClosure);
        Iterator<Goods> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testWhileClosure() {
        //===============================================================
        List<Employee> list = new ArrayList<Employee>();
        list.add(new Employee("小明", 2000.0));
        list.add(new Employee("小霞", 6000.0));
        list.add(new Employee("小红", 10000.0));
        list.add(new Employee("小军", 3000.0));

        Closure<Employee> closure = new Closure<Employee>() {
            @Override
            public void execute(Employee employee) {
                employee.setSalary(employee.getSalary() * 1.2);
            }
        };

        Predicate<Employee> predicate = new Predicate<Employee>() {
            @Override
            public boolean evaluate(Employee employee) {
                return employee.getSalary() < 5000.0;
            }
        };

        // 如果员工工资小于 5000，则将员工工资以 1.2 倍的利率增长，直到工资不小于 5000
        // false ：该循环结构是 while    true ：该循环结构是 do-while
        Closure<Employee> whileClosure = WhileClosure.whileClosure(predicate, closure, false);
        CollectionUtils.forAllDo(list, whileClosure);
        Iterator<Employee> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testChainClosure() {
        List<Goods> list = new ArrayList<Goods>();
        list.add(new Goods("aaa", 100.0, true));
        list.add(new Goods("bbb", 600.0, false));
        list.add(new Goods("ccc", 100.0, true));
        list.add(new Goods("ddd", 200.0, false));

        // 打折商品打八折
        Closure<Goods> closure = new Closure<Goods>() {
            @Override
            public void execute(Goods goods) {
                goods.setPrice(goods.getPrice() * 0.8);
            }
        };
        // 商品价格大于一百则减去二十
        Closure<Goods> closure2 = new Closure<Goods>() {
            @Override
            public void execute(Goods goods) {
                if (goods.getPrice() > 100) {
                    goods.setPrice(goods.getPrice() - 20);
                }
            }
        };

        // 以链式的方式执行业务逻辑处理
        Closure<Goods> chainClosure = ChainedClosure.chainedClosure(closure, closure2);
        CollectionUtils.forAllDo(list, chainClosure);
        Iterator<Goods> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testCollections() {
        Set<Integer> set = Sets.newHashSet(1, 2, 3, 4, 5);
        Set<Integer> set2 = Sets.newHashSet(1, 3, 5, 7, 9);

        // 求并集
        Collection<Integer> collection = CollectionUtils.union(set, set2);
        for (Integer i : collection) {
            // System.out.println(i);
        }
        // 求交集：两种方法
        // Collection<Integer> collection2 = CollectionUtils.intersection(set, set2);
        Collection<Integer> collection2 = CollectionUtils.retainAll(set, set2);

        // 求差集
        Collection<Integer> collection3 = CollectionUtils.subtract(set, set2);
    }

    @Test
    public void testQueue() {
        // 循环队列
        CircularFifoQueue<String> queue = new CircularFifoQueue<String>(3);
        queue.add("aaa");
        queue.add("bbb");
        queue.add("ccc");
        queue.add("ddd");
        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "\t");
        }
        // 只读队列
        Queue<String> readOnlyQueue = UnmodifiableQueue.unmodifiableQueue(queue);
        // readOnlyQueue.add("eee");
        queue.add("fff");
        // 不安全，可通过 queue 来改变 readOnlyQueue
        System.out.println("\n" + readOnlyQueue + "\t" + readOnlyQueue.size());

        // 断言队列
        Queue<String> notNullQueue = PredicatedQueue.predicatedQueue(queue, NotNullPredicate.INSTANCE);
        // notNullQueue.add(null);
    }

    /**
     * IteratorMap: 遍历 Map
     */
    @Test
    public void testIteratorMap() {
        IterableMap<String, String> map = new HashedMap<String, String>();
        map.put("a1", "101");
        map.put("b1", "201");
        map.put("c1", "301");
        MapIterator<String, String> mapIterator = map.mapIterator();
        while (mapIterator.hasNext()) {
            mapIterator.next();
            System.out.println(mapIterator.getKey() + "-->" + mapIterator.getValue());
        }
    }

    /**
     * 去重过滤器
     */
    @Test
    public void testUniqueFilterIterator() {
        List<String> list = Lists.newArrayList("aaa", "bbb", "aaa", "ddd");
        Iterator<String> iterator = new UniqueFilterIterator(list.iterator());
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * 自定义过滤器
     */
    @Test
    public void testSelfFilterIterator() {
        // 做回文判断
        List<String> list = Lists.newArrayList("mom", "abc", "aba", "near");
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean evaluate(String s) {
                return new StringBuilder(s).reverse().toString().equals(s);
            }
        };

        Iterator<String> iterator = new FilterIterator(list.iterator(), predicate);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * 测试循环迭代器
     */
    @Test
    public void testLoopIterator() {
        List<String> list = Lists.newArrayList("mom", "abc", "aba", "near");

        Iterator<String> iterator = new LoopingIterator<String>(list);
        for (int i = 0; i < 10; i++) {
            System.out.println(iterator.next());
        }
    }

    /**
     * 测试数组迭代器
     */
    @Test
    public void testArrayIterator() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        Iterator<Integer> iterator = new ArrayListIterator<Integer>(arr);
        // Iterator<String> iterator = new ArrayListIterator<String>(arr, 1, 5);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * 测试双向 Map
     * DualTreeBidiMap ：有序
     * DualHashBidiMap ：无序
     */
    @Test
    public void testBidiMap() {
        BidiMap<String, String> map = new DualTreeBidiMap<String, String>();
        map.put("a1", "101");
        map.put("b1", "201");
        map.put("c1", "301");
        // System.out.println(map.inverseBidiMap().get("201"));

        MapIterator<String, String> mapIterator = map.inverseBidiMap().mapIterator();
        while(mapIterator.hasNext()){
            mapIterator.next();
            System.out.println(mapIterator.getKey()+"-->"+mapIterator.getValue());
        }
    }

    /**
     * 测试包 Bag
     * HashBag ：无序    TreeBag ：有序
     */
    @Test
    public void testBag() {
        String[] arr = "this is a cat and that is a mice and where is the food".split(" ");
        Bag<String> bag = new HashBag<String>();
       /* bag.add("b");
        bag.add("a", 3);
        bag.remove("a", 2);
        Iterator<String> iterator = bag.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }*/
        for(String s : arr){
            bag.add(s);
        }
        Set<String> set = bag.uniqueSet();
        Iterator<String> iterator = set.iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            System.out.println(key+"-->"+bag.getCount(key));
        }
    }
}