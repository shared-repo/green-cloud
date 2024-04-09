package java_basic;

public class Ex04ControlStatement06 {

	public static void main(String[] args) {
		
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		// 1. 사용자 숫자 입력 (1 ~ 9)
		System.out.print("출력할 구구단의 단을 입력하세요 : ");		
		int dan = scanner.nextInt();
		
		// 2. 입력한 숫자에 해당하는 구구단 출력
		//    5 x 1 = 5
		//    5 x 2 = 10
		//    ...
		//    5 x 9 = 45
		for (int idx = 1; idx < 10; idx++) { // idx++    ==>      idx = idx + 1      ==>     idx += 1
			System.out.printf("%d x %d = %2d\n", dan, idx, dan * idx);
		}
		
		////////////////////////////
		
		// 구구단 전체 출력 (1단 ~ 9단)
		// 1 x 1 = 1  2 x 1 = 2    ...   9 x 1 = 9
		// 1 x 2 = 2  2 x 2 = 4    ...   9 x 2 = 18
		//...
		// 1 x 9 = 9  2 x 9 = 18   ...   9 x 9 = 81
		
		for (int v = 1; v < 10; v++) {
			for(int h = 1; h < 10; h++) {
				System.out.printf("%d x %d = %2d  ", h, v, h*v);		
			}
			System.out.println();
		}

	}

}

















