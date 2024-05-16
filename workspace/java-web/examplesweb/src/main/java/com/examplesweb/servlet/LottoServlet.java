package com.examplesweb.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/select-numbers" })
public class LottoServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 요청 데이터 읽기
		String sMinAvg = req.getParameter("min_avg");
		String sMaxAvg = req.getParameter("max_avg");
		int minAvg = Integer.parseInt(sMinAvg);
		int maxAvg = Integer.parseInt(sMaxAvg);
		
		// 2. 요청 처리 (여기서는 번호 뽑기)		
		int[] numbers = new int[6];
		int avg = 0;
		boolean valid = false;
		do {
			for (int i = 0; i < numbers.length; i++) {
				
				numbers[i] = (int)(Math.random() * 45) + 1; // 1 ~ 45, random
				//중복 검사
				for (int j = 0; j < i; j++) {
					if (numbers[i] == numbers[j]) { // 중복인 경우
						//i--;	// 현재 위치에서 다시 뽑기
						i = -1;	// 처음부터 다시 뽑기
						break;
					}
				}
			}
			int sum = 0;
			for (int i = 0; i < numbers.length; i++) {
				sum += numbers[i];
			}
			avg = sum / numbers.length;
			valid = avg >= minAvg && avg <= maxAvg;
		} while (!valid);
		
		
		// 3. JSP에서 읽을 수 있도록 데이터 저장
		req.setAttribute("numbers", numbers); // 데이터는 Object로 저장 -> 읽을 때에는 다시 배열로 형변환해서 읽어야 합니다.
		req.setAttribute("avg", avg);
		
		// 4. 응답 컨텐츠 (HTML) 생산 + 응답 --> JSP로 forward
		RequestDispatcher dispatcher = req.getRequestDispatcher("06.lotto-result.jsp");
		dispatcher.forward(req, resp);
	}

}















