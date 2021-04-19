<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function v(){
		alert("사이트 단계를 선택하세요");
	}
</script>
</head>
<body>
<%
	int uid = (Integer)session.getAttribute("UID");
	String uidString = Integer.toString(uid);
	String forwardURL = "http://10.10.1.115:8080/WebServiceThird/CustomInfo.do?cus_uid=" + uidString;
%>
<a id="oh" href=<%=forwardURL %>></a>
<script> 
v();
oh.click(); 
</script>
</body>
</html>