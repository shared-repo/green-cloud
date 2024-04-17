

public class Ex04ControlStatement10 {

	public static void main(String[] args) {
		
		outer : while (true) {
			int a = 2;
			switch(a) {
			case 1: 
				System.out.println("break case");
				break; // break switch
			case 2: 
				System.out.println("break while");
				break outer; // outer 이름이 지정된 제어문 break ( 여기서는 while )
			}
			System.out.println("end of while");
			break; // break while
		}

	}

}
