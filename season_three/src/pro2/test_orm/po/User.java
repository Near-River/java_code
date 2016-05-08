package pro2.test_orm.po;

public class User {
	private String PASSWORD;

	private Integer ID;

	private String NAME;

	public String getPASSWORD(){ return PASSWORD; }

	public Integer getID(){ return ID; }

	public String getNAME(){ return NAME; }

	public void setPASSWORD(String PASSWORD){ this.PASSWORD=PASSWORD; }

	public void setID(Integer ID){ this.ID=ID; }

	public void setNAME(String NAME){ this.NAME=NAME; }

	@Override
	public String toString() {
		return "User{" +
				"PASSWORD='" + PASSWORD + '\'' +
				", ID=" + ID +
				", NAME='" + NAME + '\'' +
				'}';
	}
}