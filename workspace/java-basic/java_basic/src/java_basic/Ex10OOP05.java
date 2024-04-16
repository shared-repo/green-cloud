package java_basic;

public class Ex10OOP05 {

	public static void main(String[] args) {

		// Employee4 클래스의 인스턴스 10개 만들어서 배열에 저장
		Employee5[] employees = new Employee5[10]; // Employee4 참조 변수의 배열 만들기 ( Employee4의 인스턴스는 생성되지 않습니다. )
		for (int i = 0; i < employees.length; i++) {
			employees[i] = new Employee5(); // 배열의 각 요소에 인스턴스 할당
			employees[i].setEmpNo((i + 1));
			employees[i].setName("Employee " + (i + 1));
			employees[i].setDept("IT");
			employees[i].count = i + 1;
		}
		
		// employees[2].count = 100;
		Employee5.count = 100;
		
		for (int i = 0; i < employees.length; i++) {
			System.out.println(employees[i].info());
		}
		
	}
}

class Employee5 {
	
	// static 멤버
	// 1. 모든 인스턴스가 공유하는 멤버 -> 한 인스턴스에서 값을 변경하면 모든 인스턴스에 영향을 미칩니다.
	// 2. 필드와 메서드에 모두 적용 가능
	// 3. static 메서드에서는 인스턴스 멤버를 사용할 수 없습니다. ( 인스턴스 메서드에서는 static 멤버를 사용할 수 있습니다. )
	// 4. static 멤버는 클래스의 멤버로 static 멤버의 사용은 클래스를 통해 이루어집니다.
	
	static int count; // 전체 직원수 저장 변수
	static int getCount() {
		// System.out.println(empNo); // 오류 : static 메서드에서 인스턴스 멤버 사용 불가능
		return count;
	}	
	// 위의 멤버는 정적(static) 멤버
	
	// 아래의 멤버는 인스턴스 멤버
	private int empNo;
	private String name;
	private String dept;
	
	public Employee5() {
		System.out.println("전달인자 없는 생성자 메서드 호출");
	}
	
	public Employee5(int empNo, String name, String dept) {
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
		String info = String.format("[%d][%s][%s][count:%d]", empNo, name, dept, count); // 문자열 생성
		return info;
	}

}






