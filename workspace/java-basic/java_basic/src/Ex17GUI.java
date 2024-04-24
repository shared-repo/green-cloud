import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
		setSize(600, 400);
		setResizable(false);
		setLayout(null); // 윈도우의 구성요소가 화면에 배치되는 기본 전략을 제거 --> 코드로 직접 배치 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 윈도우가 닫히면 프로그램을 종료하는 설정
		
		//////////////////////////////////////////////
		
		JButton btn1 = new JButton(); 	// 버튼 만들기
		btn1.setText("클릭해 주세요");		// 버튼에 표시되는 글
		btn1.setSize(300, 100);			// 버튼 크기
		btn1.setLocation(150, 100);
		
		// 이벤트 처리기 연결 방식 1. 별도의 클래스를 만들어서 적용
//		ButtonClickHandler handler = new ButtonClickHandler();
//		btn1.addActionListener(handler); // JButton을 클릭했을 때 호출할 객체(인스턴스) 등록
		
		// 이벤트 처리기 연결 방식 2. 익명 클래스 적용
//		btn1.addActionListener(
//			new ActionListener() { // ActionListener 인터페이스를 구현하는 이름 없는 클래스 만들기 + 그 클래스의 인스턴스 만들기
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					Container container = getContentPane(); // 윈도우의 클라이언트 영역 객체 반환
//					int r = (int)(Math.random() * 256);
//					int g = (int)(Math.random() * 256);
//					int b = (int)(Math.random() * 256);
//					Color c = new Color(r, g, b);
//					container.setBackground(c);	// 위도우 클라이언트 영역의 배경색 변환
//				}
//			} 
//		);
		
		// 이벤트 처리기 연결 방식 3. 람다 함수 사용
		btn1.addActionListener( (e) -> {
			Container container = getContentPane(); // 윈도우의 클라이언트 영역 객체 반환
			int r = (int)(Math.random() * 256);
			int g = (int)(Math.random() * 256);
			int b = (int)(Math.random() * 256);
			Color c = new Color(r, g, b);
			container.setBackground(c);	// 위도우 클라이언트 영역의 배경색 변환
		} );
		
		add(btn1);	// 만든 버튼을 윈도우에 부착

	}
	
	class ButtonClickHandler implements ActionListener { // ActionListener : JButton이 클릭될 때 호출할 클래스에 메서드에 대한 규격 적용
		@Override
		public void actionPerformed(ActionEvent e) {
			
			// JOptionPane.showMessageDialog(null, "클릭해 주셔서 감사합니다.");
			
			Container container = getContentPane(); // 윈도우의 클라이언트 영역 객체 반환
			// container.setBackground(Color.GREEN);	// 위도우 클라이언트 영역의 배경색 변환
			int r = (int)(Math.random() * 256);
			int g = (int)(Math.random() * 256);
			int b = (int)(Math.random() * 256);
			Color c = new Color(r, g, b);
			container.setBackground(c);	// 위도우 클라이언트 영역의 배경색 변환
			
		} 		
	}
	
}






















