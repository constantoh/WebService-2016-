<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.mocomsys.sangsoo.vo.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	System.out.println("popup.jsp!!!");
	String room = (String)session.getAttribute("room");
	User user = (User)session.getAttribute("user");
	String name = user.getName();
	System.out.println(name);
	System.out.println(room);
	//session.setAttribute("roomNo", room);
	session.setAttribute("user", user);
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
		<form action="BookConferenceRoom.do" method="post">	
		<table class="table table-bordered" style="width:75%" border="2" cellpadding="5" cellspacing="5">
			<tbody>
				<tr>
					<td style="width:80px">자원</td>
					<td>
						<input type="text" style="width:90%;" name="siteName" id="siteName" value="<%=room%>" readonly>
					</td>
				</tr>
				<tr>
					<td style="width:80px">제목</td>
					<td>
						<input type="text" style="width:90%;" name="title" id="title">
					</td>
				</tr>
				<tr>
					<td style="width:80px">신청 사유</td>
					<td>
						<input type="text" style="width:90%;" name="cause" id="cause">
					</td>
				</tr>
				<tr>
					<td style="width:80px">사용 기간</td>
					<td>
							<input type="text" style="width:10%;" name="month1" id="month1">월
							<input type="text" style="width:10%;" name="day1" id="day1">일
							<input type="text" style="width:10%;" name="hour1" id="hour1">시
							<input type="text" style="width:10%;" name="minute1" id="minute1">분<br>&nbsp; &nbsp; &nbsp; - &nbsp; &nbsp; &nbsp;
							<input type="text" style="width:10%;" name="month2" id="month2">월
							<input type="text" style="width:10%;" name="day2" id="day2">일
							<input type="text" style="width:10%;" name="hour2" id="hour2">시
							<input type="text" style="width:10%;" name="minute2" id="minute2">분
					</td>
				</tr>
				<tr>
					<td style="width:80px">예약자</td>
					<td>
						<input type="text" style="width:90%;" id="userName" value="<%=name%>" readonly>
					</td>
				</tr>
			</tbody>
		</table>
		<br>
		<input type="submit" value="예약하기">
		</form>
</center>

</body>
</html>