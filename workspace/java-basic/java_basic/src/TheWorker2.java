
// Thread 구현 방법 1. Runnable 인터페이스 구현
public class TheWorker2 implements Runnable {
	
	public void doWork() {
		
		System.out.println("2. doWork의 처음");
		
		try {
			Thread.sleep(1000 * 10);// 10초간 실행 중지 -> 깨어날 때 예외 발생
		} catch (InterruptedException e) {} 
		
		System.out.println("3. doWork의 끝");
		
	}

	// Thread가 시작되면 (비동기 방식으로 실행되면) 자동으로 호출되는 메서드
	@Override
	public void run() {
		doWork();		
	}

}
