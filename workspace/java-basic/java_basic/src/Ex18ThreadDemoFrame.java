import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

//JFrame : 자바의 윈도우 구현 클래스
public class Ex18ThreadDemoFrame extends JFrame {
	
	public Ex18ThreadDemoFrame() {// 생성자 메서드 : 초기화, 클래스 이름과 동일, 결과형 없음
		
		setTitle("쓰레드 테스트 윈도우");
		// 윈도우를 닫으면 프로그램도 종료하는 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 800);
		setLayout(null); // 하위 요소의 위치와 크기를 직접 설정
		setResizable(false); // 윈도우 크기 변경 차단
		
		JButton button1 = new JButton();
		button1.setText("GUI 버튼 이벤트 처리 연습"); // 버튼 위에 표시되는 메시지
		button1.setSize(250, 50);
		button1.setLocation(15, 15); // 좌측 상단의 위치 값
		button1.addActionListener( (e) -> {						// 버튼 클릭하면 호출될 메서드 등록 2			
		} );		
		add(button1); // 윈도우에 button1을 추가하는 명령
		
		//////////
		
		JButton button2 = new JButton();
		button2.setText("동기 방식 호출"); // 버튼 위에 표시되는 메시지
		button2.setSize(250, 50);
		button2.setLocation(15, 80); // 좌측 상단의 위치 값
		button2.addActionListener( (e) -> {						// 버튼 클릭하면 호출될 메서드 등록
			System.out.println("1. doWork 호출 전");
			TheWorker1 worker = new TheWorker1();
			worker.doWork(); // 10초간 대기
			System.out.println("4. doWork 호출 후");			
		} );		
		add(button2);
		
		//////////
		
		JButton button3 = new JButton();
		button3.setText("비동기 방식 호출 (Runnable 구현)"); // 버튼 위에 표시되는 메시지
		button3.setSize(250, 50);
		button3.setLocation(15, 145); // 좌측 상단의 위치 값
		button3.addActionListener( (e) -> {						// 버튼 클릭하면 호출될 메서드 등록
			System.out.println("1. doWork 호출 전");
			
			TheWorker2 worker2 = new TheWorker2();
			// worker2.doWork(); // 동기 방식 호출
			Thread thread = new Thread(worker2);
			thread.start(); // 비동기 방식으로 worker2.run() 호출 -> doWork() 호출
			
			System.out.println("4. doWork 호출 후");			
		} );		
		add(button3);
		
		//////////
		
		JButton button4 = new JButton();
		button4.setText("비동기 방식 호출 (Thread 상속)"); // 버튼 위에 표시되는 메시지
		button4.setSize(250, 50);
		button4.setLocation(15, 210); // 좌측 상단의 위치 값
		button4.addActionListener( (e) -> {						// 버튼 클릭하면 호출될 메서드 등록
			System.out.println("1. doWork 호출 전");
			System.out.println("Frame : " + Thread.currentThread().getId());
			
			TheWorker3 worker3 = new TheWorker3();
			// worker3.doWork(); // 동기 방식 호출
			worker3.start(); // 비동기 방식으로 worker3.run() 호출 -> doWork() 호출
			
			System.out.println("4. doWork 호출 후");			
		} );		
		add(button4);
		
		//////////
		
		JButton button5 = new JButton();
		button5.setText("공유 자원 경쟁 문제"); // 버튼 위에 표시되는 메시지
		button5.setSize(250, 50);
		button5.setLocation(15, 275); // 좌측 상단의 위치 값
		button5.addActionListener( (e) -> {						// 버튼 클릭하면 호출될 메서드 등록
//			System.out.println("1. doWork 호출 전");
//			
//			TheWorker4 worker4_1 = new TheWorker4();
//			worker4_1.start(); // 비동기 방식 worker4_1.run() 호출 -> doWork() 호출
//			
//			TheWorker4 worker4_2 = new TheWorker4();
//			worker4_2.start(); // 비동기 방식 worker4_2.run() 호출 -> doWork() 호출
//			
//			System.out.println("4. doWork 호출 후");			
		} );		
		add(button5);
		
		//////////
		
		JButton button6 = new JButton();
		button6.setText("쓰레드 동기화 (synchronized block)"); // 버튼 위에 표시되는 메시지
		button6.setSize(250, 50);
		button6.setLocation(15, 340); // 좌측 상단의 위치 값
		button6.addActionListener( (e) -> {						// 버튼 클릭하면 호출될 메서드 등록
//			System.out.println("1. doWork 호출 전");
//			
//			TheWorker5 worker5_1 = new TheWorker5();
//			worker5_1.start(); // 비동기 방식 worker5_1.run() 호출 -> doWork() 호출
//			
//			TheWorker5 worker5_2 = new TheWorker5();
//			worker5_2.start(); // 비동기 방식 worker5_2.run() 호출 -> doWork() 호출
//			
//			System.out.println("4. doWork 호출 후");			
		} );		
		add(button6);
		
		//////////
		
		JButton button7 = new JButton();
		button7.setText("쓰레드 동기화 (synchronized method)"); // 버튼 위에 표시되는 메시지
		button7.setSize(250, 50);
		button7.setLocation(15, 405); // 좌측 상단의 위치 값
		button7.addActionListener( (e) -> {						// 버튼 클릭하면 호출될 메서드 등록
//			System.out.println("1. doWork 호출 전");
//			
//			TheWorker6 worker6_1 = new TheWorker6();
//			worker6_1.start(); // 비동기 방식 worker6_1.run() 호출 -> doWork() 호출
//			
//			TheWorker6 worker6_2 = new TheWorker6();
//			worker6_2.start(); // 비동기 방식 worker6_2.run() 호출 -> doWork() 호출
//			
//			System.out.println("4. doWork 호출 후");			
		} );		
		add(button7);
	}
	
	public static void main(String[] args) {
		
		Ex18ThreadDemoFrame window = new Ex18ThreadDemoFrame();	
		
		window.setVisible(true);

	}

}
