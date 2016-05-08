package pro2.test_orm.test;

import org.junit.Test;
import pro2.sorm.bean.ColumnInfo;
import pro2.sorm.bean.FieldGetSetInfo;
import pro2.sorm.bean.TableInfo;
import pro2.sorm.core.*;
import pro2.sorm.util.FileUtils;
import pro2.test_orm.po.User;
import pro2.test_orm.vo.EmpVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by near on 2015/12/13.
 */
public class TestORM {
    @Test
    public void test() {
        ColumnInfo columnInfo = new ColumnInfo("name", "int", 0);
        TypeConvertor typeConvertor = new MysqlTypeConvertor();

        FieldGetSetInfo fieldGetSetInfo = FileUtils.createFieleGetSetInfo(columnInfo, typeConvertor);

        System.out.println(fieldGetSetInfo);
    }

    @Test
     public void test2() {
        Map<String, TableInfo> tables = TableContext.getTables();
        TableInfo tableInfo = tables.get("emp");
        /*String s = FileUtils.createPoCode(tableInfo, new MysqlTypeConvertor());
        System.out.println(s);*/

        /*FileUtils.createPoFile(tableInfo, new MysqlTypeConvertor());

        TableContext.loadPOTables();
        System.out.println(TableContext.getPoTables().size());*/

        for(TableInfo table : tables.values()){
            FileUtils.createPoFile(table, new MysqlTypeConvertor());
        }
    }

    @Test
    public void test3() {
        Query query = new MysqlQuery();
        User user = new User();

        user.setID(106);
        user.setNAME("near");
        user.setPASSWORD("1111");
        query.insert(user);

        // query.delete(103, User.class);

        /*user.setID(105);
        user.setNAME("月神");
        user.setPASSWORD("999");
        query.update(user, new String[]{"NAME", "PASSWORD"});*/
    }

    @Test
    public void test4() {
        Query query = new MysqlQuery();
        /*List<User> users = new ArrayList<>();

        users = query.queryRows("select ID,PASSWORD from user where ID=?", User.class, new Object[]{105});
        System.out.println(users);*/

        // 连表查询
        List<EmpVO> empVOs = new ArrayList<>();
        String sql = "select emp.id, department.id pid, name, salary, hireDate, dname, address daddress from emp, department " +
                "where emp.id=department.id and emp.id=1";

        empVOs = query.queryRows(sql, EmpVO.class, null);
        System.out.println(empVOs);
    }

    @Test
    public void test5() {
        Query query = new MysqlQuery();

        Object obj = query.queryValue("select count(*) from user", null);
        System.out.println(obj);
        System.out.println(TableContext.getPoTables().size());
    }
}