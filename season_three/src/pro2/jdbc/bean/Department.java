package pro2.jdbc.bean;

/**
 * Created by near on 2015/12/14.
 */
public class Department {
    private int id;
    private String dname;
    private String address;

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", dname='" + dname + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Department() {

    }

    public Department(int id, String dname, String address) {

        this.id = id;
        this.dname = dname;
        this.address = address;
    }
}
