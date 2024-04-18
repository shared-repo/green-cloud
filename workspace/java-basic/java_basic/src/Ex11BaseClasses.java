import java.util.Date;

public class Ex11BaseClasses {

	public static void main(String[] args) {
		
		// 1. Object는 모든 클래스의 부모 클래스 -> Object 참조는 모든 인스턴스를 참조할 수 있습니다.
		MyObject obj = new MyObject();
		Object obj2 = new MyObject();
		obj2 = "hello";
		obj2 = new java.util.Date();
		
		// 2. Object.equals() : 객체의 동일성 비교 메서드
		MyObject obj21 = new MyObject();
		obj21.x = 100;
		MyObject obj22 = new MyObject();
		obj22.x = 100;
		System.out.println(obj21 == obj22);	// 비교 연산자는 항상 참조를 비교
		System.out.println(obj21.equals(obj22)); // equals는 기본 구현은 참조 비교, 재정의 하는 경우 재정의 구현에 따름 (값 비교)
		
		// 3. Object.hashcode() : 객체의 동일성 비교 메서드 ( 동일성 비교에 사용할 객체의 대표 값을 정수로 반환하는 메서드 )
		System.out.println(obj21.hashCode() == obj22.hashCode()); // hashcode의 기본 구현은 주소 반환, 재정의 하는 경우 재정의 구현에 따름
		
		//4. Object.toString() : 객체의 정보를 간단한 문자열로 반환하는 메서드
		System.out.println(obj21.toString()); // toString의 기본 구현은 클래스이름@주소, 재정의 하는 경우 재정의 구현을 따름
		System.out.println(obj22); // 문자열이 필요한 문맥이면 자동으로 toString 호출
		
		//5. Object.getClass() : 객체의 타입(클래스) 정보를 Class 형식의 객체에 담아서 반환
		Class claz = obj21.getClass();
		System.out.println(claz);
		
		//////////////////////////////////////////
		
		System.out.println(System.currentTimeMillis());
		System.out.println(System.nanoTime());
		
		Date d = new Date(System.currentTimeMillis()); // milliseconds 값으로 날짜 객체 만들기
		System.out.println(d);
		
		//////////////////////////////////////////
		
		//7-1-1
		int x = 10;
		Object o = x;
		System.out.println(o);
		//7-1-2
		int x2 = 10;
		Integer i = Integer.valueOf(x2);
		Object o2 = i;
		System.out.println(o2);
		
		// 7-2-1
		int y = (int)o;
		System.out.println(y);
		// 7-2-2
		Integer i2 = (Integer)o2;
		int y2 = i2.intValue();
		System.out.println(y2);
		
		// 문자열을 특정 타입으로 변환
		System.out.println("100" + "100");
		System.out.println(Integer.valueOf("100") + Integer.parseInt("100"));
		System.out.println(Double.valueOf("100.1") + Double.parseDouble("100.5"));
	}

}

// class MyObject { // == class MyObject extends Object
class MyObject extends Object { // Object == java.lang.Object, java.lang 패키지 표시는 생략가능
	
	public int x;
	
	@Override
	public boolean equals(Object obj) { // 전달인자는 비교 대상 객체	
		MyObject other = (MyObject)obj;
		return this.x == other.x;	
	}	
	@Override
	public int hashCode() {
		return x;
	}
	@Override
	public String toString() {
		return String.format("[X: %d]", x);
	}
	
	
}

















