package com.examplesweb.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(urlPatterns = { "/file-upload" })
public class FileUploadServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// multipart/form-data 방식으로 전송한 데이터는 request.getParameter로 읽을 수 없습니다.
//		String data1 = req.getParameter("data1");
//		System.out.println("data 1 : " + data1);
		
		// req.setCharacterEncoding("utf-8");

		//파일 업로드를 포함한 요청인지 확인 (multipart/form-data 형식 확인)
		if (ServletFileUpload.isMultipartContent(req) == false) {
			resp.sendRedirect("10.file-upload.jsp");
			return;
		}

		//경로 문자열을 저장할 변수
		//application.getRealPath('웹경로')
		//--> 가상경로(웹경로) -> 물리경로(컴퓨터경로)
		//--> http://..... -> C:/......
		ServletContext application = req.getServletContext(); 		// JSP의 application 내장 객체
		String path = application.getRealPath("/upload-files");		// 최종 파일 저장 경로
		String tempPath = application.getRealPath("/upload-temp");	// 임시 파일 저장 경로

		//전송 데이터 각 요소를 분리해서 개별 객체를 만들때 사용할 처리기
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 1024 * 2);	// 임시 파일을 만들지 결정하는 기준 설정
		factory.setRepository(new File(tempPath));	// 임시 파일 저장 경로 지정

		//요청 정보를 읽을 파서(Parser) 생성 (요청을 읽고 요소별로 분리)
		ServletFileUpload uploader = new ServletFileUpload(factory);
		uploader.setFileSizeMax(1024 * 1024 * 10);//최대 파일 크기

		//요청 정보를 파싱하고 개별 객체의 목록을 반환
		List<FileItem> items = null;
		try {
			items = uploader.parseRequest(req); // 요청의 각 데이터를 분해 + 포장 -> 리스트로 반환
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		////////////////////////////////////////////////////////////////////////////////

		//목록에 담긴 데이터 사용
		for (FileItem item : items) {			
			if (item.isFormField()) { // form-data인 경우 (File이 아닌 일반 데이터인 경우)
				System.out.println("DATA " + item.getFieldName() + " : " 	// 데이터 이름 ( <input> 요소의 name 속성 값 )
										   + item.getString("utf-8"));		// 데이터 값	( <input> 요소의 value 속성 값 )
			} else { //file인 경우
				String fileName = item.getName(); //파일 이름 가져오기
				if (fileName != null && fileName.length() > 0) { //내용이 있는 경우
					if (fileName.contains("\\")) { // iexplore 경우
						//C:\AAA\BBB\CCC.png -> CCC.png 
						fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
					}
					try {
						item.write(new File(path, fileName));	//파일 저장
					} catch (Exception e) {						
						e.printStackTrace();
					} 
					item.delete(); //임시 파일 삭제
				}
			}
		}

		resp.sendRedirect("10.file-list.jsp");
	}
	
}







