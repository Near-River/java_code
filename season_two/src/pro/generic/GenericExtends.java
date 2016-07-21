package pro.generic;


/**
 * 泛型继承和实现
 * Created by Near on 2015/11/29.
 */
public class GenericExtends {

    abstract class Father<T1, T2> {
        T1 age;

        public abstract void fun(T2 t2);
    }

    /**
     * 全部保留泛型，泛型子类
     *
     * @param <T1>
     * @param <T2>
     */
    class Son1<T1, T2> extends Father<T1, T2> {
        @Override
        public void fun(T2 t2) {

        }
    }

    /**
     * 部分保留泛型，泛型子类
     *
     * @param <T1>
     */
    class Son2<T1> extends Father<T1, String> {
        @Override
        public void fun(String s) {

        }
    }

    /**
     * 不保留泛型，指定具体类型
     */
    class Son3 extends Father<Integer, String> {
        @Override
        public void fun(String s) {

        }
    }

    /**
     * 泛型的擦除
     * 不保留泛型，不指定类型
     */
    class Son4 extends Father {
        @Override
        public void fun(Object object) {

        }
    }
}

