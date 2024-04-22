import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Ex14ExceptionHandling {

	public static void main(String[] args) {
		
		
		// 예외 처리를 하지 않으면 오류 메시지와 함께 프로그램이 강제 종료됩니다.
		// int a = 10 / 0;		
		
		System.out.println("1");
		
		method();
				
		System.out.println("6");
	}
	
	public static void method()  {
		
		System.out.println("2");
		
		try { // 예외 발생이 의심되는 영역 설정 (이 영역에서 발생한 예외만 처리 가능)
			method2();
			// method2() 에서 예외가 발생하면 즉시 catch 구문으로 이동하기 때문에 try 블럭 내부의 아래 코드는 실행되지 않습니다.
			System.out.println("after calling method2");
		} catch (ArithmeticException ex) { //extends RuntimeException
			System.out.println("산술 오류 정상 처리");
			// throw ex; // 강제로 예외 발생 --> 다시 예외 흐름으로 전환
		} catch (ClassCastException ex) {
			System.out.println("형변환 오류 정상 처리");
		} catch (RuntimeException ex) {
			System.out.println("사용자 정의 오류 정상 처리");
		} catch (FileNotFoundException e) {
			System.out.println("파일 없음 오류 정상 처리");
		} catch (Exception ex) { // 나머지 모든 예외 상황 -> 상속 관계에서 부모 클래스이므로 반드시 마지막에 작성해야 합니다.
			System.out.println("알 수 없는 오류 정상 처리");
		} finally { // 예외 여부와 관계 없이 실행이 보장되는 영역
			System.out.println("예외 여부와 관계 없이 실행");			
		}
		
		System.out.println("5");
	}	
	
	// 여러 종류의 예외(Exception)을 발생시키는 테스트 메서드
	public static void method2() throws FileNotFoundException {
		
		System.out.println("3");
		
		// switch ((int)(Math.random() * 8)) { // 0 ~ 7 : 50%는 예외 발생, 50%는 정상 실행
		switch (0) {
		case 0 :
			int x = 10 / 0;// ArimethicException 예외가 발생하면 호출한 곳으로 보고
			break;
		case 1 :
			Object o = 10; //Integer
			String s = (String)o; //ClassCastException
			break;
		case 2 :
			RuntimeException ex = new RuntimeException("사용자 정의 예외");
			throw ex; //강제로 예외 발생 ( 여기서는 선택적 구문 : new RuntimeException()으로 예외가 이미 발생 )
		case 3 :
			FileInputStream fis = new FileInputStream("x.exe"); // FileNotFoundException
			break;
		}
		
		// 위에서 예외가 발생하면 즉시 호출한 곳으로 돌아가기 때문에 아래 코드는 실행되지 않습니다.
		System.out.println("4");		
		
	}

}













