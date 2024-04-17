public class Ex10OOP09 {

	public static void main(String[] args) {
		
		// 1. 상속 관계의 클래스인 경우 참조 타입과 인스턴스 타입이 다를 수 있습니다.
		//    부모타입 참조변수 = 자식타입의 인스턴스 관계만 가능   ( 자식타입 참조 = 부모타입 인스턴스 관계는 불가능 )
		
		Parent5 p1 = new Child5();			// 부모타입 참조변수 = 자식타입 인스턴스  ( 암시적 형변환 )
		// p1.b = 10; // 오류 : 실제 Child5의 인스턴스라도 Parent5의 참조로는 Parent5의 멤버만 사용할 수 있습니다.
				
		// Child5 c1 = new Parent5();			// 컴파일 오류 : 부모타입 참조변수 = 자식타입 인스턴스
		// Child5 c1 = (Child5)new Parent5();	// 실행 오류 :  부모타입 참조변수 = 자식타입 인스턴스    ( 명시적 형변환 )
		
		Child5 c2 = (Child5)p1;
		
		Parent5 p2 = new Parent5();
		if (p2 instanceof Child5) { // 형변환 가능 여부 확인 (안전한 형변환) : p1이 Child5 or Child5의 자식 타입인지 확인
			Child5 c3 = (Child5)p2;
		} else {
			System.out.println("형변환 불가능");
		}
		
		/////////////////////////////////////////////////////
		
		// 2. 메서드 재정의
		Parent5 p3 = new Parent5();
		Child5 c3 = new Child5();
		p3.m();	// Parent5.m() 호출
		c3.m();	// Child5.m() 호출
		
		
		
		System.out.println("==========================");
		// 3. 참조타입과 인스턴스 타입이 다른 경우 재정의 메서드를 호출하면 --> 메서드 호출 기준 ? ... 인스턴스타입 기준 호출		
		Parent5 p4 = new Child5();
		p4.m(); // Parent5.m() or Child5.m()
		
		
		////////////////////////////////////////////////////////
		
		System.out.println("프로그램의 끝");
		

		
	}
}

class Parent5 {
	int a = 10;	
	
	void m() {
		System.out.println("Parent5.m()");
	}
	final void m2() {}
}

class Child5 extends Parent5 {
	
	int b = 20;	
	
	// 메서드 재정의 (method overriding) : 부모 클래스의 메서드를 형태는 유지하고 내용만 변경하는 구현
	void m() {
		// super.m(); // 부모 클래스의 m() 호출
		System.out.println("Child5.m()");
	}
	// void m2() {} // 오류 : 부모 클래스의 final 메서드는 재정의 불가능
}

















