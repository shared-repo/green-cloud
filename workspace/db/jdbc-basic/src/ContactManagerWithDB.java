import java.util.ArrayList;

public class ContactManagerWithDB {

	private java.util.Scanner scanner = new java.util.Scanner(System.in);

	private ContactDao dao = new ContactDao();
	
	public ContactManagerWithDB() {}
	
	public void manage() {
		
		program : while (true) {
			String task = selectTask();
			
			switch (task) {
			case "1": // 연락처 등록 
				// 1. 연락처 입력 + 인스턴스 생성
				ContactDto contact = inputNewContact();
				// 2. 인스턴스를 연락처 테이블에 저장
				// ContactDao dao = new ContactDao();
				dao.insertContact(contact);
				
				System.out.println("연락처를 등록했습니다.");
				
				break;
			case "2": // 수정 
				// 1. 수정할 연락처 검색
				// 1-1. 수정할 이름 입력
				System.out.print("수정할 이름 : ");
				String nameToEdit = scanner.nextLine();
				// 1-2. 검색 수행
				
				// 1-3. 검색결과 출력				
				
				// 2. 수정할 연락처 고유번호(no) 입력
				System.out.print("수정할 연락처의 고유번호 : ");
				int noToEdit = scanner.nextInt(); // 입력을 처리한 후 버퍼에 엔터문자가 남아 있습니다.
				scanner.nextLine(); // nextInt() 호출 후 버퍼에 남아있는 입력 데이터 제거
				// 3. 입력된 번호에 해당하는 연락처 목록에서 삭제	

				break;
			case "3": // 삭제				
				// 1. 삭제할 연락처 검색
				// 1-1. 검색할 이름 입력
				System.out.print("삭제할 이름 : ");
				String nameToDelete = scanner.nextLine();
				// 1-2. 검색 수행
				ArrayList<ContactDto> contactsToDelete = dao.selectContactsByName(nameToDelete);
				// 1-3. 검색결과 출력
				if (contactsToDelete.size() == 0) {
					System.out.println("삭제 대상 연락처가 없습니다.");
				} else {
					System.out.println("[ 검색된 연락처 목록 ]");
					for (ContactDto c: contactsToDelete) {
						System.out.println(c);
					}
					
					// 2. 삭제할 연락처 고유번호(no) 입력
					System.out.print("삭제할 연락처의 고유번호 : ");
					int noToDelete = scanner.nextInt(); // 입력을 처리한 후 버퍼에 엔터문자가 남아 있습니다.
					scanner.nextLine(); // nextInt() 호출 후 버퍼에 남아있는 입력 데이터 제거
					// 3. 입력된 번호에 해당하는 연락처 목록에서 삭제	
					dao.deleteContactByNo(noToDelete);
					
					System.out.println("연락처를 삭제했습니다.");
				}
				
				break;
			case "4": // 검색 (이름기준) 
				// 1. 검색할 이름 입력
				System.out.print("검색할 이름 : ");
				String nameToSearch = scanner.nextLine();
				
				// 2. 입력된 이름과 목록의 연락처의 이름을 비교해서 같은 이름의 연락처 찾기 (완전일치비교X, 부분일치비교O)
				ArrayList<ContactDto> searchResult = dao.selectContactsByName(nameToSearch);
				
				// 3. 검색된 연락처 출력
				if (searchResult.size() == 0) {
					System.out.println("검색된 연락처가 없습니다.");
				} else {
					System.out.println("[ 검색된 연락처 목록 ]");
					for (ContactDto c: searchResult) {
						System.out.println(c);
					}
				}
				
				break;
			case "5": // 연락처 목록
				// 1. 전체 연락처 목록 조회
				// ContactDao dao2 = new ContactDao();
				ArrayList<ContactDto> contacts = dao.selectAllContacts();
				// 2. 조회 결과 출력
				if (contacts.size() == 0) {
					System.out.println("등록된 연락처가 없습니다.");
				} else {
					System.out.println("[ 전체 연락처 목록 ]");
					for (ContactDto c: contacts) {
						System.out.println(c);
					}
				}

				break;				
			
			case "0": 
				System.out.println("프로그램을 종료합니다.");
				break program;
			default: 
				System.out.println("지원하지 않는 작업입니다.");;
			}
		}
	}
	
	public String selectTask() {
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
	
	public ContactDto inputNewContact() {
		// 1. 연락처 인스턴스 만들기 ( 1개의 연락처 정보 )
		ContactDto contact = new ContactDto();
		// 2. 이름, 이메일, 전화번호 사용자입력 -> 입력된 내용을 인스턴스에 저장
		System.out.print("이름 : ");
		String name = scanner.nextLine();
		contact.setName(name);
		System.out.print("전화번호 : ");
		String phone = scanner.nextLine();
		contact.setPhone(phone);
		System.out.print("이메일 : ");
		String email = scanner.nextLine();
		contact.setEmail(email);
		
		return contact;
	}
	
	/////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		
		ContactManagerWithDB contactManager = new ContactManagerWithDB();
		contactManager.manage();
		
	}

}


// 테이블 생성
// USE market_db;
//
// CREATE TABLE contact
// (
//		no int not null primary key auto_increment,
//    	name varchar(50) not null,
//    	phone varchar(20) not null,
//    	email varchar(50) null,
//    	regidate DateTime default (now())
// );























