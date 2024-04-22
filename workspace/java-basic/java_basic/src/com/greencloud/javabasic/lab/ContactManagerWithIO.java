// 연락처 관리 프로그램 만들기
// 연락처 등록, 전체목록, 검색, 삭제, 수정 기능
package com.greencloud.javabasic.lab;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ContactManagerWithIO {

	private java.util.Scanner scanner = new java.util.Scanner(System.in);

	// 전체 연락처 정보를 저장하는 배열 만들기
	private ArrayList<Contact> contactList = new ArrayList<>();
	
	public void manage() {
		
		program : while (true) {
			String task = selectTask();
			
			switch (task) {
			case "1": // 연락처 등록 
				// 1. 연락처 입력 + 인스턴스 생성
				Contact contact = inputNewContact();
				// 3. 인스턴스를 연락처 목록에 저장
				contactList.add(contact);
				break;
			case "2": // 수정 
				// 1. 수정할 연락처 검색
				// 1-1. 수정할 이름 입력
				System.out.print("수정할 이름 : ");
				String nameToEdit = scanner.nextLine();
				// 1-2. 검색 수행
				ArrayList<Contact> editList = searchContact(nameToEdit);
				// 1-3. 검색결과 출력
				if (editList.size() == 0) {
					System.out.println("해당하는 연락처가 없습니다.");
					break;
				}
				// 검색결과가 있는 경우 아래 코드 실행
				System.out.println("[수정할 연락처 목록]");
				for (Contact c : editList) {
					System.out.println(c); // c.toString()
				}
				// 2. 수정할 연락처 고유번호(no) 입력
				System.out.print("수정할 연락처의 고유번호 : ");
				int noToEdit = scanner.nextInt(); // 입력을 처리한 후 버퍼에 엔터문자가 남아 있습니다.
				scanner.nextLine(); // nextInt() 호출 후 버퍼에 남아있는 입력 데이터 제거
				// 3. 입력된 번호에 해당하는 연락처 목록에서 삭제	
				for (Contact c : editList) {
					if (c.getNo() == noToEdit) {
						System.out.print("[수정내용] 이름 (수정하지 않으려면 엔터) : ");
						String name = scanner.nextLine();
						if (name.length() > 0) {
							c.setName(name);
						}
						System.out.print("[수정내용] 전화번호  (수정하지 않으려면 엔터) : ");
						String phone = scanner.nextLine();
						if (phone.length() > 0) {
							c.setPhone(phone);
						}
						System.out.print("[수정내용] 이메일  (수정하지 않으려면 엔터) : ");
						String email = scanner.nextLine();
						if (email.length() > 0) {
							c.setEmail(email);
						}
						break;
					}
				}
				break;
			case "3": // 삭제				
				// 1. 삭제할 연락처 검색
				// 1-1. 검색할 이름 입력
				System.out.print("삭제할 이름 : ");
				String nameToDelete = scanner.nextLine();
				// 1-2. 검색 수행
				ArrayList<Contact> deleteList = searchContact(nameToDelete);
				// 1-3. 검색결과 출력
				if (deleteList.size() == 0) {
					System.out.println("해당하는 연락처가 없습니다.");
					break;
				} 				
				System.out.println("[삭제할 연락처 목록]");
				for (Contact c : deleteList) {
					System.out.println(c); // c.toString()
				}
				
				// 검색결과가 있는 경우 아래 코드 실행
				// 2. 삭제할 연락처 고유번호(no) 입력
				System.out.print("삭제할 연락처의 고유번호 : ");
				int noToDelete = scanner.nextInt(); // 입력을 처리한 후 버퍼에 엔터문자가 남아 있습니다.
				scanner.nextLine(); // nextInt() 호출 후 버퍼에 남아있는 입력 데이터 제거
				// 3. 입력된 번호에 해당하는 연락처 목록에서 삭제	
				for (Contact c : deleteList) {
					if (c.getNo() == noToDelete) {
						contactList.remove(c); // remove(숫자) : 해당 위치의 항목 제거, remove(객체) : 주어진 객체와 같은 객체 제거
						break;
					}
				}
				
				break;
			case "4": // 검색 (이름기준) 
				// 1. 검색할 이름 입력
				System.out.print("검색할 이름 : ");
				String nameToSearch = scanner.nextLine();
				
				// 2. 입력된 이름과 목록의 연락처의 이름을 비교해서 같은 이름의 연락처 찾기 (완전일치비교X, 부분일치비교O)
				ArrayList<Contact> searchedList = searchContact(nameToSearch);				
				
				// 3. 검색된 연락처 출력
				if (searchedList.size() == 0) {
					System.out.println("해당하는 연락처가 없습니다.");
				} else {
					System.out.println("[검색된 연락처 목록]");
					for (Contact c : searchedList) {
						System.out.println(c); // c.toString()
					}
				}
				break;
			case "5": // 연락처 목록 
				showContactList();
				break;
				
			case "6": // 연락처 목록을 파일에 저장
				FileOutputStream fos = null;
				ObjectOutputStream oos = null;
				try {
					fos = new FileOutputStream("contacts.dat");
					oos = new ObjectOutputStream(fos);
					oos.writeObject(contactList);
				} catch (IOException e) { // FileNotFoundException의 부모 클래스 : 이 예외처리 영역에서 FileNotFoundException 처리 가능
					System.out.println("데이터를 파일에 저장하지 못했습니다.");
					e.printStackTrace(); // 오류에 대한 정보를 화면에 출력하는 메서드 호출 ( 개발하는 과정에만 사용 )
				} finally {
					try { oos.close(); } catch (Exception ex) {}
					try { fos.close(); } catch (Exception ex) {}
				}
				break;				
			case "7": // 연락처 목록을 파일에서 읽기 -> 연락처 목록 변수에 저장
				FileInputStream fis = null;
				ObjectInputStream ois = null;
				try {
					fis = new FileInputStream("contacts.dat");
					ois = new ObjectInputStream(fis);
					contactList = (ArrayList<Contact>)ois.readObject();
					
					Contact lastContact = contactList.get(contactList.size() - 1); // 마지막 Contact 객체 가져오기
					Contact.setNextNo(lastContact.getNo() + 1); // 마지막 연락처의 고유 번호 다음 번호를 nextNo에 저장
				} catch (IOException ex) {
					System.out.println("연락처 정보 복원 실패 : 파일 처리 오류");
				} catch (ClassNotFoundException ex) {
					System.out.println("연락처 정보 복원 실패 : 유효하지 않은 클래스 데이터");
				} finally {
					try { ois.close(); } catch (Exception ex) {}
					try { fis.close(); } catch (Exception ex) {}
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
		System.out.println("* 6. 연락처 저장");
		System.out.println("* 7. 연락처 복원");
		System.out.println("* 0. 종료");
		System.out.println("********************");
		System.out.print("작업을 선택하세요 : ");
		String task = scanner.nextLine();
		return task;
	}
	
	public Contact inputNewContact() {
		// 1. 연락처 인스턴스 만들기 ( 1개의 연락처 정보 )
		Contact contact = new Contact();
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
	
	public void showContactList() {
		System.out.println("***** 연락처 정보 *****");
		for (Contact c : contactList) {
			System.out.println(c); // c.toString()
		}
	}
	
	public ArrayList<Contact> searchContact(String nameToSearch) {
		// 1. 검색 결과를 저장할 컬렉션 객체 만들기
		ArrayList<Contact> searchedList = new ArrayList<>();
		
		// 2. 전체 목록을 반복하면서 사용자가 입력한 이름과 부분일치하는 연락처를 찾아서 목록에 저장
		for (Contact c : contactList) {
			if (c.getName().contains(nameToSearch)) {
				searchedList.add(c);
			}
		}
		
		// 3. 목록을 반환
		return searchedList;
	}
	
	/////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		
		ContactManagerWithIO contactManager = new ContactManagerWithIO();
		contactManager.manage();
		
	}

}

























