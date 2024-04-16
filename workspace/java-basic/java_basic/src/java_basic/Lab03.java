package java_basic;

// 연락처 관리 프로그램 만들기
// 연락처 등록, 전체목록, 검색, 삭제, 수정 기능

public class Lab03 {

	private static java.util.Scanner scanner = new java.util.Scanner(System.in);
	
	public static void main(String[] args) {
		
		// 2. 연락처 인스턴스 만들기 및 사용 실습 코드
//		Contact contact;
//		contact = new Contact(1, "장동건", "010-6542-8852", "jdk@example.com");
//		System.out.println(contact.info());
//		
//		Contact contact2 = new Contact();
//		contact2.setNo(2);
//		contact2.setName("김윤석");
//		contact2.setPhone("010-7412-9586");
//		contact2.setEmail("kys@example.com");		
//		System.out.println(contact2.info());
		
		
		// 전체 연락처 정보를 저장하는 배열 만들기
		Contact[] contactList = new Contact[1000];
		int nextPosition = 0; // 새 연락처를 등록할 배열의 위치 번호
		
		program : while (true) {
			String task = selectTask();
			
			switch (task) {
			case "1": // 연락처 등록 
				// 1. 연락처 인스턴스 만들기 ( 1개의 연락처 정보 )
				// 2. 이름, 이메일, 전화번호 사용자입력 -> 입력된 내용을 인스턴스에 저장
				// 3. 인스턴스를 연락처 목록에 저장
				// 4. 3번까지 구현한 후 구현 내용을 메서드로 전환
				break;
			case "2": break;
			case "3": break;
			case "4": break;
			case "5": break;
			case "0": 
				System.out.println("프로그램을 종료합니다.");
				break program;
			default: 
				System.out.println("지원하지 않는 작업입니다.");;
			}
		}
	}
	
	static String selectTask() {
		System.out.println("********************");
		System.out.println("* 1. 연락처 등록");
		System.out.println("* 2. 연락처 수정");
		System.out.println("* 3. 연락처 삭제");
		System.out.println("* 4. 연락처 검색");
		System.out.println("* 5. 연락처 목록");
		System.out.println("* 0. 종료");
		System.out.println("********************");
		System.out.print("작업을 선택하세요 : ");
		String task = scanner.nextLine();
		return task;
	}

}

// 1. 클래스 만들기
//    필드 : 순서번호, 이름, 전화번호, 이메일
//    메서드 : 연락처 정보 반환
//    생성자 : 전달인자없는 생성자, 모든 필드를 전달인자로 하는 생성자
//    필드 은닉화 처리 : private으로 만들고 getter, setter 만들기
class Contact {
	private int no;
	private String name;
	private String phone;
	private String email;

	public Contact() {}
	public Contact(int no, String name, String phone, String email) {
		this.no = no;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String info() {
		return String.format("[%03d][%10s][%13s][%s]", no, name, phone, email);
	}
	  
}























