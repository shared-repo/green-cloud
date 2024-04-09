package java_basic;

public class Ex04ControlStatement08 {

	public static void main(String[] args) {
		
		// 1. 사용자가 'y'를 입력하지 않으면('n'을 입력하면) 반복문 중단
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		String again = "y";
		// while(again.equals("y")) { // 소문자 y일 때에만 반복
		// while(again.equals("y") || again.equals("Y")) { // 대소문자 y일 때 반복
		// while(again.toLowerCase().equals("y")) { // 대소문자 y일 때 반복
		while(again.equalsIgnoreCase("y")) { // 대소문자 y일 때 반복
			
			System.out.println("반복 실행문 실행");
			
			System.out.print("계속할까요?(y or n) : ");
			again = scanner.nextLine();
			
		}
		
		System.out.println("프로그램이 종료됩니다.");

		
		
	}

}

















