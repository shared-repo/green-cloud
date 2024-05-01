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
		Connection conn = null;
		PreparedStatement pstmt = null;				
		try {
			// 1. 드라이버 준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 객체 만들기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/market_db", "green_cloud", "mysql");
			
			// 3-1. 명령 객체 만들기
			String sql = "INSERT INTO lotto_winning_number VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			for (LottoWinningNumber n : numbers) {
				pstmt.setInt(1, n.getRnd());
				long tick = n.getGameDate().getTime(); // 1970.1.1 00:00:00 초 이후에 경과된 시간을 1/1000초 단위로 계산한 값
				pstmt.setDate(2, new java.sql.Date(tick));
				pstmt.setInt(3, n.getNumber1());
				pstmt.setInt(4, n.getNumber2());
				pstmt.setInt(5, n.getNumber3());
				pstmt.setInt(6, n.getNumber4());
				pstmt.setInt(7, n.getNumber5());
				pstmt.setInt(8, n.getNumber6());
				pstmt.setInt(9, n.getBonusNumber());
				
				// 4-1. 명령 실행
				pstmt.executeUpdate(); 
			}
			
			// 5. 결과가 있으면 결과 처리
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 6. 연결 종료					
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
	}

	public void insertAllWinningNumbersWithBatch(ArrayList<LottoWinningNumber> numbers) {
		Connection conn = null;
		PreparedStatement pstmt = null;				
		try {
			// 1. 드라이버 준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 객체 만들기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/market_db", "green_cloud", "mysql");
			
			// 3-1. 명령 객체 만들기
			String sql = "INSERT INTO lotto_winning_number VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			for (LottoWinningNumber n : numbers) {
				pstmt.setInt(1, n.getRnd());
				long tick = n.getGameDate().getTime(); // 1970.1.1 00:00:00 초 이후에 경과된 시간을 1/1000초 단위로 계산한 값
				pstmt.setDate(2, new java.sql.Date(tick));
				pstmt.setInt(3, n.getNumber1());
				pstmt.setInt(4, n.getNumber2());
				pstmt.setInt(5, n.getNumber3());
				pstmt.setInt(6, n.getNumber4());
				pstmt.setInt(7, n.getNumber5());
				pstmt.setInt(8, n.getNumber6());
				pstmt.setInt(9, n.getBonusNumber());
				
				// 4-1. 명령 실행
				// pstmt.executeUpdate();
				pstmt.addBatch();
			}
			
			// 4-1. 명령 실행
			pstmt.executeBatch();
			
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

























