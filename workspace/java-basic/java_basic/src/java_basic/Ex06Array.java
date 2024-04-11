package java_basic;

public class Ex06Array {

	public static void main(String[] args) {
		
		// 1-1. 배열 만들기
		int[] ar1_1;	// 참조 변수 만들기
		ar1_1 = new int[10];	// 인스턴스 만들기 + 만들어진 인스턴스 주소를 참조 변수에 저장
		
		// 1-2. 배열 만들기
		int[] ar1_2 = new int[10];
		
		// 2-1. 배열 요소 사용
		int[] ar2_1 = new int[10];
		ar2_1[0] = 10;
		ar2_1[1] = 20;
		System.out.printf("[%d][%d]\n", ar2_1[0], ar2_1[1]);
		
		// 2-2. 배열 요소 사용
		int[] ar2_2 = new int[10];
		for (int i = 0; i < 10; i++) {
			ar2_2[i] = (int)(Math.random() * 900) + 100; // 100 ~ 1000 : 3자리 정수 난수
 		}
		
		for (int i = 0; i < 10; i++) {
			System.out.printf("[%d]\n", ar2_2[i]);
		}
		

	}

}
