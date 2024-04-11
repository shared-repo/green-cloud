package java_basic;

public class Ex04ControlStatement11 {

	public static void main(String[] args) {
		//1. 성적 처리기를 사용자가 원할 때까지 반복해서 실행하도록 수정 (9번 참고)
		//2. 점수 입력 유효성 검사 -> 0 ~ 100이 아니면 다시 입력하도록 수정 (10번 참고)
		
		// 성적 처리기 만들기
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		System.out.print("첫 번째 점수 : ");
		int score1 = scanner.nextInt();
		System.out.print("두 번째 점수 : ");
		int score2 = scanner.nextInt();
		System.out.print("세 번째 점수 : ");
		int score3 = scanner.nextInt();
		
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
		}
		
		// 4. 결과 출력
		if (valid) {
			System.out.printf("[SCORE1: %d][SCORE2: %d][SCORE3: %d][SUM: %d][AVG: %f][GRADE: %s]", 
							  score1, score2, score3, sum, avg, grade);
		} else {
			System.out.println("입력 오류");
		}
		

	}

}

















