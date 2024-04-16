package java_basic;

public class Ex10OOP01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

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






