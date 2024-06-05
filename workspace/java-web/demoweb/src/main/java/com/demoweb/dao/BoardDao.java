package com.demoweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardCommentDto;
import com.demoweb.dto.BoardDto;

public class BoardDao {

	public void insertBoard(BoardDto board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1. 드라이버 준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 객체 만들기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demoweb", "green_cloud", "mysql");
			
			// 3. 명령 객체 만들기
			String sql = "INSERT INTO board (title, writer, content) VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			
			// 4. 명령 실행 ( 결과가 있으면 결과 저장 - select 인 경우 )
			pstmt.executeUpdate(); // insert, update, delete sql은 executeUpdate로 실행
			pstmt.close();
			
			// sql = "SELECT boardNo FROM board WHERE title = ? AND writer = ? AND content = ?";
			sql = "SELECT LAST_INSERT_ID()"; // 현재 세션에서 발생한 마지막 AUTO_INCREMENT 값 조회
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();		
			
			// 5. 결과가 있으면 결과 처리
			rs.next();
			int boardNo = rs.getInt(1);
			board.setBoardNo(boardNo);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
	}
	
	public ArrayList<BoardDto> selectAllBoard() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardDto> boardList = new ArrayList<>();
		try {
			// 1. 드라이버 준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 객체 만들기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demoweb", "green_cloud", "mysql");
			
			// 3. 명령 객체 만들기
			// String sql = "SELECT boardno, title, writer, readcount, writedate, modifydate FROM board WHERE deleted = false";
			String sql = "SELECT boardno, title, writer, readcount, writedate, modifydate, deleted " +
						 "FROM board " +
						 "ORDER BY boardno DESC ";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 명령 실행 ( 결과가 있으면 결과 저장 - select 인 경우 )
			rs = pstmt.executeQuery(); // select sql은 executeQuery로 실행
			
			// 5. 결과가 있으면 결과 처리
			while (rs.next()) {
				BoardDto board = new BoardDto();
				board.setBoardNo(rs.getInt(1));
				board.setTitle(rs.getString(2));
				board.setWriter(rs.getString(3));
				board.setReadCount(rs.getInt(4));
				board.setWriteDate(rs.getDate(5));
				board.setModifyDate(rs.getDate(6));
				board.setDeleted(rs.getBoolean(7));
				boardList.add(board);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return boardList;
	}

	public void insertBoardAttach(BoardAttachDto attach) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. 드라이버 준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 객체 만들기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demoweb", "green_cloud", "mysql");
			
			// 3. 명령 객체 만들기
			String sql = "INSERT INTO boardattach (boardno, userfilename, savedfilename) VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attach.getBoardNo());
			pstmt.setString(2, attach.getUserFileName());
			pstmt.setString(3, attach.getSavedFileName());
			
			// 4. 명령 실행 ( 결과가 있으면 결과 저장 - select 인 경우 )
			pstmt.executeUpdate(); // insert, update, delete sql은 executeUpdate로 실행
			
			// 5. 결과가 있으면 결과 처리
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 6. 연결 종료
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
	}
	
