package pro2.test_orm.po;

public class Emp {
	private java.util.Date hireDate;

	private String name;

	private Integer pid;

	private Integer id;

	private Double salary;

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

}