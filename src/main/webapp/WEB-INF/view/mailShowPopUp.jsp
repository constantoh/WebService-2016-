<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.mocomsys.sangsoo.vo.MyMailFlag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	System.out.println("mailShowPopUp.jsp !!!");
	MyMailFlag[] mails = (MyMailFlag[])session.getAttribute("mail");
	String mailNoS = (String)session.getAttribute("mailNo");
	int mailNo = Integer.parseInt(mailNoS);
	System.out.println("no : " + mailNo);
	String sendName = mails[mailNo].getName();
	String receivedDate = mails[mailNo].getReceivedDate();
	String title = mails[mailNo].getTitle();
	String attachName = mails[mailNo].getAttachName();
	String content = mails[mailNo].getContent();
	System.out.println("mail : " + content);
	System.out.println("attach Name : " + attachName);
	
%>
</head>
<body>
		<table class="table" style="width:80%" border="2" cellpadding="5" cellspacing="5">
			<tbody>
				<tr>
					<td style="width:80px">보낸 사람</td>
					<td>
						<input type="text" style="width:90%;" name="siteName" id="siteName" value="<%=sendName%>" readonly>
					</td>
				</tr>
				<tr>
					<td style="width:80px">제목</td>
					<td>
						<input type="text" style="width:90%;" name="title" id="title" value="<%=title %>" readonly>
					</td>
				</tr>
				<tr>
					<td style="width:80px">첨부 파일</td>
					<td>
					<%
						if(!attachName.equals("")){
					%>
							<a href="AttachDownload.do?fileName=<%=attachName%>&title=<%=title%>"><%=attachName %></a>
					<%
						}
					%>
					</td>
				</tr>
				<tr>
					<td style="width:80px">본문</td>
					<td>
					<%
						out.println(content);
					%>
					</td>
				</tr>
			</tbody>
		</table>
</body>
</html>