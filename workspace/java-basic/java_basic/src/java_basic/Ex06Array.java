package java_basic;

public class Ex06Array {

	public static void main(String[] args) {
		
		// 1-1. 배열 만들기
		int[] ar1_1;	// 참조 변수 만들기
		ar1_1 = new int[10];	// 인스턴스 만들기 + 만들어진 인스턴스 주소를 참조 변수에 저장
		
		// 1-2. 배열 만들기
		int[] ar1_2 = new int[10]; // 10개의 배열 요소의 값은?
		
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
		
		for (int i = 0; i < ar2_2.length; i++) { // 배열.length : 배열 요소의 갯수 반환
			System.out.printf("[%d]\n", ar2_2[i]);
		}
		
		System.out.println("==================================");
		// 3-1. 2차원 배열
		int[][] ar3 = new int[10][5]; // 각 요소가 int[5]인 10개 짜리 배열 만들기
		for (int r = 0; r < ar3.length; r++) {
			for (int c = 0; c < ar3[r].length; c++) {
				ar3[r][c] = (int)(Math.random() * 900) + 100; // 100 ~ 1000 : 3자리 정수 난수
			}
		}
		for (int r = 0; r < ar3.length; r++) {	// r : row 의미
			for (int c = 0; c < ar3[r].length; c++) {	// c : column 의미
				System.out.printf("[%3d]", ar3[r][c]);
			}
			System.out.println();
		}
		
		// 4-1. 배열 초기화 : 배열 인스턴스를 만들면서 즉시 값을 대입
		int a2;
		a2 = 10;
		int a = 10; // 초기화 : 변수를 만들면서 즉시 값을 대입
		
		int[] ar4_1 = new int[10];
		ar4_1[0] = 10;
		// int[] ar4_2 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[] ar4_2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		for (int i = 0; i < ar4_2.length; i++) {
			System.out.println(ar4_2[i]);
		}
		
		//int[][] ar4_3 = new int[][] {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}};
		int[][] ar4_3 = {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}};
		
		for (int i = 0; i < ar4_3.length; i++) {
			for (int j = 0; j < ar4_3[i].length; j++) {
				System.out.println(ar4_3[i][j]);
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
