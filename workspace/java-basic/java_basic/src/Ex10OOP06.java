

public class Ex10OOP06 {

	public static void main(String[] args) {
		
	}
}

class Constant {
	
	int a = 10; // 필드 초기화
	final int B1 = 10; // 필드 초기화
	final int B2;
	// final int B3; // 오류 : 필드 초기화 또는 생성자 초기화 둘 중 하나는 반드시 필요 
	// static final int C1; // 오류 : static final은 반드시 필드 초기화 필요
	static final int C2 = 10;
	
	public Constant() {
		a = 20; // 생성자 초기화
		// B1 = 20; // 오류 : 필드 초기화된 final 변수는 생성자 초기화 불가능
		B2 = 20; // 필드 초기화되지 않은 final 변수는 생성자 초기화 가능		
		// C1 = 20; // 오류 : static final은 생성자 메서드에서 초기화 불가능
	}
	
	public void m() {
		a = 30; // 대입, 할당
		// B1 = 30; // 오류 : final 변수는 값 변경 불가능
		// B2 = 30; // 오류 : final 변수는 값 변경 불가능
		// B3 = 30; // 오류 : final 변수는 값 변경 불가능
		// C1 = 30; // 오류 : static final 변수는 값 변경 불가능
		// C2 = 30; // 오류 : static final 변수는 값 변경 불가능
		
	}
		
}

















