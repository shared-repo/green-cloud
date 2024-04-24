import javax.swing.JFrame;

public class Ex17GUI {
	
	public static void main(String[] args) {

		MyWindow window = new MyWindow();
				
		window.setVisible(true); // 윈도우를 화면에 표시하는 명령

	}
	
}

class MyWindow extends JFrame {
	
	public MyWindow() { // 생성자 -> 초기화 수행, GUI 프로그램에서는 보통 윈도우에 대한 초기화 수행
		initUI();
	}
	
	private void initUI() {
		setTitle("Hello, Java GUI");
		setSize(500, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 윈도우가 닫히면 프로그램을 종료하는 설정
	}
	
}







