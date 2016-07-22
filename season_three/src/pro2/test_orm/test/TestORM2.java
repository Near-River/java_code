package pro2.test_orm.test;

import org.junit.Test;
import pro2.sorm.core.MysqlQueryPlus;
import pro2.sorm.core.QueryFactory;
import pro2.sorm.core.SimpleQuery;
import pro2.test_orm.po.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by near on 2015/12/13.
 */
public class TestORM2 {

    @Test
    public void test() {
        SimpleQuery simpleQuery = new MysqlQueryPlus();

        Object obj = simpleQuery.queryValue("select count(*) from user", null);
        System.out.println(obj);
    }

    @Test
    public void test2() {
        SimpleQuery simpleQuery = new MysqlQueryPlus();
        List<User> users = new ArrayList<>();

        users = simpleQuery.queryRows("select * from user", User.class, null);
        System.out.println(users);
    }

    @Test
    public void test3() {
        QueryFactory factory = QueryFactory.getInstance();
        SimpleQuery query = factory.createQuery();
        List<User> users = new ArrayList<>();

        users = query.queryRows("select * from user", User.class, null);
        System.out.println(users);
    }

    @Test
    public void test4() {
        QueryFactory factory = QueryFactory.getInstance();
        SimpleQuery query = factory.createQuery();
        User user = (User) query.queryById(User.class, 105);
        System.out.println(user);
    }

}