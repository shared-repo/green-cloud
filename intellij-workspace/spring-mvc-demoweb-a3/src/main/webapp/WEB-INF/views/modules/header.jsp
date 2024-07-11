<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
    	   	 
<%@ taglib prefix="c" uri="jakarta.tags.core" %> <%-- jstl 라이브러리의 core 객체들을 c:tagname 형식으로 사용하는 선언 --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
		
		<div id="header">
		
            <div class="title">
                <a href="/spring-demoweb/home">DEMO WEBSITE</a>
            </div>
            <div class="links">
            <c:choose>
            	<c:when test="${ empty loginuser }">
            	<%-- 
            	<a href="/spring-demoweb/account/login-form">로그인</a>
                <a href="/spring-demoweb/account/register-form">회원가입</a>
                --%>
                <a href="/spring-demoweb/account/login">로그인</a>
                <a href="/spring-demoweb/account/register">회원가입</a>
            	</c:when>
            	<c:otherwise>
            	${ loginuser.memberId }님 환영합니다.
            	<a href="/spring-demoweb/account/logout">로그아웃</a>
            	</c:otherwise>
            </c:choose>            
            </div>
        </div>
                
        <div id="menu">
            <div>
                <ul>
                    <li><a href="#"><spring:message code="menu.admin" /></a></li>
					<li><a href="/spring-demoweb/mail/send-mail"><spring:message code="menu.email" /></a></li>
					<li><a href="#"><spring:message code="menu.library" /></a></li>
					<li><a href="/spring-demoweb/board/list"><spring:message code="menu.board" /></a></li>
                </ul>
            </div>
		</div>
		
		<div id="counter" style="text-align:right; padding:5px; border: solid 1px">
		[TOTAL : ${ total == null ? "0" : total }]
		[CURRENT : ${ applicationScope.current == null ? "0" : current }]
		</div>