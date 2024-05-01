import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

// Layer 분할

public class Ex03 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("검색할 직원 이름 : ");
		String nameKey = scanner.nextLine();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. 드라이버 준비
			// DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 객체 만들기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employees", "green_cloud", "mysql");
			
			// 3-1. 명령 객체 만들기
//			String sql = "SELECT emp_no, first_name, last_name, gender, hire_date " +
//					 	 "FROM employees " + 
//						 "WHERE first_name LIKE '%" + nameKey + "%' OR last_name LIKE '%" + nameKey + "%'";			
//			pstmt = conn.prepareStatement(sql);
			
			// 3-2. 명령 객체 만들기
			String sql = "SELECT emp_no, first_name, last_name, gender, hire_date " +
				 	 	 "FROM employees " + 
				 	 	 "WHERE first_name LIKE ? OR last_name LIKE ?"; // ? : 여기에 데이터가 결합될 예정입니다.			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + nameKey + "%"); // SQL의 첫 번째 ?에 적용할 데이터
			pstmt.setString(2, "%" + nameKey + "%"); // SQL의 두 번째 ?에 적용할 데이터
			
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




















