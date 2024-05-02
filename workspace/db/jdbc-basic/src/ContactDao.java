import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ContactDao {
	
	public void insertContact(ContactDto contact) {
		
		// 사용자가 입력한 연락처 정보를 DB에 insert
		Connection conn = null;
		PreparedStatement pstmt = null;				
		try {
			// 1. 드라이버 준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 객체 만들기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/market_db", "green_cloud", "mysql");
						
			// 3-1. 명령 객체 만들기
			String sql = "INSERT INTO contact (name, phone, email) VALUES (?, ?, ?)";
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
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 객체 만들기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/market_db", "green_cloud", "mysql");
						
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

	
	
}
