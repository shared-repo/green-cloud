public class Ex10OOP08 {

	public static void main(String[] args) {
		Child c1 = new Child(); // Parent 생성자 호출 -> Child 생성자 호출
		c1.pm(); // Child가 pm()을 상속받았으므로 호출 가능
	}
}

class Parent2 {}
class Parent {

	private int pno = 1;
	String pname;
	
	Parent() {
		System.out.println("Parent의 전달인자 없는 생성자 메서드");
	}
	Parent(int pno) {
		System.out.println("Parent의 전달인자 있는 생성자 메서드");
	}
	
	void pm() {
		System.out.println("Parent.pm()");
	}	
}

// class Child2 extends Parent, Parent2 {} // 오류 : 다중상속 불가능 (부모 클래스는 반드시 1개)

class Child extends Parent { // extends Parent : Parent의 멤버를 포함
	
	String cname;
	
	Child() {
		// super(); // 부모 클래스의 전달인자 없는 생성자 메서드 호출하는 구문 (생략 가능)
		super(10); // 부모 클래스의 전달인자 있는 생성자 메서드 호출하는 구문 (생략 불가능)
		System.out.println("Child의 전달인자 없는 생성자 메서드");
	}
	
	void cm() {
		System.out.println("Child.cm()");
		pname = "test in child";
		// pno = 10; // 오류 : 부모 클래스의 private 멤버는 사용 불가능	
		
	}
	
}


final class Parent3 {}
// class Child2 extends Parent3 {} // 오류 : final 클래스를 상속할 수 없습니다.













