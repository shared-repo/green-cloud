package java_basic;

public class Ex10OOP03 {

	public static void main(String[] args) {
		
		int x;
		x = 10;
		int y = 20; // 초기화 : 변수를 만들면서 즉시 어떤 값을 저장
		
		Employee3 emp; // 참조 변수 만들기
		System.out.println("인스턴스 생성 전");
		// emp = new Employee3(); // 인스턴스 만들기 + 초기화 : 전달인자 없는 생성자 메서드 호출
		emp = new Employee3(1, "홍길동", "Administrator"); // 인스턴스 만들기 + 초기화 : 전달인자 있는 생성자 메서드 호출
		System.out.println("인스턴스 생성 후");
		
		String info = emp.info();
		System.out.println(info);
		
	}

}

class Employee3 {
	
	private int empNo;
	private String name;
	private String dept;
	
	// 생성자 메서드 : 인스턴스가 생성(new)될 때 자동으로 호출되는 메서드
	// 				메서드 이름은 클래스 이름과 동일
	//				결과형 없음
	//				오버로딩 가능 (전달인자의 자료형과 갯수를 다르게해서 여러 개의 생성자 만들 수 있습니다)
	public Employee3() {
		System.out.println("전달인자 없는 생성자 메서드 호출");
	}
	
	public Employee3(int empNo, String name, String dept) {
		System.out.println("전달인자 있는 생성자 메서드 호출");
		this.empNo = empNo;
		this.name = name;
		this.dept = dept;
	}

	public int getEmpNo() {	
		return empNo;
	}
	public void setEmpNo(int empNo) {
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

	public String info() {
		String info = String.format("[%d][%s][%s]", empNo, name, dept); // 문자열 생성
		return info;
	}

}






