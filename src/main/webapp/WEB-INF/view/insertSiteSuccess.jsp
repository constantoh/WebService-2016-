<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	System.out.println("insertSiteSuccess");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<%
	int uid = (Integer)session.getAttribute("UID");
	String uidString = Integer.toString(uid);
	String forwardURL = "http://10.10.1.115:8080/WebServiceThird/CustomInfo.do?cus_uid=" + uidString;
%>
<a id="oh" href=<%=forwardURL %>></a>
<script>
alert("사이트를 등록하였습니다.");
oh.click(); 
</script>
</body>
</html>