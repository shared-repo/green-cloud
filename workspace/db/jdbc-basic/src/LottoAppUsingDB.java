

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.greencloud.javabasic.lab.NumberSet;

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
				// market_db 데이터베이스에 테이블 만들기 ( mysql workbench에서 한 번만 수행, 테이블은 파일의 내용을 참고해서 구현 -- 아래 주석 참고 )
				// 데이터베이스의 기존 데이터 모두 삭제 ( delete 구문 )
				// 파일에서 데이터 읽기 ( 1 ~ 1116회차 데이터 )
				// 읽은 데이터를 데이터베이스에 저장
				Connection conn = null;
				PreparedStatement pstmt = null;				
				try {
					// 1. 드라이버 준비
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					// 2. 연결 객체 만들기
					conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/market_db", "green_cloud", "mysql");
					
					// 3-1. 명령 객체 만들기 (삭제 관련)
					String sql = "DELETE FROM lotto_winning_number";
					// String sql = "TRUNCATE TABLE lotto_winning_numbers;";
					pstmt = conn.prepareStatement(sql);
					
					// 4-1. 명령 실행 ( 삭제 관련 )
					pstmt.executeUpdate(); 
					
					
					sql = "INSERT INTO lotto_winning_numbers VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
					pstmt = conn.prepareStatement(sql);
					
					FileInputStream fis = null;		// 파일에 대한 byte[] 입출력
					InputStreamReader isr = null;	// String <-> byte[] 변환
					BufferedReader br = null;		// 한 줄씩 읽는 기능 제공
					try {
						fis = new FileInputStream("lotto-winning-numbers.txt");
						isr = new InputStreamReader(fis);
						br = new BufferedReader(isr);
						
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");	// 특정 형식의 문자열 -> 날짜 or 날짜 -> 특정 형식의 문자열
						
						while (true) {
							String line = br.readLine();	// 텍스트 파일에서 한 줄 읽기
							if (line == null) {				// EOF
								break;
							}
							
							String[] data = line.split(","); // split : "abc-efg-xy-tac".split("-") --> ["abc", "efg", "xy", "tac"]
							pstmt.setInt(1, Integer.parseInt(data[0]));
							java.util.Date d = sdf.parse(data[1]);
							long tick = d.getTime(); // 1970.1.1 00:00:00 초 이후에 경과된 시간을 1/1000초 단위로 계산한 값
							pstmt.setDate(2, new java.sql.Date(tick));
							pstmt.setInt(3, Integer.parseInt(data[2]));
							pstmt.setInt(4, Integer.parseInt(data[3]));
							pstmt.setInt(5, Integer.parseInt(data[4]));
							pstmt.setInt(6, Integer.parseInt(data[5]));
							pstmt.setInt(7, Integer.parseInt(data[6]));
							pstmt.setInt(8, Integer.parseInt(data[7]));
							pstmt.setInt(9, Integer.parseInt(data[8]));
							
							pstmt.executeUpdate();
						}			
					} catch (IOException ex) {			
					} finally {
						try { br.close(); } catch (Exception ex) {}
						try { isr.close(); } catch (Exception ex) {}
						try { fis.close(); } catch (Exception ex) {}
					}					
					
					// 5. 결과가 있으면 결과 처리
					
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					// 6. 연결 종료					
					try { pstmt.close(); } catch (Exception ex) {}
					try { conn.close(); } catch (Exception ex) {}
				}
				
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


// 테이블 생성 구문
// USE market_db;
//
// CREATE TABLE lotto_winning_number
// (
//	  rnd int not null primary key,
//    game_date date not null,
//    number1 int not null,
//    number2 int not null,
//    number3 int not null,
//    number4 int not null,
//    number5 int not null,
//    number6 int not null,
//    bonus_number int not null
// );






















