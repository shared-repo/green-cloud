<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  
<%
//방문 횟수를 저장하는 변수
int cookieData = 1, sessionData = 1, applicationData = 1;

//1. Cookie
//요청객체로부터 브라우저가 전송한 쿠키 목록을 반환
Cookie[] cookies = request.getCookies();//요청 정보에 포함된 쿠키 데이터 반환 (처음 요청에는 없음)

if (cookies != null) {
	for (int i = 0; i < cookies.length; i++) {
		//이름이 cookiedata인 쿠키를 찾아서
		if (cookies[i].getName().equals("cookiedata")) {
			//쿠키의 값을 변수에 저장
			String data = cookies[i].getValue();//쿠키는 항상 문자열
			data = URLDecoder.decode(data, "utf-8");//비영문자 처리
			cookieData = Integer.parseInt(data);//문자열 -> 숫자
		}
	}
}

//이름이 cookiedata인 쿠키 객체 생성하고 값을 지정
Cookie cookie = 
	new Cookie("cookiedata", //쿠기의 이름
		URLEncoder.encode(String.format("%d", cookieData + 1), "utf-8"));

//만료시간을 지정하면 쿠키를 파일로 기록
//-> 브라우저를 종료하고 다시 실행해도 쿠키 값을 읽을 수 있습니다.
// cookie.setMaxAge(60 * 10);
// cookie.setMaxAge(0); //--> 쿠키 제거

response.addCookie(cookie);//응답 객체에 쿠키를 기록 --> 브라우저에 쿠기 저장

//2. Session
//세션에 값이 들어있다면 그 값을 읽어서 변수에 저장
if (session.getAttribute("sessiondata") != null) {
	//여기서 읽는 값은 이전 요청에서 기록한 데이터
	sessionData = (Integer)session.getAttribute("sessiondata");
}

//세션에 값을 저장
session.setAttribute("sessiondata", Integer.valueOf(sessionData + 1));

// 추가 요청이 없는 경우 세션을 유지하는 시간 설정
// System.out.println(session.getMaxInactiveInterval());
// session.setMaxInactiveInterval(1); // 0 or less : infinite


//3. Application (ServletContext)
if (application.getAttribute("applicationdata") != null) {
	applicationData = (Integer)application.getAttribute("applicationdata");
}

application.setAttribute("applicationdata", Integer.valueOf(applicationData + 1));

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
</head>
<body>
	<a href="08.statemanagement.jsp">새로고침</a>
	&nbsp;&nbsp;
	<a href="08.statemanagement2.jsp">이동</a>
	<hr />
	COOKIE DATA : <%= cookieData %><br />
	SESSION DATA : <%= sessionData %><br />
	APPLICATION DATA : <%= applicationData %><br />
</body>
</html>