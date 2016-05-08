package pro.annotation.test;

/**
 * Created by near on 2015/12/7.
 */
@MyTable(value = "t_student")
public class Student {
    @MyField(columnName = "stuId", type = "varchar", length = 16)
    private String id;
    @MyField(columnName = "stuName", type = "varchar", length = 32)
    private String name;
    @MyField(columnName = "stuAge", type = "int", length = 3)
    private int age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Student() {
    }

    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
