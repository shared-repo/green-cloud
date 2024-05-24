package com.demoweb.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.catalina.core.ApplicationPart;

import com.demoweb.common.Util;
import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardDto;
import com.demoweb.service.BoardService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/board/edit" })
@MultipartConfig(location = "D:\\instructor-och\\green-cloud\\workspace\\java-web\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\demoweb\\board-temp")
public class BoardEditServlet extends HttpServlet {

	private BoardService boardService = new BoardService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 1. 요청 데이터 읽기
		String sBoardNo = req.getParameter("boardno"); // 요청 데이터 읽기 : 사용자가 선택한 글번호 읽기
		int boardNo = Integer.parseInt(sBoardNo);
				
		// 2. 요청 처리 : 데이터베이스에 데이터 저장 -> BoardService를 호출해서 처리
		BoardService boardService = new BoardService();				
		BoardDto board = boardService.findBoardByBoardNo(boardNo);
		
		// 2. JSP에서 읽을 수 있도록 데이터 전달 ( request 객체에 저장 )
		req.setAttribute("board", board);
		
		// 3. 응답 컨텐츠 생산 ( JSP로 forward 이동 )		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/board/edit.jsp");
		rd.forward(req, resp); // 지정된 경로로 forward 이동
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 1. 데이터 읽기 ( @MultipartConfig 설정된 경우 컨테이너가 File + FormData를 분해해서 request에 저장 )
		ApplicationPart boardNoPart = (ApplicationPart)req.getPart("boardno"); 	// multipart/form-data 로 전송된 데이터 읽기 --> ApplicationPart 형식으로 반환
		ApplicationPart titlePart = (ApplicationPart)req.getPart("title"); 		// multipart/form-data 로 전송된 데이터 읽기 --> ApplicationPart 형식으로 반환
		ApplicationPart contentPart = (ApplicationPart)req.getPart("content"); 	// multipart/form-data 로 전송된 데이터 읽기 --> ApplicationPart 형식으로 반환
		ApplicationPart attachPart = (ApplicationPart)req.getPart("attach"); 	// multipart/form-data 로 전송된 데이터 읽기 --> ApplicationPart 형식으로 반환
		
		int boardNo = Integer.parseInt(boardNoPart.getString("utf-8"));
		
		// 2-1. board 테이블 데이터 수정을 위한 DTO 만들기
		BoardDto board = new BoardDto();
		board.setBoardNo(boardNo);
		board.setTitle(titlePart.getString("utf-8"));
		board.setContent(contentPart.getString("utf-8"));		
		
		ArrayList<BoardAttachDto> attachments = new ArrayList<>();
		board.setAttachments(attachments);
		
		// 2-2. 파일 저장
		String userFileName = attachPart.getSubmittedFileName();
		if (userFileName != null && userFileName.length() > 0) {
			String savedFileName = Util.makeUniqueFileName(userFileName);
			String dir = req.getServletContext().getRealPath("/board-attachments");
			// attachPart.write(dir + savedFileName);
			attachPart.write(new File(dir, savedFileName).getAbsolutePath());
			
			// 2-3. boardattach 테이블 데이터 추가를 위한 DTO 만들기
			BoardAttachDto attach = new BoardAttachDto();
			attach.setBoardNo(boardNo);
			attach.setUserFileName(userFileName);
			attach.setSavedFileName(savedFileName);
			attachments.add(attach);
		}
		
		// 2-5. 데이터베이스 데이터 수정 + 저장
		boardService.modifyBoard(board);
		
		// 3. 상세보기로 이동 (redirect)
		resp.sendRedirect("detail?boardno=" + boardNo);
		
	}
	
}





















