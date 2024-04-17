

public class Ex08Method {

	public static void main(String[] args) {
		
		// 박스 그리기 ( 가로20 X 세로10 )
		// ********************
		// *                  *
		// *                  *
		// ...
		// ********************
		
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		System.out.print("박스 그리기에 사용할 문자 : ");
		String s = scanner.nextLine();
		System.out.print("박스의 너비 : ");
		int width  = scanner.nextInt();
		System.out.print("박스의 높이 : ");
		int height = scanner.nextInt();
		
		drawBox(s, width, height); // 메서드 호출 : 메서드의 코드가 실행되도록 요청
		// drawBox();
		
	}

	// 메서드 선언 (정의) : 메서드 만들기
	private static void drawBox(String s, int width, int height) {
		
		for (int h = 0; h < height; h++) { // 높이 : 10회 반복
			for (int w = 0; w < width; w++) { // 너비 : 20개 * 출력
				if (h == 0 || h == height - 1 || w == 0 || w == width - 1) {
					// System.out.print("*");
					System.out.print(s);
				} else {
					System.out.print(" ");
				}
				
			}
			System.out.println();
		}
	}

}








