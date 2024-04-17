

public class Ex10OOP02 {

	public static void main(String[] args) {
		
		// 클래스 사용 1. 인스턴스 만들기
		Employee2 emp; // 참조 변수 만들기
		emp = new Employee2(); // 인스턴스 만들기 + 인스턴스의 주소를 참조 변수에 저장
		
		// 클래스 사용 2. 멤버 접근 : .(dot) 연산자 사용
		// emp.empNo = 1;	// 오류 : private 멤버는 외부에서 접근 불가능
		emp.setEmpNo(0);
		emp.setName("Jane Doe");
		emp.setDept("HR");
		
		String info = emp.info();
		System.out.println(info);
		
	}

}

// 클래스 만들기 : 클래스 -> 설계도, 자료형
class Employee2 {
	
	// 접근 지정자
	// private : 외부에서 접근 불가능
	// public : 모든곳에서 접근 가능
	
	// 특성 : 필드 (변수)
	private int empNo;
	private String name;
	private String dept;
	
	public int getEmpNo() {	// 읽기 메서드 : get(is) + 대문자로 시작하는 변수이름
		return empNo;
	}
	public void setEmpNo(int empNo) {// 쓰기 메서드 : set + 대문자로 시작하는 변수이름
		if (empNo < 1) {
			System.out.println("유효하지 않은 사번입니다.");
			return;
		}
		this.empNo = empNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}

	// 기능 : 메서드 ( 클래스의 멤버 변수를 처리하는 코드 )
	public String info() {
		//System.out.printf("[%d][%s][%s]", empNo, name, dept); // 출력
		String info = String.format("[%d][%s][%s]", empNo, name, dept); // 문자열 생성
		return info;
	}

}






