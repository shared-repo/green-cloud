<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
pageContext.setAttribute("page-data", "This is Page Scope Data (from redirect)");
request.setAttribute("req-data", "This is Request Scope Data (from redirect)");
session.setAttribute("session-data", "This is Session Scope Data (from redirect)");
application.setAttribute("application-data", "This is Application Scope Data (from redirect)");


response.sendRedirect("04.result.jsp");

%>