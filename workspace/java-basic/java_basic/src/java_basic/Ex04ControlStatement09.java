package java_basic;

public class Ex04ControlStatement09 {

	public static void main(String[] args) {

		java.util.Scanner scanner = new java.util.Scanner(System.in);

		// 가위바위보 게임 만들기

		while (true) {

			// 1. 컴퓨터 번호 선택 (난수 1, 2, 3) -> 변수에 저장
			int com = (int) (Math.random() * 3) + 1; // 1, 2, 3

			// 2. 사용자 번호 입력(1:가위, 2:바위, 3:보) -> 변수에 저장
			System.out.print("가위(1), 바위(2), 보(3)를 선택하세요 : ");
			int you = scanner.nextInt();

			// 3. 승패 계산 -> 변수에 저장
			String result = "";
			if ((you == 1 && com == 3) || (you == 2 && com == 1) || (you == 3 && com == 2)) {
				result = "이겼습니다.";
			} else if (you == com) {
				result = "비겼습니다.";
			} else {
				result = "졌습니다.";
			}

			// 4. 출력
			System.out.printf("[컴퓨터 : %d][사용자 : %d][결과 : %s]\n", com, you, result);

			// 5. 게임 계속 여부 확인 및 처리
			System.out.print("계속할까요?(y or n) : ");
			String again = scanner.next();
			// if (again.equalsIgnoreCase("y") == false) { // 'y'를 입력하지 않았다면
			if (!again.equalsIgnoreCase("y")) { // 'y'를 입력하지 않았다면
				break;
			}

		} // end of while

		System.out.println("게임을 종료합니다.");

		scanner.close(); // 자원 반환

	}

}

// !true -> false
// !false -> true














