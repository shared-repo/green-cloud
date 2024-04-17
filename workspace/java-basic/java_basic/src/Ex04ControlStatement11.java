

public class Ex04ControlStatement11 {

	public static void main(String[] args) {
		//1. 성적 처리기를 사용자가 원할 때까지 반복해서 실행하도록 수정 (9번 참고)
		//2. 점수 입력 유효성 검사 -> 0 ~ 100이 아니면 다시 입력하도록 수정 (8번 참고)
		
		// 성적 처리기 만들기
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		while (true) { // 성적 처리기 반복
			int score1;
			System.out.print("첫 번째 점수 : ");
			score1 = scanner.nextInt();				
			if (score1 < 0 || score1 > 100)
				continue; // 아래의 구문은 실행하지 않고 반복문의 처음으로 이동
			
			int score2;
			do {
				System.out.print("두 번째 점수 : ");
				score2 = scanner.nextInt();
			} while (score2 < 0 || score2 > 100);
			
			int score3;
			do {
				System.out.print("세 번째 점수 : ");
				score3 = scanner.nextInt();
			} while (score3 < 0 || score3 > 100);
			
			int sum = score1 + score2 + score3;
			double avg = sum / 3.;
			
			char grade;
			boolean valid = true;
			switch ((int)avg / 10) {
			case 10: 
			case 9: grade = 'A'; break;
			case 8: grade = 'B'; break;
			case 7: grade = 'C'; break;
			case 6: grade = 'D'; break;
			case 5: 
			case 4: 
			case 3: 
			case 2: 
			case 1: 
			case 0: grade = 'F'; break;
			default: valid = false; grade = 'X';
			} // end of switch
			
			// 4. 결과 출력
			if (valid) {
				System.out.printf("[SCORE1: %d][SCORE2: %d][SCORE3: %d][SUM: %d][AVG: %f][GRADE: %s]\n", 
								  score1, score2, score3, sum, avg, grade);
			} else {
				System.out.println("입력 오류");
			}
			
			System.out.print("계속할까요?(y or n) : ");
			String again = scanner.next();
			if (!again.equalsIgnoreCase("y")) { // !again.equalsIgnoreCase("y") == again.equalsIgnoreCase("y") == false
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		
		} // end of while
		

	}

}

















