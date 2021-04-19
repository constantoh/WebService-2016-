<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title>모코엠시스MD통합포털</title>
</head>
<body>
<center>
	<h1>통합 포털 로그인</h1>
	<hr>
		<form action="MemberCheck.do" method="post">
			<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="id"/></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="pwd"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="로그인"/></td>
				</tr>
			</table>
		</form>
	<hr>
</center>
</body>
</html>
