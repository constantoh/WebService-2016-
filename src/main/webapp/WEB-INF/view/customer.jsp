<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.mocomsys.sangsoo.vo.Customer"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>고객</title>
</head>
<%
	Customer[] customers = (Customer[]) session.getAttribute("customers");
	int many = (Integer)session.getAttribute("many");
%>
<body>
<body>

	<h5>#고객 정보 관리</h1>
		<center>
		<!-- 
		<div style="width:75%">
			<select style="width:100px;" id="search_select" name="search_select" onchange="customer_selectbox_print(1)">
				<option value="1">고객명</option>
			</select>
			<input id="search_str" type="text" value="">
			<input type="button" class="btn btn-default" id="btn_search" onclick="search(1)" value="검색">
			<input type="button" class="btn btn-default" onclick="search(0)" value="전체보기">

			<input type="button" class="btn btn-default" id="customer_insert_popup" value="신규 고객 등록">
		</div>
		
		<select style='width:100px;' id='search_select' name='search_select' onchange='customer_selectbox_print(1)'>"
									+"<option value='1'>고객명</option></select>"
								+"<input id='search_str' type='text' value=''>"
								+"<input type='button' value='검색'>"
								+"<input type='button' value='전체보기'>"
								+"<input type='button' value='신규 고객 등록'>"
								+"</div>
		 -->
	<table border="2" cellpadding="5" cellspacing="5"  style="width:75%">
		<thead>
		<tr>
			<th colspan="10" class="tb_title">
				<center>고객 정보</center>
			</th>
		</tr>
		</thead>
		<tbody>
	
<%
		for(int i=0; i<many; i++){
			String t= "";

			if(i%6==0)
				t+="<tr>";
				
			t+="<td style='width:15px'>"
				+ (i+1) + "</td><td style='cursor:pointer;'"
				+ customers[i].getCustomer_UID() + "')';>"
				+"<a href='CustomInfo.do?cus_uid="
				+ customers[i].getCustomer_UID() + "'>" 
				+ customers[i].getCustomer_NAME() + "</a></td>";
				
			if(i%6==5)
				t+="</tr>";
			out.print(t);
		}
	%>
	</tbody>
	</table>
	</center>
</body>
</html>