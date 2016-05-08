package pro2.test_orm.vo;

import java.text.SimpleDateFormat;

/**
 * Value Object
 */
public class EmpVO {
	private java.util.Date hireDate;
	private String name;
	private Integer pid;
	private Integer id;
	private Double salary;
	private String dname;
	private String daddress;

	public java.util.Date getHireDate(){ return hireDate; }

	public String getName(){ return name; }

	public Integer getPid(){ return pid; }

	public Integer getId(){ return id; }

	public Double getSalary(){ return salary; }

	public void setHireDate(java.util.Date hireDate){ this.hireDate=hireDate; }

	public void setName(String name){ this.name=name; }

	public void setPid(Integer pid){ this.pid=pid; }

	public void setId(Integer id){ this.id=id; }

	public void setSalary(Double salary){ this.salary=salary; }

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getDaddress() {
		return daddress;
	}

	public void setDaddress(String daddress) {
		this.daddress = daddress;
	}

	@Override
	public String toString() {
		return "EmpVO{" +
				"hireDate=" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(hireDate) +
				", name='" + name + '\'' +
				", pid=" + pid +
				", id=" + id +
				", salary=" + salary +
				", dname='" + dname + '\'' +
				", daddress='" + daddress + '\'' +
				'}';
	}
}