import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Ex01 {

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
			String sql = "SELECT emp_no, first_name, last_name, gender, hire_date FROM employees LIMIT 100";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 명령 실행 ( 결과가 있으면 결과 저장 - select 인 경우 )
			rs = pstmt.executeQuery();
			
			// 5. 결과가 있으면 결과 처리
			while (rs.next()) {
				System.out.printf("[%d][%s][%s][%s][%s]\n", 
						rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
			}
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




















