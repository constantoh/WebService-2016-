<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page import="com.mocomsys.sangsoo.vo.Customer"%>    
<%@page import="com.mocomsys.sangsoo.vo.Site" %>

<%
	Customer[] customers = (Customer[]) session.getAttribute("customers");
	int uid = (Integer)session.getAttribute("UID");
	System.out.println("uid : " + uid);

	int k=0;
	while(true){
		if(customers[k].getCustomer_UID()==uid)
			break;
		k++;
	}
	uid = k;
	System.out.println("k : " + k + " uid 2: " + uid);

	Site[] sites = (Site[])session.getAttribute("siteByUID");
	int manySiteByUID = (Integer)session.getAttribute("manySiteByUID");
	/*
	System.out.println("jsp ) many is " + manySiteByUID);
	for(int i=0; i<manySiteByUID; i++){
		System.out.println(sites[i].getSite_UID() + ", " + sites[i].getCustomer_UID()  + ", " 
				+ sites[i].getSite_Name() + ", " + sites[i].getSite_state_UID() + ", " + sites[i].getCharge_Dept()
				+ ", " + sites[i].getNote());
	}
	*/
	
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>고객조회-<%=customers[uid].getCustomer_NAME() %></title>
</head>
<body>

	<h5># 고객 정보 관리 조회</h5>
			
	<center>
	<table style="width:75%;" border="2" cellpadding="5" cellspacing="5">
		<tbody>
			<tr>
				<td style="width:80px;">고객 상호</td>
				<td style="width:50%;">
				<%
					out.println(customers[uid].getCustomer_NAME());
				%></td>
				<td style="width:80px;">업종 분야</td>
				<td>
				<%
					out.println(customers[uid].getBusiness_CATEGORY());
				%>
				</td>
			</tr>
			<tr>
				<td style="width:80px;">영업 담당자</td>
				<td>
				<%
					out.println(customers[uid].getSales_Person());
				%>
				</td>
				<td style="width:80px;">등급</td>
				<td>
				<%
					if(customers[uid].getGrade()!=null)
						out.println(customers[uid].getGrade());
				%>
				</td> 
			</tr>
		</tbody>
	</table>
	
	<br><br><br>
	
		<center>
		<form action="InsertSite.do" method="post">	
		<table class="table table-bordered" style="width:75%" border="2" cellpadding="5" cellspacing="5">
			<tbody>
				<tr>
					<td style="width:80px">사이트명</td>
					<td>
						<input type="text" style="width:90%;" id="siteName" name="siteName">
					</td>
					<td style="width:100px">사이트 단계</td>
						<td style="width:200px">
							<select style="width:90%;" id="siteState"  name="siteState">
								<option value="">------------------</option>
								<option value="1">이행 예정</option>
								<option value="2">이행 중</option>
								<option value="3">인수인계 진행</option>
								<option value="4">무상-유지보수</option>
								<option value="5">유상-유지보수</option>
								<option value="6">만료</option>
								<option value="7">종료</option>
								<option value="8">미계약</option>
								<option value="9">PS제안</option>
								<option value="10">PS실패</option>
							</select>
						</td>
				</tr>
				<tr>
					<td style="height:100px;" class="tb_title">비 고</td>
					<td colspan="3">
						<textarea style="height:100px; width:96%;" id="siteNote" name="siteNote"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
		<br>
		<input type="submit" value="사이트 등록">
		</form>
		</center>
		
	<br><br><br>
	
	
	- 사이트 등록 리스트
	
	<table class="table table-bordered" style="width:75%;" border="2" cellpadding="5" cellspacing="5">
		<thead>
			<tr>
  				<th style="width:20px;" class="tb_title">#</th>
				<th class="tb_title" style="width:120px;"><center>사이트명</center></th>
				<th style="width:85px;" class="tb_title"><center>사이트 단계</center></th>
				<th class="tb_title"><center>비고</center></th>
				
			</tr>
		</thead>
		<tbody>
		<%
			String t="";
			for(int i=0; i<manySiteByUID; i++){
				t="<tr><td>" + (i+1) + "</td><td>" + sites[i].getSite_Name() + "</td><td>" 
						+ sites[i].getSite_state_UID() + "</td><td>"
						+ sites[i].getNote() + "</td></tr>";
				out.println(t);
			}
		%>
		</tbody>
	</table>
	<br><br><br><br>
	</center>
</body>
</html>