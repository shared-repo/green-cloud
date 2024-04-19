import java.util.ArrayList;
import java.util.Date;

public class Ex12Generic {

	public static void main(String[] args) {
		
		// 1-1. ArrayList는 모든 자료형의 데이터를 저장할 수 있습니다. ( 이를 위해 내부에서 Object 형식으로 데이터 관리 )
		ArrayList al = new ArrayList();
		al.add(1);
		al.add("문자열");
		al.add(new Date());
		
		// 1-2. 데이터를 읽을 때 형변환 필요 ( 내부에서 Object 형식으로 데이터를 관리하기 때문 )
		int x = (int)al.get(0);
		// String s = (String)al.get(0); // 오류 : 형변환 오류
		
		// 2. Object 형식을 사용해서 여러 자료형의 데이터 저장
		TheObject obj1 = new TheObject();
		obj1.v = 10;
		obj1.v = "문자열";
		
		int x2 = (int)obj1.v; // 형변환 오류 발생
		
		// 3. Generic 형식을 사용해서 여러 자료형의 데이터 저장
		// TheGeneric<Integer, String> gobj1 = new TheGeneric<Integer, String>();
		TheGeneric<Integer, String> gobj1 = new TheGeneric<>();
		gobj1.v = 10;
		// gobj1.v = "hello"; // 오류 : Generic 타입은 변수 선언시점에 결정한 자료형으로 사용해야 합니다.
		gobj1.v2 = "hello";
		
		TheGeneric<String, Date> gobj2 = new TheGeneric<>();
		gobj2.v = "hello";
		gobj2.v2 = new Date();
		
		int x3 = gobj1.v; // Generic 타입을 읽을 때 형변환 필요 없음

	}
}

class TheObject {
	Object v;
}
class TheGeneric<T, E> { // 미확정 자료형 T, V를 사용하는 클래스 -> T, V는 참조변수 및 인스턴스를 만들 때 결정 
	
	T v;
	E v2;
	
}









