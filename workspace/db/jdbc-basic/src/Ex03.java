import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

// Layer 분할

public class Ex03 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("검색할 직원 이름 : ");
		String nameKey = scanner.nextLine();
		
		EmployeeDao dao = new EmployeeDao();
		ArrayList<EmployeeDto> employees = dao.selectEmployeesByName(nameKey);
		
		for (EmployeeDto emp : employees) {
			System.out.println(emp);
		}
		
		scanner.close();

	}

}




















