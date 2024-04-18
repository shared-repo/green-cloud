
public class Ex10OOP13 {

	public static void main(String[] args) {
		
		// 인터페이스는 인스턴스 생성 불가능 (참조 변수 선언 가능)
		TheInterface ti;
		// ti = new TheInterface(); // 오류 : 인터페이스의 인스턴스 생성 불가능
		ti = new TheImplementation(); // 인터페이스를 구현한 클래스의 인스턴스를 참조하는 방식으로 사용 ( 다형성 사용 전제 )
		ti.m();
		ti.m2();
		ti.dm();
		TheInterface.sm();
		
		
		
	}
}

// interface 구현 형식 : interface 이름 { ... }
interface TheInterface {
	
	// 1. 추상메서드	
	public abstract void m();
	void m2();// == public abstract void m2();
	
	// 2. static final : 상수
	public static final int V1 = 100;
	int V2 = 100; // == public static final int V2 = 100;
	
	// 3. default method ( 내용이 있는 메서드 )
	default void dm() { // == public default void dm() 
		System.out.println("This is TheInterface's default method");
		pdm(); // 인터페이스 내의 private 메서드 호출 가능
	}
	private void pdm() { // == private default void pdm() 
		System.out.println("This is TheInterface's private default method");
	}
	
	// 4. static method
	static void sm() { // == public static void sm()
		System.out.println("TheInterface's static method");
		psm(); // 인터페이스의 내의 private static 메서드 호출 가능
	}
	private static void psm() { // == public static void sm()
		System.out.println("TheInterface's private static method");
	}
	
}

// 인터페이스는 다른 클래스에 의해 구현되는 방식으로 사용
class TheImplementation implements TheInterface {

	// 추상메서드에의 재정의는 필수 (강제)
	@Override
	public void m() {
		System.out.println("theImplementation's m()");
	}
	@Override
	public void m2() {
		System.out.println("theImplementation's m2()");
	}
	
	// default method의 재정의는 선택적
//	@Override
//	public void dm() { }
	
}

// 인터페이스 사이에 상속 가능
interface TheInterface2 extends TheInterface {
	
	void m3();
}
interface TheInterface3 {
	void m4();
}
// 인터페이스는 다중 상속 허용
interface TheInterface4 extends TheInterface2, TheInterface3 {
	void m5();
}
// 인터페이스를 구현하는 클래스는 구현 대상 인터페이스가 상속한 메서드까지 재정의 해야 합니다.
class TheImplementation2 implements TheInterface4 {

	@Override
	public void m() {}
	@Override
	public void m2() {}
	@Override
	public void m3() {}
	@Override
	public void m4() {}
	@Override
	public void m5() {}
	
}
// 다중 인터페이스 구현 가능
class TheImplementation3 implements TheInterface, TheInterface3 {

	@Override
	public void m4() {}
	@Override
	public void m() {}
	@Override
	public void m2() {}
	
}




































