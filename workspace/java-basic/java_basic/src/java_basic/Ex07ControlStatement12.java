package java_basic;

public class Ex07ControlStatement12 {

	public static void main(String[] args) {
		// 로또 당첨 예상번호 추출기 만들기
		
		// 1. 1 ~ 45, 중복되지 않는 Random 숫자 6개 뽑기 -> 배열에 저장
		int[] numbers = new int[6];
		for (int i = 0; i < numbers.length; i++) {
			
			numbers[i] = (int)(Math.random() * 45) + 1; // 1 ~ 45, random
			//중복 검사
		}
		
		// 2. 
		// 3.
		// 4. 결과 출력
		System.out.print("당첨 예상 번호 : ");
		for (int i = 0; i < numbers.length; i++) {
			System.out.printf("[%2d]", numbers[i]);
		}
		System.out.println();
		// 5.

	}

}
