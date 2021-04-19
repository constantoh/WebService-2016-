<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function v(){
		alert("에러!");
	}
</script>
</head>
<body>
<%
	String forwardURL = "http://10.10.1.115:8080/WebServiceThird/Index2.jsp";
%>
<a id="oh" href=<%=forwardURL %>></a>
<script> 
v();
//opener.document.location.reload();

oh.click(); 
</script>
</body>
</html>