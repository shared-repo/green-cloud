package java_basic;

public class Ex08Method03 {

	public static void main(String[] args) {
		// 로또 당첨 예상번호 추출기 만들기
		
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		program: while (true) {
			System.out.println("***********************");
			System.out.println("* 1. 당첨 예상 번호 뽑기");
			System.out.println("* 2. 종료");
			System.out.println("***********************");		
			System.out.print("작업을 선택하세요 : ");
			String selection = scanner.nextLine();
			switch (selection) {
			case "1": 
				int[] numbers = new int[6];
				boolean valid = false;
				do {
					// 1. 1 ~ 45, 중복되지 않는 Random 숫자 6개 뽑기 -> 배열에 저장
					for (int i = 0; i < numbers.length; i++) {
						
						numbers[i] = (int)(Math.random() * 45) + 1; // 1 ~ 45, random
						//중복 검사
						for (int j = 0; j < i; j++) {
							if (numbers[i] == numbers[j]) { // 중복인 경우
								//i--;	// 현재 위치에서 다시 뽑기
								i = -1;	// 처음부터 다시 뽑기
								break;
							}
						}
					}
					
					// 2. 뽑힌 숫자의 평균 값이 20 ~ 26 범위에 있는지 검사 -> 범위를 벗어나면 1부터 다시
					int sum = 0;
					for (int i = 0; i < numbers.length; i++) {
						sum += numbers[i];
					}
					int avg = sum / numbers.length;
					valid = avg >= 20 && avg <= 26;
					
					// 3. 다른 당첨 확률 증가 로직
					
				} while (!valid);
				
				// 4. 결과 출력
				System.out.print("당첨 예상 번호 : ");
//				for (int i = 0; i < numbers.length; i++) {
//					System.out.printf("[%2d]", numbers[i]);
//				}
				for (int number : numbers) {
					System.out.printf("[%2d]", number);
				}
				System.out.println();
				// 5.
				break;
			case "2": 
				System.out.println("행운을 빕니다.");
				System.out.println("프로그램을 종료합니다.");
				// return;
				break program;
			default:
				System.out.println("지원하지 않는 명령입니다.");
			}
		}
		
		


	}

}
