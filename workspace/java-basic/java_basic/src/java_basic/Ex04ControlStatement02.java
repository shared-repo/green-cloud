package java_basic;

public class Ex04ControlStatement02 {

	public static void main(String[] args) {
		
		// 계산기 만들기
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		// 1. 숫자 입력 -> 변수에 저장		
		System.out.print("첫 번째 숫자 : ");
		int operand1 = scanner.nextInt(); // 버퍼에서 숫자 읽어오기
		scanner.nextLine(); // 버퍼에서 enter 읽어오기
		
		// 2. 연산자 입력 (+, -, *, /, %) -> 변수에 저장
		System.out.print("연산자 (+,-,*,/,%) : ");
		String op = scanner.nextLine();
		// String op = scanner.next();
		
		// 3. 숫자 입력 -> 변수에 저장
		System.out.print("두 번째 숫자 : ");
		int operand2 = scanner.nextInt();
		
		// 테스트 : 입력 내용 출력
		// System.out.printf("%d %s %d\n", operand1, op, operand2);
		// ------------------------------------
		
		// 4. 연산 수행 -> 결과를 변수에 저장
		double result = 0;
		boolean valid = true;
		switch (op) {
		case "+": result = operand1 + operand2; break;
		case "-": result = operand1 - operand2; break;
		case "*": result = operand1 * operand2; break;
		case "/": result = operand1 / operand2; break;
		case "%": result = operand1 % operand2; break;
		default : valid = false;
		}
		
		// 5. 결과 출력
		if (valid) {
			System.out.printf("%d %s %d = %f\n", operand1, op, operand2, result);
		} else {
			System.out.println("지원하지 않는 연산자입니다.");
		}
		

	}

}

















