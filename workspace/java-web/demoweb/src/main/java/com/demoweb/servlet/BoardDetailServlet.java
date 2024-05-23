package com.demoweb.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.demoweb.dto.BoardDto;
import com.demoweb.service.BoardService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/board/detail.action" })
public class BoardDetailServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 요청 처리
		String sBoardNo = req.getParameter("boardNo"); // 요청 데이터 읽기 : 사용자가 선택한 글번호 읽기
		int boardNo = Integer.parseInt(sBoardNo);
		String sPageNo = req.getParameter("pageNo");
		int pageNo = Integer.parseInt(sPageNo);
		
		// 사용자가 읽은 글 목록을 세션에서 가져오기
		HttpSession session = req.getSession();
		ArrayList<Integer> readList = (ArrayList<Integer>)session.getAttribute("read-list");
		if (readList == null) { // 세션에 목록이 없으면 
			readList = new ArrayList<>(); // 목록 새로 만들기
			session.setAttribute("read-list", readList); // 세션에 목록 등록
		}
				
		BoardService boardService = new BoardService();
		if (!readList.contains(boardNo)) { // 현재 글 번호가 읽은 글 목록에 포함되지 않은 경우
			
			readList.add(boardNo); // 읽은 글 목록에 현개 글 번호 추가			
		}
		
		BoardDto board = boardService.findBoardByBoardNo(boardNo);
		
		if (board == null) { // 조회되지 않은 경우 (글 번호가 잘못되었거나 또는 삭제된 글인 경우)
			resp.sendRedirect("list.action");
			return;
		}
		
		// 2. JSP에서 읽을 수 있도록 데이터 전달 ( request 객체에 저장 )
		req.setAttribute("board", board);
		req.setAttribute("pageNo", pageNo);
		
		// 3. 응답 컨텐츠 생산 ( JSP로 forward 이동 )		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/board/detail.jsp");
		rd.forward(req, resp); // 지정된 경로로 forward 이동
	}

}