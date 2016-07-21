package pro2.network;

import org.junit.Test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * Created by Near on 2015/12/5.
 */
public class TestInetAddress {
    @Test
    public void test(){
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();

            System.out.println(inetAddress.getHostAddress());
            System.out.println(inetAddress.getHostName());

            inetAddress = InetAddress.getByName("kltitrade.com");

            System.out.println(inetAddress.getHostAddress());
            System.out.println(inetAddress.getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        InetSocketAddress inetSocketAddress = new InetSocketAddress("www.sina.com.cn", 8080);
        // InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8080);

        System.out.println(inetSocketAddress.getAddress());
        System.out.println(inetSocketAddress.getHostName());
        System.out.println(inetSocketAddress.getPort());
    }
}
