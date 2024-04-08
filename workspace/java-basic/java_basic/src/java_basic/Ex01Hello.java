package java_basic;

public class Ex01Hello {

	public static void main(String[] args) {
		
		System.out.println("Hello, Java World !!!");
		System.out.println("My Java Program !!!");
		System.out.println("Bye");
		
		doTest();
		
		int a = 10;
		System.out.println('a'); // 문자 a를 출력하려면?
		
		int x = 10; //리터럴
		char c = 'a';
		double d = 1.11;
		
		
		System.out.println("안녕하세요\n반갑습니다");
		
		
	}
	
	/**
	 * 문서 주석을 설명하기 위한 테스트 메서드<br>
	 * 주석의 내용이 도움말 형태로 표시됩니다.
	 */
	public static void doTest() {
		System.out.println("Test Method");
	}

}
