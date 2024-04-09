package java_basic;

public class Ex04ControlStatement04 {

	public static void main(String[] args) {
		
		// 성적 처리기 만들기
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		// int score1, score2, score3;
		
		// 1-1. 첫 번째 점수 입력 -> 변수에 저장
		System.out.print("첫 번째 점수 : ");
		int score1 = scanner.nextInt();
		// 1-2. 두 번째 점수 입력 -> 변수에 저장
		System.out.print("두 번째 점수 : ");
		int score2 = scanner.nextInt();
		// 1-3. 세 번째 점수 입력 -> 변수에 저장
		System.out.print("세 번째 점수 : ");
		int score3 = scanner.nextInt();
		
		// 2. 합계, 평균 계산 -> 각각 변수에 저장
		int sum = score1 + score2 + score3;
		// int avg = sum / 3;
		double avg = sum / 3.;
		
		// 3. 등급 계산 -> 변수에 저장
		char grade;
		boolean valid = true;
		switch ((int)avg / 10) {
		case 10: grade = 'A'; break;
		case 9: grade = 'A'; break;
		case 8: grade = 'B'; break;
		case 7: grade = 'C'; break;
		case 6: grade = 'D'; break;
		case 5: grade = 'F'; break;
		case 4: grade = 'F'; break;
		case 3: grade = 'F'; break;
		case 2: grade = 'F'; break;
		case 1: grade = 'F'; break;
		case 0: grade = 'F'; break;
			default: valid = false;
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

















