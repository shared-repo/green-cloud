package java_basic;

public class Ex05ReferenceType {

	public static void main(String[] args) {
		
		int pv = 10; // primitive type 변수
		
		java.util.Scanner scanner; // 참조형 변수 만들기 (주소를 저장하는 변수 만들기)
		scanner = new java.util.Scanner(System.in); // 값을 저장하는 변수 만들기 (인스턴스 만들기)
		
		
		java.util.Random r;	// 참조형 변수 만들기 (주소를 저장하는 변수 만들기)
		r = new java.util.Random(); // 값을 저장하는 변수 만들기 (인스턴스 만들기)
		
		int rv = r.nextInt(1, 10);
		
		// String s = "Hello";
		String s;
		s = new String("Hello");
		System.out.println(s);
		
		int pv2 = 0;
		String s2 = null; // 값을 저장하는 변수의 주소를 갖지 않는 참조형 변수 만들기
		// System.out.println(s2.equals("hi")); // null 상태인 참조형 변수를 사용하면 NullPointerException 오류 발생
		
		// s2가 null인지 확인하는 방법
		if (s2 == null) {
			System.out.println("s2가 참조하는 값을 저장하는 변수(인스턴스)가 없습니다.");
		}
		
		
		
	}

}
