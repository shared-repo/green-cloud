package java_basic;

public class Ex04ControlStatement05 {

	public static void main(String[] args) {
		
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		// 가위바위보 게임 만들기 
		
		// 1. 컴퓨터 번호 선택 (난수 1, 2, 3) -> 변수에 저장
		int com = (int)(Math.random() * 3) + 1; // 1, 2, 3
		
		// 2. 사용자 번호 입력(1:가위, 2:바위, 3:보) -> 변수에 저장
		System.out.print("가위(1), 바위(2), 보(3)를 선택하세요 : ");
		int you = scanner.nextInt();
		
		// 3. 승패 계산 -> 변수에 저장
		String result = "";
		if ((you == 1 && com == 3) || 
			(you == 2 && com == 1) || 
			(you == 3 && com == 2)) {
			result = "이겼습니다.";
		} else if (you == com) {
			result = "비겼습니다.";
		} else {
			result = "졌습니다.";
		}
		
		// 4. 출력
		System.out.printf("[컴퓨터 : %d][사용자 : %d][결과 : %s]\n", com, you, result);
		
		scanner.close(); // 자원 반환
		
		int x = (int)(Math.random() * 100) + 1;
		
		// 참고 : 삼항연산자 -> 단순 if ~ else의 약식 표현
		
//		String m = "";
//		if (x % 2 == 0) {
//			m = "짝수입니다.";
//		} else {
//			m = "홀수입니다.";			
//		}
		
		String m = x % 2 == 0 ? "짝수입니다." : "홀수입니다.";
		
		System.out.println(m);
		

	}

}

















