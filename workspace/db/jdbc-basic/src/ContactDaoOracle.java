import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ContactDaoOracle {
	
	/*
	 * CREATE SEQUENCE CONTACT_SEQUENCE;
	 * 
	 * SELECT CONTACT_SEQUENCE.NEXTVAL FROM DUAL; -- NEXTVAL : 다음 번호 뽑기 SELECT
	 * CONTACT_SEQUENCE.CURRVAL FROM DUAL; -- CURRVAL : 마지막으로 뽑은 값 읽기
	 * 
	 * CREATE TABLE CONTACT 
	 * ( 
	 * 		NO NUMBER PRIMARY KEY
	 * 		, NAME VARCHAR2(100) NOT NULL 
	 * 		, PHONE VARCHAR2 (50) NULL 
	 * 		, EMAIL VARCHAR2 (50) NULL 
	 * 		, REGIDATE DATE DEFAULT (SYSDATE) 
	 * );
	 *
	 */
	
	private Connection getConnection() throws Exception {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		return DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe", "green_cloud", "oracle");
								
	}
	
	public void insertContact(ContactDto contact) {
		
		// 사용자가 입력한 연락처 정보를 DB에 insert
		Connection conn = null;
		PreparedStatement pstmt = null;				
		try {
			// 1. 드라이버 준비
			// 2. 연결 객체 만들기
			conn = getConnection();
						
			// 3-1. 명령 객체 만들기
			String sql = "INSERT INTO contact (no, name, phone, email) " + 
						 "					  VALUES (contact_sequence.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, contact.getName());
			pstmt.setString(2, contact.getPhone());
			pstmt.setString(3, contact.getEmail());
				
			// 4-1. 명령 실행
			pstmt.executeUpdate();
					
			// 5. 결과가 있으면 결과 처리
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 6. 연결 종료					
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
	}
	
	public ArrayList<ContactDto> selectAllContacts() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ContactDto> contacts = new ArrayList<>(); // 조회 결과를 저장할 컬렉션 객체
		try {
			// 1. 드라이버 준비
			// 2. 연결 객체 만들기
			conn = getConnection();
						
			// 3. 명령 객체 만들기
			String sql = "SELECT no, name, phone, email, regidate " +
				 	 	 "FROM contact ";			
			pstmt = conn.prepareStatement(sql);
			
			// 4. 명령 실행 ( 결과가 있으면 결과 저장 - select 인 경우 )
			rs = pstmt.executeQuery();
			
			// 5. 결과가 있으면 결과 처리			
			while (rs.next()) {
				ContactDto c = new ContactDto(); // 한 행의 데이터를 저장할 Dto 객체 만들기
				c.setNo(rs.getInt(1));
				c.setName(rs.getString(2));
				c.setPhone(rs.getString(3));
				c.setEmail(rs.getString(4));
				c.setRegiDate(rs.getDate(5));
				contacts.add(c); // 현재 행의 데이터를 저장한 객체를 조회 결과를 저장할 컬렉션 객체에 추가
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		// 조회 결과 return
		return contacts;
	}

	public ArrayList<ContactDto> selectContactsByName(String nameToSearch) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ContactDto> contacts = new ArrayList<>(); // 조회 결과를 저장할 컬렉션 객체
		try {
			// 1. 드라이버 준비
			// 2. 연결 객체 만들기
			conn = getConnection();
						
			// 3. 명령 객체 만들기
			String sql = "SELECT no, name, phone, email, regidate " +
				 	 	 "FROM contact " +
				 	 	 "WHERE name LIKE ?";			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + nameToSearch + "%");
			
			// 4. 명령 실행 ( 결과가 있으면 결과 저장 - select 인 경우 )
			rs = pstmt.executeQuery();
			
			// 5. 결과가 있으면 결과 처리			
			while (rs.next()) {
				ContactDto c = new ContactDto(); // 한 행의 데이터를 저장할 Dto 객체 만들기
				c.setNo(rs.getInt(1));
				c.setName(rs.getString(2));
				c.setPhone(rs.getString(3));
				c.setEmail(rs.getString(4));
				c.setRegiDate(rs.getDate(5));
				contacts.add(c); // 현재 행의 데이터를 저장한 객체를 조회 결과를 저장할 컬렉션 객체에 추가
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		// 조회 결과 return
		return contacts;
	}
	
	public void deleteContactByNo(int noToDelete) {

		Connection conn = null;
		PreparedStatement pstmt = null;				
		try {
			// 1. 드라이버 준비
			// 2. 연결 객체 만들기
			conn = getConnection();
						
			// 3-1. 명령 객체 만들기
			String sql = "DELETE FROM contact WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noToDelete);
				
			// 4-1. 명령 실행
			pstmt.executeUpdate();
					
			// 5. 결과가 있으면 결과 처리
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 6. 연결 종료					
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
	}
}