	// 글번호를 받아서 게시글 조회 및 반환 ( primary key 검색이므로 단일 객체 반환 )
	public BoardDto selectBoardByBoardNo(int boardNo) {
		
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		BoardDto board = null;		// 조회한 데이터를 저장할 DTO 객체
		
		try {
			// 1. Driver 등록
			// DriverManager.registerDriver(new Driver());
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demoweb", "green_cloud", "mysql");
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = 
					"SELECT boardno, title, writer, content, readcount, writedate, modifydate " +
					"FROM board " +
					"WHERE boardno = ? AND deleted = FALSE ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); // executeQuery : select 일 때 사용하는 메서드
						
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) {	// 결과 집합의 다음 행으로 이동
				board = new BoardDto();
				board.setBoardNo(rs.getInt(1));
				board.setTitle(rs.getString(2));
				board.setWriter(rs.getString(3));
				board.setContent(rs.getString(4));
				board.setReadCount(rs.getInt(5));
				board.setWriteDate(rs.getDate(6));
				board.setModifyDate(rs.getDate(7));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return board;
	}

	public ArrayList<BoardAttachDto> selectBoardAttachByBoardNo(int boardNo) {
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		ArrayList<BoardAttachDto> attachments = new ArrayList<>();
		
		try {
			// 1. Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demoweb", "green_cloud", "mysql");
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = 
					"SELECT attachno, boardno, userfilename, savedfilename, downloadcount " +
					"FROM boardattach " +
					"WHERE boardno = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); // executeQuery : select 일 때 사용하는 메서드
						
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) {	// 결과 집합의 다음 행으로 이동
				BoardAttachDto attach = new BoardAttachDto();
				attach.setAttachNo(rs.getInt(1));
				attach.setBoardNo(rs.getInt(2));
				attach.setUserFileName(rs.getString(3));
				attach.setSavedFileName(rs.getString(4));
				attach.setDownloadCount(rs.getInt(5));
				attachments.add(attach);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return attachments;
	}

	public BoardAttachDto selectBoardAttachByAttachNo(int attachNo) {
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		BoardAttachDto attach = null;
		
		try {
			// 1. Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demoweb", "green_cloud", "mysql");
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = 
					"SELECT attachno, boardno, userfilename, savedfilename, downloadcount " +
					"FROM boardattach " +
					"WHERE attachno = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attachNo);
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); // executeQuery : select 일 때 사용하는 메서드
						
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			if (rs.next()) {	// 결과 집합의 다음 행으로 이동
				attach = new BoardAttachDto();
				attach.setAttachNo(rs.getInt(1));
				attach.setBoardNo(rs.getInt(2));
				attach.setUserFileName(rs.getString(3));
				attach.setSavedFileName(rs.getString(4));
				attach.setDownloadCount(rs.getInt(5));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return attach;
	}

	public void updateBoardDeleted(int boardNo) {
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		try {
			// 1. Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demoweb", "green_cloud", "mysql");
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = "UPDATE board SET deleted = TRUE WHERE boardno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			// 4. 명령 실행
			pstmt.executeUpdate();
						
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
	}

	public void deleteBoardAttach(int attachNo) {
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		try {
			// 1. Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demoweb", "green_cloud", "mysql");
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = "DELETE FROM boardattach WHERE attachno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attachNo);
			
			// 4. 명령 실행
			pstmt.executeUpdate();
						
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
	}

	public void updateBoard(BoardDto board) {
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		try {
			// 1. Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demoweb", "green_cloud", "mysql");
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = "UPDATE board SET title = ?, content = ? WHERE boardno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getBoardNo());
			
			// 4. 명령 실행
			pstmt.executeUpdate();
						
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
	}

	public void insertComment(BoardCommentDto comment) {
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		try {
			// 1. Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demoweb", "green_cloud", "mysql");
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = "INSERT INTO boardcomment (boardno, writer, content, groupno, step, depth) VALUES (?, ?, ?, -1, 1, 0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getBoardNo());
			pstmt.setString(2, comment.getWriter());
			pstmt.setString(3, comment.getContent());
			
			// 4-1. 명령 실행 1 -> 댓글 삽입
			pstmt.executeUpdate();
			pstmt.close();
			
			// 4-2. 명령 실행 2 -> groupno 변경 (최상위 글의 글번호를 groupno로 사용)
			sql = "UPDATE boardcomment SET groupno = LAST_INSERT_ID() where commentno = LAST_INSERT_ID()";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
						
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
	}

	public ArrayList<BoardCommentDto> selectBoardCommentByBoardNo(int boardNo) {
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		ArrayList<BoardCommentDto> comments = new ArrayList<>();
		
		try {
			// 1. Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demoweb", "green_cloud", "mysql");
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = 
					"SELECT commentno, boardno, writer, content, writedate, modifydate, groupno, step, depth, deleted " +
					"FROM boardcomment " +
					"WHERE boardno = ? " +
					"ORDER BY groupno DESC, step ASC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); // executeQuery : select 일 때 사용하는 메서드
						
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) {	// 결과 집합의 다음 행으로 이동
				BoardCommentDto comment = new BoardCommentDto();
				comment.setCommentNo(rs.getInt(1));
				comment.setBoardNo(rs.getInt(2));
				comment.setWriter(rs.getString(3));
				comment.setContent(rs.getString(4));
				comment.setWriteDate(rs.getTimestamp(5));
				comment.setModifyDate(rs.getTimestamp(6));
				comment.setGroupNo(rs.getInt(7));
				comment.setStep(rs.getInt(8));
				comment.setDepth(rs.getInt(9));
				comment.setDeleted(rs.getBoolean(10));
				
				comments.add(comment);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return comments;
	}

	public void deleteComment(int commentNo) {
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		try {
			// 1. Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demoweb", "green_cloud", "mysql");
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = "UPDATE boardcomment set deleted = true WHERE commentno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentNo);
			
			// 4. 명령 실행
			pstmt.executeUpdate();
						
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}		
	}

	public void updateComment(BoardCommentDto comment) {
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		try {
			// 1. Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demoweb", "green_cloud", "mysql");
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = "UPDATE boardcomment set content = ? WHERE commentno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getContent());
			pstmt.setInt(2, comment.getCommentNo());
			
			// 4. 명령 실행
			pstmt.executeUpdate();
						
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}		
		
	}

	public void insertReComment(BoardCommentDto comment) {
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		try {
			// 1. Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demoweb", "green_cloud", "mysql");
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = "INSERT INTO boardcomment (boardno, writer, content, groupno, step, depth) VALUES (?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getBoardNo());
			pstmt.setString(2, comment.getWriter());
			pstmt.setString(3, comment.getContent());
			// pstmt.setInt(4, comment.getCommentNo());
			pstmt.setInt(4, comment.getGroupNo());
			pstmt.setInt(5, comment.getStep());
			pstmt.setInt(6, comment.getDepth());
			  
			// 4-1. 명령 실행
			pstmt.executeUpdate();
									
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
	}

	public BoardCommentDto selectBoardCommentByCommentNo(int commentNo) {
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		BoardCommentDto comment = null;
		
		try {
			// 1. Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demoweb", "green_cloud", "mysql");
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = 
					"SELECT commentno, boardno, writer, content, writedate, modifydate, groupno, step, depth, deleted " +
					"FROM boardcomment " +
					"WHERE commentno = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentNo);
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); // executeQuery : select 일 때 사용하는 메서드
						
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) {	// 결과 집합의 다음 행으로 이동
				comment = new BoardCommentDto();
				comment.setCommentNo(rs.getInt(1));
				comment.setBoardNo(rs.getInt(2));
				comment.setWriter(rs.getString(3));
				comment.setContent(rs.getString(4));
				comment.setWriteDate(rs.getTimestamp(5));
				comment.setModifyDate(rs.getTimestamp(6));
				comment.setGroupNo(rs.getInt(7));
				comment.setStep(rs.getInt(8));
				comment.setDepth(rs.getInt(9));
				comment.setDeleted(rs.getBoolean(10));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return comment;
	}

	public void updateStep(BoardCommentDto parent) {
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		try {
			// 1. Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demoweb", "green_cloud", "mysql");
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = "UPDATE boardcomment SET step = step + 1 WHERE groupno = ? AND step > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, parent.getGroupNo());
			pstmt.setInt(2, parent.getStep());
			
			// 4-1. 명령 실행
			pstmt.executeUpdate();
									
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
	}
	
}
















