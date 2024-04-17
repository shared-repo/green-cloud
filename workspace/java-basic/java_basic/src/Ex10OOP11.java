public class Ex10OOP11 {

	public static void main(String[] args) {
		
		// 1. 
		TheAbstract ta;	// 추상클래스의 참조 변수 생성 가능
		// ta = new TheAbstract(); // 오류 : 추상클래스는 인스턴스 생성 불가능
		
		// 2.
		TheAbstract2 ta2 = new TheImplement();
		ta2.abstractMethod();
		
	}
	
}

// 추상 클래스 : 인스턴스 생성 불가능한 클래스 ( new 사용 불가능 )
abstract class TheAbstract {	
}

// class TheAbstract2 { // 오류 : 추상메서드를 포함하는 클래스는 반드시 추상클래스로 구현
abstract class TheAbstract2 {
	// 추상 메서드 : 본문이 없는 메서드 ( { ... }가 없는 메서드
	abstract void abstractMethod();
	
	//추상클래스는 비추상 메서드와 필드를 가질 수 있습니다.
	int x = 10;
	void m() {}
}

// 추상클래스는 다른 클래스가 상속해서 사용
// 추상클래스를 상속한 클래스는 반드시 추상메서드를 재정의 해야하거나 추상클래스로 만들어야 합니다.
class TheImplement extends TheAbstract2 {

	@Override
	void abstractMethod() {
		System.out.println("추상메서드 재정의");
		
	}
	
}










