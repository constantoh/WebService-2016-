<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.mocomsys.sangsoo.vo.User"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
	System.out.println("TransferMailPopUp.jsp");
	User user = (User)session.getAttribute("user");
	String email_ID = user.getEmail_id();
%>
<body>
	<form method="post" action="TransferMail.do">
		<table cellspacing=5>
	   		 <tr>
		        <td>보내는 사람</td>
		        <td>: <input type="text" name="from" size=50 readonly value="<%=email_ID  %>"></td>
		    </tr>
		    <tr>
		        <td>받는 사람</td>
		        <td>: <input type="text" name="to"   size=50></td>
		    </tr>
		    <tr>
		        <td>참조할 사람</td>
		        <td>: <input type="text" name="cc"   size=50></td>
		    </tr>
		    <tr>
		        <td>제목</td>
		        <td>: <input type="text" name="subject" size=50></td>
		    </tr>
		    <tr>
		        <td colspan=2><textarea rows=12 cols=70 name="content" style="resize: none; wrap:hard;"></textarea></td>
		    </tr>
		    <tr>
		        <td colspan=2><input type="submit" value="보내기"></td>
		    </tr>
		</table>
	</form>
</body>
</html>