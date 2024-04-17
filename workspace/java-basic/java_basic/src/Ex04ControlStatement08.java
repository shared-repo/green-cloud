

public class Ex04ControlStatement08 {

	public static void main(String[] args) {
		
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
//		// 1. 사용자가 'y'를 입력하지 않으면('n'을 입력하면) 반복문 중단
		
//		String again = "y";
//		// while(again.equals("y")) { // 소문자 y일 때에만 반복
//		// while(again.equals("y") || again.equals("Y")) { // 대소문자 y일 때 반복
//		// while(again.toLowerCase().equals("y")) { // 대소문자 y일 때 반복
//		while(again.equalsIgnoreCase("y")) { // 대소문자 y일 때 반복
//			
//			System.out.println("반복 실행문 실행");
//			
//			System.out.print("계속할까요?(y or n) : ");
//			again = scanner.nextLine();
//			
//		}
//		
//		System.out.println("프로그램이 종료됩니다.");
		
		// 2. 사용자가 'y'를 입력하지 않으면('n'을 입력하면) 반복문 중단 2
		
//		while(true) { // 무한 반복
//			
//			System.out.println("반복 실행문 실행");
//			
//			System.out.print("계속할까요?(y or n) : ");
//			String again2 = scanner.nextLine();
//			
//			if (again2.equalsIgnoreCase("y") == false) {
//				break; // 반복문을 종료하는 명령
//			}
//			
//		}
		
		// 3. 사용자 입력 유효성 검사 ( do ~ while 문 연습 )
		//    사용자 숫자 입력 -> 0 ~ 100 이 아니면 다시 입력
		int number = -10;
//		while (number < 0 || number > 100) {
//			System.out.print("0 ~ 100 범위의 숫자 입력 : ");
//			number = scanner.nextInt();
//		}
		do {
			System.out.print("0 ~ 100 범위의 숫자 입력 : ");
			number = scanner.nextInt();
		} while (number < 0 || number > 100);
		
		System.out.printf("Number : %d\n", number);
		
		System.out.println("프로그램이 종료됩니다.");

		
		
	}

}

















