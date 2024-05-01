import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// JDBC 기본 코드 테스트 

public class Ex01_1 {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. 드라이버 준비
			// DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 객체 만들기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employees", "green_cloud", "mysql");
			
			// 3. 명령 객체 만들기
			String sql = "DELETE FROM employees WHERE emp_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, -1000);
			
			// 4. 명령 실행 ( 결과가 있으면 결과 저장 - select 인 경우 )
			pstmt.executeUpdate(); // executeUpdate : insert, update, delete 등의 구문에 사용
			
			System.out.println("데이터를 삭제했습니다. ");
			// 5. 결과가 있으면 결과 처리
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		

	}

}




















