import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

public class Ex12Collections {

	public static void main(String[] args) throws Exception {


		//1. ArrayList : List 인터페이스 구현, 가변 배열 (크기가 자동으로 변경되는 배열)
//		// ArrayList al = new ArrayList();//Non Generic 방식 (Object를 사용해서 모든 자료형의 데이터 저장)
//		ArrayList<String> al = new ArrayList<>();//Generic 방식 (지정된 자료형 데이터 저장)
//		//al.add(100); //Generic 컬렉션인 경우 오류
//		al.add("첫 번째 데이터.");//목록의 마지막에 추가
//		al.add("두 번째 데이터.");
//		al.add("세 번째 데이터.");
//		al.add("네 번째 데이터.");
//		al.add("네 번째 데이터.");// 중복 허용
//		al.add(1, "삽입된 데이터.");//1번째 위치에 삽입		
//		for (int i = 0; i < al.size(); i++) {//컬렉션.size() : 요소의 갯수 ( 배열에서는 length )
//			System.out.println(al.get(i));//List컬렉션.get(위치) : 지정된 위치의 데이터 반환 ( 배열은 [위치] )
//		}
//		al.remove(2);//위치가 2번째 데이터 삭제
//		String strToRemove = (String)al.get(0); // Generic인 경우 형변환 필요 없음
//		al.remove(strToRemove);//strToRemove와 같은 객체를 제거 (여기서는 0번째 데이터)		
//		System.out.println();
//		for (int i = 0; i < al.size(); i++) {//컬렉션.size() : 요소의 갯수
//			System.out.println(al.get(i));//List컬렉션.get(위치) : 지정된 위치의 데이터 반환
//		}
		
		/////////////////////////////////////////////////////////////////////////////
		
		//2. Vector : ArrayList와 동일한데 ThreadSafe한 특성을 갖습니다.
//		Vector<String> al = new Vector<>();//
//		al.add("첫 번째 데이터.");//목록의 마지막에 추가
//		al.add("두 번째 데이터.");
//		al.add("세 번째 데이터.");
//		al.add("네 번째 데이터.");
//		al.add(1, "삽입된 데이터.");//1번째 위치에 삽입
//		
//		for (int i = 0; i < al.size(); i++) {//컬렉션.size() : 요소의 갯수
//			System.out.println(al.get(i));//List컬렉션.get(위치) : 지정된 위치의 데이터 반환
//		}
//		
//		al.remove(2);//위치가 2번째 데이터 삭제
//		String strToRemove = al.get(0);
//		al.remove(strToRemove);//strToRemove와 같은 객체를 제거 (여기서는 0번째 데이터)
//		
//		System.out.println();
//		for (int i = 0; i < al.size(); i++) {//컬렉션.size() : 요소의 갯수
//			System.out.println(al.get(i));//List컬렉션.get(위치) : 지정된 위치의 데이터 반환
//		}
		
		/////////////////////////////////////////////////////////
		
		//3. LinkedList
//		LinkedList<String> al = new LinkedList<>();//
//		al.add("첫 번째 데이터.");//목록의 마지막에 추가
//		al.add("두 번째 데이터.");
//		al.add("세 번째 데이터.");
//		al.add("네 번째 데이터.");
//		al.add(1, "삽입된 데이터.");//1번째 위치에 삽입
//		
//		for (int i = 0; i < al.size(); i++) {//컬렉션.size() : 요소의 갯수
//			System.out.println(al.get(i));//List컬렉션.get(위치) : 지정된 위치의 데이터 반환
//		}
//		
//		al.remove(2);//위치가 2번째 데이터 삭제
//		String strToRemove = al.get(0);
//		al.remove(strToRemove);//strToRemove와 같은 객체를 제거 (여기서는 0번째 데이터)
//		
//		System.out.println();
//		for (int i = 0; i < al.size(); i++) {//컬렉션.size() : 요소의 갯수
//			System.out.println(al.get(i));//List컬렉션.get(위치) : 지정된 위치의 데이터 반환
//		}
		
		/////////////////////////////////////////////////////////////////
		
		//4. HashSet
//		HashSet<String> al = new HashSet<>();//
//		al.add("첫 번째 데이터.");
//		al.add("두 번째 데이터.");
//		al.add("세 번째 데이터.");
//		al.add("네 번째 데이터.");
//		al.add("네 번째 데이터.");//기존 데이터 덮어쓰기 (중복데이터)
//
////		for (int i = 0; i < al.size(); i++) {
////			al.get(i);//Set 계열은 순서에 기반한 위치 정보 사용 X
////		}
//		for (String str : al) { //목록에서 다음 데이터가 있으면 데이터를 읽어서 str 저장
//			System.out.println(str);
//		}
//		
//		System.out.println();
//		
//		// al.remove(2);//오류 : 위치 번호 사용 불가능
//		String strToRemove = "세 번째 데이터.";
//		al.remove(strToRemove);//strToRemove와 같은 객체를 제거
//		
//		for (String str : al) {//목록에서 다음 데이터가 있으면 데이터를 읽어서 str 저장
//			System.out.println(str);
//		}
//		
//		System.out.println();
		
		///////////////////////////////////////////////////////////////
		
		//4-1. HashSet2
//		HashSet<String> al = new HashSet<>();//
//		al.add("첫 번째 데이터.");
//		al.add("두 번째 데이터.");
//		al.add("세 번째 데이터.");
//		al.add("네 번째 데이터.");
//		al.add("네 번째 데이터.");//기존 데이터 덮어쓰기 (중복데이터)
//
////		for (String str : al) { //목록에서 다음 데이터가 있으면 데이터를 읽어서 str 저장
////			System.out.println(str);
////		}
//		
//		//enhanced for의 내부 동작
//		Iterator<String> iter = al.iterator(); //Iterator : java의 순회에 대한 표준
//		while (iter.hasNext()) { // hasNext : 다음 항목이 있으면 true 없으면 false
//			String data = iter.next(); //next : 다음 항목 가져오기
//			System.out.println(data);
//		}
//		
//		System.out.println();
//		
//		//al.remove(2);//오류 : 위치 번호 사용 불가능
//		String strToRemove = "세 번째 데이터.";
//		al.remove(strToRemove);//strToRemove와 같은 객체를 제거
//		
//		for (String str : al) {//목록에서 다음 데이터가 있으면 데이터를 읽어서 str 저장
//			System.out.println(str);
//		}
//		
//		System.out.println();	
		
		////////////////////////////////////////////////////////
		
		//5. HashMap (Hashtable)
//		//HashMap<String, String> al = new HashMap<>();		// Thread-unsafe
//		Hashtable<String, String> al = new Hashtable<>();	// Thread-safe
//		al.put("하나", "첫 번째 데이터."); // put : 데이터 추가
//		al.put("둘", "두 번째 데이터.");
//		al.put("셋", "세 번째 데이터.");
//		al.put("넷", "네 번째 데이터.");
//		al.put("다섯", "네 번째 데이터.");//삽입 성공
//		al.put("다섯", "다섯 번째 데이터.");//삽입 실패 (overwrite)
//		
//		System.out.println("TEST : " + al.get("넷"));
//		
//		Set<String> keys = al.keySet();//keySet : 키 목록 반환 (Set 형식으로)
//		for (String key : keys) { //목록에서 다음 데이터가 있으면 데이터를 읽어서 key 저장
//			String value = al.get(key); //get: 목록에서 key로 검색된 데이터 반환
//			System.out.println( value );
//		}
//		
//		String keyToRemove = "3";
//		al.remove(keyToRemove);		
//		System.out.println();		
//		for (String key : al.keySet()) {
//			System.out.println(al.get(key));
//		}
		
		//////////////////////////////////////////////
		
		 //6. Properties 
		 //   (키 : 문자열, 값 : 문자열인 Map / 파일에 저장하고 읽는 기능 제공 / 주로 설정정보 관리용으로 사용)
//		 Properties al = new Properties();// Generic 형식이 아님
//		 al.put("1", "First Data.");
//		 al.put("3", "Second Data.");
//		 al.put("4", "Third Data.");
//		 al.put("2", "Fourth Data.");
//		 al.put("5", "Fourth Data.");//삽입 성공
//		 al.put("5", "Fifth Data.");//삽입 실패
//		
//		 //store: 파일에 데이터를 저장하는 명령
//		 //FileOutputStream : 파일에 쓰기 목적으로 연결하는 클래스
//		 al.store(new FileOutputStream("test.properties"), "test properties file");
//		
//		 Properties props = new Properties();
//		 //load : 파일에서 데이터를 읽고 Properties로 가져오는 메서드
//		 //FileInputStream : 파일에 읽기 목적으로 연결하는 클래스
//		 props.load(new FileInputStream("test.properties"));//파일에서 데이터 읽는 명령
//		
//		 Set keys = props.keySet();//키 목록 반환
//		 for (Object key : keys) {//목록에서 다음 데이터가 있으면 데이터를 읽어서 str 저장
//		 	String value = (String)props.get(key);//목록에서 key로 검색된 데이터 반환
//		 	System.out.println( value );
//		}
		
		//7. Stack (후입선출 - Last In First Out)
//		Stack<String> stack = new Stack<>();
//		stack.push("a;dklja;sldkjfa;sdfjl"); // push : 데이터 추가 (삽입)
//		stack.push("13243654321321");
//		stack.push("ㅁ;ㄴ아ㅓㄻ;니ㅏ얼");
//		
//		while (stack.size() > 0) {
//			System.out.println(stack.pop());	//pop : 마지막 데이터를 읽고 목록에서 제거
//			//System.out.println(stack.peek());	//읽고 목록에 유지
//		}	
		
		//8. Queue (선입선출 - First In First Out)
//		Queue<String> queue = new LinkedList<>();
//		queue.offer("a;dklja;sldkjfa;sdfjl");	//offer : 데이터 추가 (삽입)
//		queue.offer("13243654321321");
//		queue.offer("ㅁ;ㄴ아ㅓㄻ;니ㅏ얼");
//		
//		while (queue.size() > 0) {
//			System.out.println(queue.poll());	//poll : 첫번째 데이터를 읽고 목록에서 제거
//			//System.out.println(queue.peek());	//읽고 목록에 유지
//		}

	}

}
