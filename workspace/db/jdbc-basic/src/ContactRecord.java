import java.util.Date;

public record ContactRecord(int no, String name, String phone, String email, Date regiDate) {
	
	// 타입 전달인자는 자동으로 필드로 구성
	// 필드에 대한 getter 자동 생성 : name()
	// toString() 자동 재정의
	// equals() 자동 재정의 : 모든 필드의 값이 일치할 때 True 반환
	// hashCode() 자동 재정의 : equals()와 같은 방식으로 동작하도록 구현
	
	// 생성자 overloading : 내부에서 반드시 전체 데이터를 사용하는 생성자를 호출해야 합니다.
	public ContactRecord(String name, String phone, String email) {
		this(-1, name, phone, email, null);
	}
	
}
