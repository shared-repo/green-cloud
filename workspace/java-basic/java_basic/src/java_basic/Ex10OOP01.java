package java_basic;

public class Ex10OOP01 {

	public static void main(String[] args) {
		
		// 클래스 사용 1. 인스턴스 만들기
		Employee emp; // 참조 변수 만들기
		emp = new Employee(); // 인스턴스 만들기 + 인스턴스의 주소를 참조 변수에 저장
		
		// 클래스 사용 2. 멤버 접근 : .(dot) 연산자 사용
		emp.empNo = 1;
		emp.name = "John Doe";
		emp.dept = "Sales";
		
		String info = emp.info();
		System.out.println(info);
		
	}

}

// 클래스 만들기 : 클래스 -> 설계도, 자료형
class Employee {
	
	// 특성 : 필드 (변수)
	int empNo;
	String name;
	String dept;
	
	// 기능 : 메서드 ( 클래스의 멤버 변수를 처리하는 코드 )
	String info() {
		//System.out.printf("[%d][%s][%s]", empNo, name, dept); // 출력
		String info = String.format("[%d][%s][%s]", empNo, name, dept); // 문자열 생성
		return info;
	}

}






