import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EmployeeDao {
	
	public ArrayList<EmployeeDto> selectEmployeesByName(String nameKey) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EmployeeDto> employees = new ArrayList<>(); // 조회 결과를 저장할 컬렉션 객체
		try {
			// 1. 드라이버 준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 객체 만들기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employees", "green_cloud", "mysql");
						
			// 3. 명령 객체 만들기
			String sql = "SELECT emp_no, first_name, last_name, gender, birth_date, hire_date " +
				 	 	 "FROM employees " + 
				 	 	 "WHERE first_name LIKE ? OR last_name LIKE ?"; // ? : 여기에 데이터가 결합될 예정입니다.			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + nameKey + "%"); // SQL의 첫 번째 ?에 적용할 데이터
			pstmt.setString(2, "%" + nameKey + "%"); // SQL의 두 번째 ?에 적용할 데이터
			
			// 4. 명령 실행 ( 결과가 있으면 결과 저장 - select 인 경우 )
			rs = pstmt.executeQuery();
			
			// 5. 결과가 있으면 결과 처리
			while (rs.next()) {
				EmployeeDto emp = new EmployeeDto(); // 한 행의 데이터를 저장할 Dto 객체 만들기
				emp.setEmpNo(rs.getInt(1));
				emp.setFirstName(rs.getString(2));
				emp.setLastName(rs.getString(3));
				emp.setGender(rs.getString(4));
				emp.setBirthDate(rs.getDate(5));
				emp.setHireDate(rs.getDate(6));
				employees.add(emp); // 현재 행의 데이터를 저장한 객체를 조회 결과를 저장할 컬렉션 객체에 추가
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
		return employees;
	}

}
