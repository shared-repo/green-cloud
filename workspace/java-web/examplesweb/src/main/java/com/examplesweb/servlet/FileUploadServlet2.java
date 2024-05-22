package com.examplesweb.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import org.apache.catalina.core.ApplicationPart;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(urlPatterns = { "/file-upload2" })
@MultipartConfig(location = "C:\\Users\\Administrator\\Downloads")
public class FileUploadServlet2 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ApplicationPart data1 = (ApplicationPart)req.getPart("data1");
		ApplicationPart data2 = (ApplicationPart)req.getPart("data2");
		ApplicationPart attach = (ApplicationPart)req.getPart("attach");
		
		System.out.println(data1.getName() + " : " + data1.getString("utf-8"));
		System.out.println(data2.getName() + " : " + data2.getString("utf-8"));
		System.out.println(attach.getName() + " : " + attach.getSubmittedFileName());
		
		ServletContext application = req.getServletContext(); 		// JSP의 application 내장 객체
		String path = application.getRealPath("/upload-files");		// 최종 파일 저장 경로
		String tempPath = application.getRealPath("/upload-temp");	// 임시 파일 저장 경로
		attach.write(new File(path, attach.getSubmittedFileName()).getAbsolutePath());
		
		resp.sendRedirect("10.file-list.jsp");
	}
	
}







