public class Ex10OOP10 {

	public static void main(String[] args) {
		
	}

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
