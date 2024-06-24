package com.demoweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.demoweb.dto.AppSetting;
import com.demoweb.dto.MemberDto;

// 데이터베이스 테이블 만들기 + 초기 데이터 저장
//
// CREATE TABLE app_settings
// (
//	  setting_name varchar(100) primary key,
//    setting_value varchar(100) not null
// );
//
// INSERT INTO app_settings VALUES ("total_counter", "0");

public class AppSettingsDao {

	public AppSetting selectAppSettingBySettingName(String settingName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AppSetting appSetting = null; // 조회 결과를 저장할 변수
		try {
			// 1. 드라이버 준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 객체 만들기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demoweb", "green_cloud", "mysql");
			
			// 3. 명령 객체 만들기
			String sql = "SELECT setting_name, setting_value " +
						 "FROM app_settings " +
						 "WHERE setting_name = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, settingName);
			
			// 4. 명령 실행 ( 결과가 있으면 결과 저장 - select 인 경우 )
			rs = pstmt.executeQuery();
			
			// 5. 결과가 있으면 결과 처리
			if (rs.next()) {
				appSetting = new AppSetting(); // 조회 결과를 저장할 객체 생성
				appSetting.setSettingName(rs.getString(1)); // 객체에 조회한 각 값을 저장
				appSetting.setSettingValue(rs.getString(2)); // 객체에 조회한 각 값을 저장
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return appSetting; // 조회 결과를 저장한 객체 반환		
	}
	
	public void updateAppSetting(AppSetting appSetting) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// 1. 드라이버 준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 객체 만들기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demoweb", "green_cloud", "mysql");
			
			// 3. 명령 객체 만들기
			String sql = "UPDATE app_settings " +
						 "SET setting_value = ? " +
						 "WHERE setting_name = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, appSetting.getSettingValue());
			pstmt.setString(2, appSetting.getSettingName());
			
			// 4. 명령 실행 ( 결과가 있으면 결과 저장 - select 인 경우 )
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
