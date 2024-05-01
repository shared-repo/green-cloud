import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class LottoDao {
	
	public void deleteAllWinningNumbers() {
		
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
			// 5. 결과가 있으면 결과 처리
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 6. 연결 종료					
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
	}

	
	public void insertAllWinningNumbers(ArrayList<LottoWinningNumber> numbers) {
		
	}
}
