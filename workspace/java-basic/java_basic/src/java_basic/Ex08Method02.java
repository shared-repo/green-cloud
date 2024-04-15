package java_basic;

public class Ex08Method02 {

	public static void main(String[] args) {
		
		//sum(10, 20);
		int result = sum(10, 20);	// 전달인자가 2개인 sum 호출
		System.out.println(result);
		
		int result2 = sum(10, 20, 30); // 전달인자가 3개인 sum 호출
		System.out.println(result2);
		
		int result3 = sum2(10, 20);
		System.out.println(result3);
		
		result3 = sum2(10, 20, 30, 40, 50);
		System.out.println(result3);
		
		result3 = sum2(10, 20, 30, 40, 50, 60, 70, 80, 90);
		System.out.println(result3);
		
	}
	
	static int sum(int a, int b) {	// method signature
	
		int result = a + b;
		return result; 	// return : 메서드를 즉시 종료하고 호출한 곳으로 이동
						// return value : 메서드를 즉시 종료하고 value를 호출한 곳으로 반환 
	}
	// 이름은 같지만 전달인자의 종류(자료형)와 갯수가 다르면 다른 메서드로 처리 --> 메서드 오버로딩
	static int sum(int a, int b, int c) {	// method signature
		
		int result = a + b + c;
		return result; 	// return : 메서드를 즉시 종료하고 호출한 곳으로 이동
						// return value : 메서드를 즉시 종료하고 value를 호출한 곳으로 반환 
	}
	// 이름은 같지만 전달인자의 종류(자료형)와 갯수가 다르면 다른 메서드로 처리 --> 메서드 오버로딩
	static double sum(double a, int b, int c) {	// method signature
		
		double result = a + b + c;
		return result; 	// return : 메서드를 즉시 종료하고 호출한 곳으로 이동
						// return value : 메서드를 즉시 종료하고 value를 호출한 곳으로 반환 
	}
	
	
	static int sum2(int ... data) { // ... : 가변 인자 배열 -> 전달인자의 갯수와 상관 없이 모든 전달인자를 배열로 수신
		int result = 0;
		for (int d : data) {
			result += d;
		}
		return result;
	}

	

	
	

	

}








