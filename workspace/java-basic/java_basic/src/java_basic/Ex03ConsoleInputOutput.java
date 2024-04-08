package java_basic;

public class Ex03ConsoleInputOutput {

	public static void main(String[] args) {
		
		// 콘솔 입력기 만들기
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		System.out.print("이름을 입력하세요 : "); // 화면에 내용 출력 ( 출력 끝에 엔터 입력 없음 )
		String name = scanner.nextLine(); // 엔터를 입력할 때까지의 내용 읽고 문자열 반환
		
		System.out.print("나이를 입력하세요 : "); // 화면에 내용 출력
		int age = scanner.nextInt(); // 엔터를 입력할 때까지의 내용 읽고 정수로 변환
		
		System.out.println("[" + name + "][" + age + "]"); 	// 화면에 내용 출력  ( 출력 끝에 엔터 입력 있음 )
		System.out.printf("[%s][%d]\n", name, age); 		// 화면에 내용 출력
		
		// 출력 서식
		// %s : 문자열
		// %d : 정수
		// %f : 부동소수점
		// %c : 문자
		// %b : boolean

	}

}
