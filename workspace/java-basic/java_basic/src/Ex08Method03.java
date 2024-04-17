

public class Ex08Method03 {

	static java.util.Scanner scanner = new java.util.Scanner(System.in);
	
	public static void main(String[] args) {
	
		program: while (true) {
		
			String selection = selectTask();

			switch (selection) {
			case "1": 
				int[] numbers = new int[6];
				boolean valid = false;
				do {
					// selectBasicNumbers(numbers); // main에서 만든 변수에 selectBasicNumbers2에서 뽑은 데이터를 저장한 값 사용
					numbers = selectBasicNumbers2();// selectBasicNumbers2에서 뽑은 데이터를 return으로 반환한 값 사용 
					valid = checkAverage(numbers);										
				} while (!valid);
				
				showNumbers(numbers);
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
	
	static String selectTask() {
		System.out.println("***********************");
		System.out.println("* 1. 당첨 예상 번호 뽑기");
		System.out.println("* 2. 종료");
		System.out.println("***********************");		
		System.out.print("작업을 선택하세요 : ");
		String selection = scanner.nextLine();
		
		return selection;
	}
	
	static void showNumbers(int[] numbers) {
		System.out.print("당첨 예상 번호 : ");
		for (int number : numbers) {
			System.out.printf("[%2d]", number);
		}
		System.out.println();
	}
	
	static boolean checkAverage(int[] numbers) {
		int sum = 0;
		for (int i = 0; i < numbers.length; i++) {
			sum += numbers[i];
		}
		int avg = sum / numbers.length;
		boolean valid = avg >= 20 && avg <= 26;
		return valid;
	}
	
	static void selectBasicNumbers(int[] numbers) {
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
	}
	static int[] selectBasicNumbers2() {
		int[] numbers = new int[6];
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
		return numbers;
	}

}























