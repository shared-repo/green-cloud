package com.demoweb.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.demoweb.common.Util;
import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardDto;
import com.demoweb.dto.MemberDto;
import com.demoweb.service.BoardService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/board/write" })
public class BoardWriteServlet extends HttpServlet {

	private BoardService boardService = new BoardService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1-1. 로그인 여부 확인 : Session에 인증 데이터있는지 확인
		//      이 코드는 중복을 피하기 위해 AuthFilter로 이동
//		HttpSession session = req.getSession(); // 서블릿에는 session 내장 객체가 없으므로 request 객체에서 유도
//		if (session.getAttribute("loginuser") == null) { // 1.2. 로그인 하지 않은 사용자는
//			// 로그인 화면으로 이동 (redirect)
//			resp.sendRedirect("/demoweb/account/login");
//			return;
//		}
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/board/write.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//파일 업로드를 포함한 요청인지 확인 (multipart/form-data 형식 확인)
		if (ServletFileUpload.isMultipartContent(req) == false) {
			resp.sendRedirect("list");
			return;
		}

		//경로 문자열을 저장할 변수
		//application.getRealPath('웹경로')
		//--> 가상경로(웹경로) -> 물리경로(컴퓨터경로)
		//--> http://..... -> C:/......
		ServletContext application = req.getServletContext(); // JSP의 application 내장 객체
		String path = application.getRealPath("/board-attachments");	//최종 파일 저장 경로
		String tempPath = application.getRealPath("/board-temp");		//임시 파일 저장 경로

		//전송 데이터 각 요소를 분리해서 개별 객체를 만들때 사용할 처리기
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 1024 * 2);	//임시 파일을 만들지 결정하는 기준 설정
		factory.setRepository(new File(tempPath));	//임시 파일 저장 경로 지정

		//요청 정보를 읽을 파서(Parser) 생성 (요청을 읽고 요소별로 분리)
		ServletFileUpload uploader = new ServletFileUpload(factory);
		uploader.setFileSizeMax(1024 * 1024 * 10);//최대 파일 크기

		BoardDto board = new BoardDto();	// 게시글 정보를 저장하는 DTO 객체
		ArrayList<BoardAttachDto> attachments = new ArrayList<>(); // 첨부파일 정보를 저장하는 DTO 객체
		board.setAttachments(attachments);
		
		//요청 정보를 파싱하고 개별 객체의 목록을 반환
		try {
			List<FileItem> items = uploader.parseRequest(req);
			
			//목록에 담긴 데이터 사용
			for (FileItem item : items) {			
				if (item.isFormField()) { //form-data인 경우 (File이 아닌 일반 데이터인 경우)
					if (item.getFieldName().equals("title")) {
						board.setTitle(item.getString("utf-8"));
					} else if (item.getFieldName().equals("writer")) {
						board.setWriter(item.getString("utf-8"));
					} else if (item.getFieldName().equals("content")) {
						board.setContent(item.getString("utf-8"));
					}					
				} else { //file인 경우
					String fileName = item.getName(); //파일 이름 가져오기
					if (fileName != null && fileName.length() > 0) { //내용이 있는 경우
						if (fileName.contains("\\")) { // iexplore 경우
							//C:\AAA\BBB\CCC.png -> CCC.png 
							fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
						}
						String uniqueFileName = Util.makeUniqueFileName(fileName);
						try {
							//item.write(new File(path, fileName));//파일 저장
							item.write(new File(path, uniqueFileName));//파일 저장
						} catch (Exception e) {
							e.printStackTrace();
						} 
						item.delete(); //임시 파일 삭제
						
						// 첨부파일 정보를 객체에 저장
						BoardAttachDto attachment = new BoardAttachDto();
						attachment.setUserFileName(fileName);
						attachment.setSavedFileName(uniqueFileName);
						attachments.add(attachment);
					}
				}
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		
		// 2. 데이터 저장 (서비스 호출)
		boardService.writeBoard(board);
		
		// 3. 목록으로 이동
		resp.sendRedirect("/demoweb/board/list");
		
		
	}
	
}
