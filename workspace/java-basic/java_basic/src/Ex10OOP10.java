public class Ex10OOP10 {

	public static void main(String[] args) {
		
		// 다형성 사례 1 : 배열의 다형성 -> 하나의 배열에 서로 다르지만 유사한 객체를 담아서 일괄 처리
		
		// Rectangle 3개 Oval 3개, Line 3개를 만들어서 사용 -> 하나의 배열에 담아서 사용
		Shape[] shapes = new Shape[9]; // Shape형 참조변수 9개 생성
		for (int i = 0; i < shapes.length; i++) {
			switch (i % 3) {
			case 0: shapes[i] = new Rectangle(); break; // Shape 참조 = Rectangle 인스턴스;
			case 1: shapes[i] = new Oval(); break;
			case 2: shapes[i] = new Line(); break;
			}
		}		
		for (Shape shape : shapes) {
			// 동일한 코드가 상황에 따라 (여기서는 인스턴스에 따라) 다르게 동작하는 현상 : 다형성 (Polymorphism)
			shape.draw(); // 메서드는 인스턴스 타입 기준으로 호출
		}
		
		System.out.println("=================================");
		// 다형성 사례 2 : 전달인자의 다형성 -> 유사한 전달인자를 사용하는 여러 개의 overloading 메서드를 하나로 통합
		Rectangle r = new Rectangle();
		doDraw(r);
		Oval o = new Oval();
		doDraw(o);
		Line l = new Line();
		doDraw(l);
	}
	
	static void doDraw(Shape s) { // s = new Rectangle(), s = new Oval(), s = new Line()
		s.draw(); // 동일한 코드가 참조하는 인스턴스에 따라 다른 동작 수행 -> 다형성
	}
//	static void doDraw(Rectangle r) {
//		r.draw();
//	}
//	static void doDraw(Oval o) {
//		o.draw();
//	}
//	static void doDraw(Line l) {
//		l.draw();
//	}
}

class Shape {
	public void draw() {
		System.out.println("Draw Shape");
	}
}
class Rectangle extends Shape {
	public void draw() {
		System.out.println("Draw Rectangle");
	}
}
class Oval extends Shape {
	public void draw() {
		System.out.println("Draw Oval");
	}
}
class Line extends Shape {
	public void draw() {
		System.out.println("Draw Line");
	}
}
