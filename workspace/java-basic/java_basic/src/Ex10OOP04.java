

public class Ex10OOP04 {

	public static void main(String[] args) {

		// Employee4 클래스의 인스턴스 10개 만들어서 배열에 저장
		Employee4[] employees = new Employee4[10]; // Employee4 참조 변수의 배열 만들기 ( Employee4의 인스턴스는 생성되지 않습니다. )
		for (int i = 0; i < employees.length; i++) {
			employees[i] = new Employee4(); // 배열의 각 요소에 인스턴스 할당
			employees[i].setEmpNo((i + 1));
			employees[i].setName("Employee " + (i + 1));
			employees[i].setDept("IT");
		}
		
		for (int i = 0; i < employees.length; i++) {
			System.out.println(employees[i]);
		}
		
	}

}

class Employee4 {
	
	private int empNo;
	private String name;
	private String dept;
	
	public Employee4() {
		System.out.println("전달인자 없는 생성자 메서드 호출");
	}
	
	public Employee4(int empNo, String name, String dept) {
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






