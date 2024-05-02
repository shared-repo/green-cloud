import java.util.Date;

public class ContactDto {
	
	private int no;						// 자동증가 설정
	private String name;
	private String phone;
	private String email;
	private Date regiDate = new Date();	// default 설정

	public ContactDto() {}

	public ContactDto(String name, String phone, String email) {
		this.name = name;
		this.phone = phone;
		this.email = email;
	}	

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getRegiDate() {
		return regiDate;
	}

	public void setRegiDate(Date regiDate) {
		this.regiDate = regiDate;
	}

	public String toString() {
		return String.format("[%3d][%10s][%13s][%20s][%s]", no, name, phone, email, regiDate);
	}



}