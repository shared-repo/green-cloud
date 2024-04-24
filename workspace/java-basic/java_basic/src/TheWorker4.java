
public class TheWorker4 extends Thread {
	
	@Override
	public void run() { //Thread가 시작되면 비동기 방식으로 호출되는 메서드
		doWork();
	}

	private static int result; // static은 모든 인스턴스가 공유하는 멤버
	
	public void doWork() {
		System.out.println("2. Beginning of doWork");
		try {
			// 1에서 100까지의 합을 계산하는 코드
			result = 0;
			for (int i = 1; i <= 100; i++) {
				if (i % 10 == 0) { //10의 배수인 경우
					try { Thread.sleep(1000); } catch (Exception ex) {}
				}
				result = result + i;
			}			
			System.out.println("RESULT : " + result);
			
		} catch (Exception ex) {
			//do nothing
		}
		System.out.println("3. End of doWork");		
	}

}
