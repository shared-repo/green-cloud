

public class Ex04ControlStatement03 {

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
		//    등급 기준 -> 90 이상 100 이하 : A, 80 이상 90 미만 : B, 70 이상 80 미만 : C, 60 이상 70 미만 : D, 0 이상 60 미만 : F
		String grade = "";
//		if (avg >= 90) {
//			grade = "A";
//		} else if (avg >= 80) {
//			grade = "B";
//		} else if (avg >= 70) {
//			grade = "C";
//		} else if (avg >= 60) {
//			grade = "D";
//		} else {
//			grade = "F";
//		}
				
		boolean valid = true;
		if (avg >= 90 && 100 >= avg) {
			grade = "A";
		} else if (avg >= 80 && avg < 90) {
			grade = "B";
		} else if (avg >= 70 && avg < 80) {
			grade = "C";
		} else if (avg >= 60 && avg < 70) {
			grade = "D";
		} else if (avg >= 0 && avg < 60) {
			grade = "F";
		} else {
			valid = false;
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

















