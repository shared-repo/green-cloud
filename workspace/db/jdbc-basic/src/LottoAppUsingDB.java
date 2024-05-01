

import java.util.Scanner;

public class LottoAppUsingDB {

	private Scanner scanner = new Scanner(System.in);
		
	public LottoAppUsingDB() {
		
	}
	
	public void doStart() {
	
		program: while (true) {
		
			String selection = selectTask();

			System.out.println();
			
			switch (selection) {
			case "0": 
				System.out.println("행운을 빕니다.");
				System.out.println("프로그램을 종료합니다.");
				break program;
			case "1": // 당첨 예상 번호 뽑기				
				break;
			case "2": // 당첨 예상 번호 목록 보기				
				break;
			case "3": // 당첨 예상 번호 내보내기				
				break;				
			case "4": // 당첨 번호 데이터베이스 초기화
				// market_db 데이터베이스에 테이블 만들기 ( mysql workbench에서 한 번만 수행, 테이블은 파일의 내용을 참고해서 구현 )
				// 데이터베이스의 기존 데이터 모두 삭제
				
				// 파일에서 데이터 읽기
				// 읽은 데이터를 데이터베이스에 저장
				break;
			case "5":
				
				break;
			default:
				System.out.println("지원하지 않는 명령입니다.");
			}
			
			System.out.println();
		}

	}
	
	private String selectTask() {
		System.out.println("***********************");
		System.out.println("* 0. 종료");
		System.out.println("* 1. 당첨 예상 번호 뽑기");
		System.out.println("* 2. 당첨 예상 번호 목록 보기");
		System.out.println("* 3. 당첨 예상 번호 내보내기");
		System.out.println("* 4. 당첨 번호 데이터베이스 초기화");
		System.out.println("* 5. 당첨 번호 검색");
		System.out.println("***********************");		
		System.out.print("작업을 선택하세요 : ");
		String selection = scanner.nextLine();
		
		return selection;
	}	
	
	private boolean checkAverage(int avg) {
		boolean valid = avg >= 20 && avg <= 26;
		return valid;
	}	
	
	private int[] selectBasicNumbers() {
		int[] numbers = new int[6];
		for (int i = 0; i < numbers.length; i++) {
			
			numbers[i] = (int)(Math.random() * 45) + 1; // 1 ~ 45, random
			//중복 검사
			for (int j = 0; j < i; j++) {
				if (numbers[i] == numbers[j]) { // 중복인 경우
					//i--;	// 현재 위치에서 다시 뽑기
					i = -1;	// 처음부터 다시 뽑기
					break;
				}
			}
		}
		return numbers;
	}		
	
	public static void main(String[] args) {
		
		LottoAppUsingDB lottoApp = new LottoAppUsingDB();
		lottoApp.doStart();
		
	}

}























