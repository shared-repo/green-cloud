package java_basic;

public class Ex04ControlStatement07 {

	public static void main(String[] args) {

		for (int v = 0; v < 10; v++) {
			// for (int h = 0; h < 10; h++) {
			for (int h = 0; h < v + 1; h++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		System.out.println();System.out.println();
		
		for (int v = 0; v < 10; v++) {
			for (int s = 0; s < 10 - (v + 1); s++) {
				System.out.print(" ");
			}
			for (int h = 0; h < v + 1; h++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
	}

}

















