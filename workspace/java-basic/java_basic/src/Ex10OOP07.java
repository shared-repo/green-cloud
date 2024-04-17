import com.greencloud.MyClass2; // 이후 코드에서 MyClass2는 com.greencloud.MyClass2 입니다.
import com.greencloud.javabasic.OtherClass1;

public class Ex10OOP07 {

	public static void main(String[] args) {
		
		// MyClass mc = new MyClass(); // 오류 : 클래스를 사용할 때 패키지이름을 표시해야 합니다.
		com.greencloud.javabasic.MyClass mc = new com.greencloud.javabasic.MyClass();
		
		MyClass2 mc2 = new MyClass2(); // import 구문을 사용해서 패키지명 생략 가능
		
		///////////////////////////////////////////////////////////////////
		
		// 아래 코드 오류 : 다른 패키지의 public이 아닌 클래스 사용 불가능
		// com.greencloud.javabasic.OtherClass2 oc2 = new com.greencloud.javabasic.OtherClass2();
		
		OtherClass1 oc1 = new OtherClass1();
		oc1.x = 20; // public 멤버 접근 가능
		// oc1.y = 20; // private 멤법 접근 불가능
		// oc1.z = 20; // default 멤버 접근 불가능 (다른 패키지 이므로)
		
		com.greencloud.javabasic.MyClass mc1_2;
		// mc1_2 = new MyClass(10); // 오류 : private 생성자 메서드는 다른 클래스에서 호출 불가능 -> 다른 클래스에서 인스턴스 생성 불가능
		
	}
}
