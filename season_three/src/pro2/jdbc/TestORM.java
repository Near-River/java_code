package pro2.jdbc;

import org.junit.Test;
import pro2.jdbc.bean.Department;
import pro2.jdbc.bean.Employer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by near on 2015/12/14.
 */
public class TestORM {
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    @Test
    public void test() {
        String sql = null;
        List<Employer> employerList = new ArrayList<>();
        try {
            connection = SqlUtils.getConnection();

            sql = "select id, name, salary, hireDate from emp";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Employer employer = new Employer(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getDate(4));
                employerList.add(employer);
            }

            for (Employer employer : employerList) {
                System.out.println(employer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtils.close(preparedStatement, connection);
        }
    }

    @Test
    public void test2() {
        String sql = null;
        List<Employer> employerList = new ArrayList<>();
        try {
            connection = SqlUtils.getConnection();

            sql = "select name, salary, hireDate, dname, address from emp, department where emp.id=department.id";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Employer employer = new Employer();
                employer.setName(resultSet.getString(1));
                employer.setSalary(resultSet.getDouble(2));
                employer.setHireDate(resultSet.getDate(3));

                Department department = new Department();
                department.setDname(resultSet.getString(4));
                department.setAddress(resultSet.getString(5));
                employer.setDepartment(department);

                employerList.add(employer);
            }

            for (Employer employer : employerList) {
                System.out.println(employer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtils.close(preparedStatement, connection);
        }
    }
}
