<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	String name = request.getParameter("name");


	System.out.println("hi query 1" + name);

	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=utf8");
	
	System.out.println("hi query 2" + name);

%>
<%=name %>님의 최근 3일 실적
