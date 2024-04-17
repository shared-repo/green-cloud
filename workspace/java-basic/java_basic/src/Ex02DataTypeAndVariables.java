

public class Ex02DataTypeAndVariables {

	public static void main(String[] args) {
		
//		// 변수 만들기 : 자료형 변수이름;
//		int x; // 4byte 정수형 공간, 이름은 x
//		x = 10; // 변수에 데이터 저장
//		System.out.println(x);
//		
//		double y = 11.11; 	// 변수 만들기 + 데이터 저장 (초기화)
//		double z = y;		// y변수에 저장된 데이터를 읽어서 z 변수에 저장
//		System.out.println(z);
//		
//		int o = 1, p = 2, q = 3; // 같은 자료형의 변수는 한번에 선언 가능 ( 비권장 )
//		
//		int s;
//		// int t = s + 10; //오류 : 초기화되지 않은 변수 사용할 수 없습니다.
//		
//		int s2 = 100;
//		int t2 = s2 + 10; 


		//1. 변수 만들기 ( 자료형 이름; )
		int number; // int 자료형, number 이름을 가진 변수 만들기
		number = 10; // 변수에 값을 저장
		System.out.println("Number : " + number); // 변수의 값 읽기, 문자열 + 숫자 -> 문자열
		
		int aaa;
		aaa = 50;
		int bbb = 40; // 초기화 : 변수를 만드는 즉시 어떤 값을 저장
		int ccc = aaa + bbb;
		
		{ // 영역
			int localV = 100;
			System.out.println(localV); // 같은 지역(영역)에 만들어진 변수 사용 가능
			System.out.println(ccc); // 상위 영역 (부모 영역)에서 만들어진 변수 사용 가능
		}
		// System.out.println(localV); // 오류 : 다른 지역(영역)에 만들어진 변수 사용 불가능
		
		
//		MyType mt = new MyType(); // Reference Type
//		mt.d = 10;
//		mt.s = "Test Class";
		
		
		// 나이를 저장해야 합니다. 변수를 만들고 35세 나이를 저장하세요
		int age2;
		age2 = 35;
		// 이메일을 저장할 변수를 만들고 이메일 값을 저장하세요
		String email;
		email = "johndoe@example.com";
		
		//2. 변수 초기화
		double height = 185.4; // 변수 선언(만들기) + 값 저장
		
		//3. 초기화하지 않은 변수는 사용할 수 없습니다.
		int age;
		// System.out.println(age); // 오류
		
		
		//4. 변수의 영역
		{ // 영역 생성
			int x = 20; // 특정 영역에서 만들어진 변수는 그 영역 + 하위 영역에서만 사용 가능
			x = 30;
			{
				x = 40;
			}
		
		}
		// x = 40; // 다른 영역의 변수는 사용할 수 없습니다.

		//5. 자료형
		boolean b; // boolean  타입의 변수에는 true 또는 false만 저장할 수 있습니다.
		b = true;
		b = false;
		System.out.println("5. boolean : " + b);
		
		//6. 리터럴 : 코드에 사용된 값 표현 --> 10, 11.11, true, "문자열데이터", 'a'
		int color = 0xfefefe; // 16진수 정수 표현 : 0x로 시작
		System.out.println(color);
		
		//7. char 자료형
		int A = 10;
		char c = 'A'; // 문자 리터럴은 '를 사용해서 표현
		System.out.println("7. 문자표현 : " + c); // 문자 출력
		System.out.println("7. 숫자표현 : " + (int)c); // (자료형)변수 : 변수를 지정된 자료형으로 변경
		
		//8. 문자열 자료형
		String name = "John Doe"; // 문자열 리터럴은 " 사용
		System.out.println("8.Name : " + name);
		
		//9. Escape Sequence
		String message = "안녕하세요 \"홍길동\"님. \n만나서 반갑습니다.";
		System.out.println("9. escape sequence : " + message);
			
		//10. float vs double
		// float f1 = 3.14; // 오류 : 3.14는 double로 해석 -> float 변수에 저장할 수 없습니다.
		float f1 = 3.14f; // 3.14f는 float으로 해석
		System.out.println("10. float literal : " + f1);
		
		//11. 형변환 (Type Casting)
		double d = 10; // --> 10.0으로 변경 ( 데이터 손실이 없으면 암시적 형변환 )
		int x = (int)11.11; // --> 11로 변경 ( 데이터 손실이 발생하면 명시적 형변환 )
		String s = "value 1 : " + d + " / value 2 : " + x; // d와 x를 문자열로 형변환
		double d2 = d + x; // double + int -> double ( 범위가 넓을 쪽으로 변환 )
		// int y = (int)"100"; // 문자열을 숫자로 직접 형변환 할 수 없습니다.
		
		int y = Integer.valueOf("100"); // 문자열을 정수로 변환 ( parseInt와 같은 기능 )
		int y2 = Integer.parseInt("100"); // 문자열을 정수로 변환 ( valueOf와 같은 기능 )
		double d3 = Double.parseDouble("11.1123"); // 문자열을 실수로 변환
		
		System.out.println(s);
		System.out.println(y);
		System.out.println(y + y); // 숫자 + 숫자
		System.out.println("100" + "100"); // 문자열 + 문자열
		System.out.println(y2);
		
		// 1 -> 1 
		//  '1' -> 81
		// 100 + 100 -> 200
		// "100" + "100" -> "100100"
		

	}

}
