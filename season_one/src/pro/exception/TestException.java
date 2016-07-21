package pro.exception;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by Near on 2015/11/26.
 */
public class TestException {
    @Test
    public void test() {
        class A {
            public void method() throws IOException {
            }
        }

        class B extends A {
            public void method() throws FileNotFoundException {
            }
        }

        class C extends A {
            public void method() {
            }
        }

        /*class D extends A {
            //超过父类抛出异常的范围
            public void method() throws Exception {
            }
        }*/

        class E extends A {
            public void method() throws IOException, FileNotFoundException {
            }
        }

        class F extends A {
            // ArithmeticException extends RuntimeException
            public void method() throws IOException, ArithmeticException {
            }
        }

        /*class G extends A {
            // ParseException extends Exception
            public void method() throws IOException, ParseException {
            }
        }*/
    }
}

/**
 * 自定义异常类
 */
class MyException extends Exception {
    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }
}
