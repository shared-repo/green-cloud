

public class Ex09ReferenceType2 {

	public static void main(String[] args) {
		
		int x = 10;
		int y = 10;
		System.out.println(x == y);
		
		String s1 = new String("Hello");
		String s2 = new String("Hello");
		// 문자열(참조형)에 대한 비교 연산자는 주소 비교
		System.out.printf("주소가 %s\n", s1 == s2 ? "같습니다" : "다릅니다");
		// 문자열에 대해 내용을 비교하려면 equals를 사용
		System.out.printf("내용이 %s\n", s1.equals(s2) ? "같습니다" : "다릅니다");
		
		System.out.println("=============================");
		
		String s3 = "Hello Java";
		String s4 = "Hello Java"; // 이 라인의 "Hello Java"는 위 라인의 "Hello Java"와 같은 문자열 인스턴스입니다.
		System.out.printf("주소가 %s\n", s3 == s4 ? "같습니다" : "다릅니다");
		
		System.out.printf("3번째 문자 : %c\n", s3.charAt(3));
		System.out.printf("문자열의 길이 (문자 갯수) : %d\n", s3.length());
		
		String s5 = "This is test string";
		String s6 = s5.replace("s", "$");
		System.out.println(s5);
		System.out.println(s6);
		
		String s7 = "Hello " + "Java " + "!!!";
		
		StringBuilder sb = new StringBuilder(100); // 기존 문자열이 저장된 공간 수정 가능 (가변 문자열)
		sb.append("Hello");
		sb.append(" ");
		sb.append("Java");
		sb.append(" ");
		sb.append("!!!");
		System.out.println(sb.toString());
		
		String s8 = "12345678901234567890";
		System.out.println( s8.substring(10) );		// 10번째부터 끝까지 반환
		System.out.println( s8.substring(10, 15) );	// 10번째부터 15번째까지 반환
		
		System.out.println( s8.indexOf("7") );		// 앞에서부터 찾기
		System.out.println( s8.lastIndexOf("7") );	// 뒤에서부터 찾기
		
		String s9 = "abc,def,ghi,jkl";
		String[] sr = s9.split(","); // -> ["abc", "def", "ghi", "jkl"]
		for (String si : sr) {
			System.out.println(si);
		}
		
		String sj = String.join("===", sr); // split의 반대기능 : 무자열 배열을 결합해서 하나의 문자열로 변환
		System.out.println(sj);
		
		String s10 = "마동석";
		System.out.println(s10.equals("마동석")); // true
		System.out.println(s10.equals("마동"));	// false
		System.out.println(s10.equals("마"));	// false
		
		System.out.println(s10.contains("마동석")); 	// true
		System.out.println(s10.contains("마동"));		// true
		System.out.println(s10.contains("마"));		// true
		
		
		Week week1 = null; // 열거형은 참조형 -> 그러므로 null 사용 가능
		week1 = Week.SATURDAY;
		System.out.println(week1);
		// week = 10; // 오류 (지정된 7개의 값만 저장 가능 )
		// week = "MONDAY"; // 오류 (지정된 7개의 값만 저장 가능 )
		
		Week week2 = Week.SATURDAY;
		System.out.println(week2 == week1);
		System.out.println(week2.equals(week1));

	}
}

// 열거 자료형 만들기 : 이 자료형은 7개의 값만 저장할 수 있습니다.
enum Week {
	MONDAY,
	TUESDAY,
	WEDNESDAY,
	THURSDAY,
	FRIDAY,
	SATURDAY,
	SUNDAY
}






















