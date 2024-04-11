package java_basic;

public class Ex07ControlStatement12 {

	public static void main(String[] args) {
		// 로또 당첨 예상번호 추출기 만들기
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
			// 3.
		} while (!valid);
		
		// 4. 결과 출력
		System.out.print("당첨 예상 번호 : ");
		for (int i = 0; i < numbers.length; i++) {
			System.out.printf("[%2d]", numbers[i]);
		}
		System.out.println();
		// 5.

	}

}
