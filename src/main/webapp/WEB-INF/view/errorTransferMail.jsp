<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function v(){
		alert("메일을 보내지 못했습니다");
		opener.document.location.reload();
		self.close();
	}
</script>
</head>
<body>
<script>
v();
</script>
</body>
</html>