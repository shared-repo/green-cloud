import java.util.Date;

// 데이터베이스의 Employee 테이블의 데이터를 저장, 전달에 사용되는 클래스
public class EmployeeDto {
	
	private int empNo;
	private String firstName;
	private String lastName;
	private String gender;
	private Date birthDate;	
	private Date hireDate;
	
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	
	@Override
	public String toString() {
		return String.format("[%d][%s %s][%s][%s][%s]", empNo, firstName, lastName, gender, birthDate, hireDate);
	}

}
















