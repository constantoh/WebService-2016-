<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	System.out.println("**************** createSchedule.jsp ***********************");
	String token = (String)session.getAttribute("token");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>일정 생성</title>
	<script>
		function closeSelf(f){
			f.submit();
			//window.close();
		}
		function RadioCheck(repeat_info){
			console.log("check ");
			var text = document.getElementById("frequncy_info1");
			var text2 = document.getElementById("frequncy_info2");
			var text3 = document.getElementById("frequncy_info3");
			var text4 = document.getElementById("frequncy_info4");
			var text5 = document.getElementById("untilYear");
			var text6 = document.getElementById("untilMonth");
			var text7 = document.getElementById("untilDay");
			var text8 = document.getElementById("day_info1");
			var text9 = document.getElementById("day_info2");
			var text10 = document.getElementById("day_info3");
			var text11 = document.getElementById("day_info4");
			var text12 = document.getElementById("day_info5");
			var text13 = document.getElementById("day_info6");
			var text14 = document.getElementById("day_info7");


			switch(repeat_info){ 
		    	case 'Y':
		    		console.log("Y")
		        	text.disabled  = false; 
		        	text2.disabled  = false; 
		        	text3.disabled  = false; 
		        	text4.disabled  = false; 
		        	text5.disabled  = false; 
		        	text6.disabled  = false; 
		        	text7.disabled  = false; 
		        	text8.disabled  = false; 
		        	text9.disabled  = false; 
		        	text10.disabled  = false; 
		        	text11.disabled  = false; 
		        	text12.disabled  = false; 
		        	text13.disabled  = false; 
		        	text14.disabled  = false; 
		        	
		      	break; 
		      	case 'N': 
		      		console.log("N");
		        	text.disabled  = true; 
		        	text2.disabled  = true; 
		        	text3.disabled  = true; 
		        	text4.disabled  = true; 
		        	text5.disabled  = true; 
		        	text6.disabled  = true; 
		        	text7.disabled  = true; 
		        	text8.disabled  = true; 
		        	text9.disabled  = true; 
		        	text10.disabled  = true; 
		        	text11.disabled  = true; 
		        	text12.disabled  = true; 
		        	text13.disabled  = true; 
		        	text14.disabled  = true; 

		      	break; 
		    } 
		};
	</script>
		<form name="scheduleForm" onsubmit="return closeSelf(this);" action="CreateSchedule2.do" method="post">	
		<table class="table table-bordered" style="width:75%" border="2" cellpadding="5" cellspacing="5">
			<tbody>
				<tr>
					<td style="width:80px">제목</td>
					<td colspan="2">
						<input type="text" style="width:100%;" name="title" id="title">
					</td>
				</tr>
				<tr>
					<td style="width:80px">장소</td>
					<td colspan="2">
						<input type="text" style="width:100%;" name="location" id="location">
					</td>
				</tr>
				<tr>
					<td style="width:80px">설명</td>
					<td colspan="2">
						<input type="text" style="width:100%;" name="description" id="description">
					</td>
				</tr>
				<tr>
					<td style="width:80px">시작 시간</td>
					<td colspan="2">
							<input type="text" style="width:10%;" name="year1" id="year1">년
							<input type="text" style="width:10%;" name="month1" id="month1">월
							<input type="text" style="width:10%;" name="day1" id="day1">일
							<input type="text" style="width:10%;" name="hour1" id="hour1">시
							<input type="text" style="width:10%;" name="minute1" id="minute1">분
					</td>
				</tr>
				<tr>
					<td style="width:80px">종료 시간</td>
					<td colspan="2">
							<input type="text" style="width:10%;" name="year2" id="year2">년
							<input type="text" style="width:10%;" name="month2" id="month2">월
							<input type="text" style="width:10%;" name="day2" id="day2">일
							<input type="text" style="width:10%;" name="hour2" id="hour2">시
							<input type="text" style="width:10%;" name="minute2" id="minute2">분
					</td>
				</tr>
				<tr>
					<td style="width:80px">반복</td>
					<td colspan="2">					
						<input type="radio" name="repeat_info" value="YesRepeat" onClick="RadioCheck('Y')">반복 
						<input type="radio" name="repeat_info" value="NoRepeat" checked="checked" onClick="RadioCheck('N')">반복 하지 않음
					</td>
				</tr>
				<tr>
					<td rowspan="2" style="width:80px">반복 정보</td>
					<td style="width:40px">빈도 </td>
					<td id="repeat1">
						<input type="radio" name="frequency_info" id="frequncy_info1" value="1" disabled="true">1주 
						<input type="radio" name="frequency_info" id="frequncy_info2" value="2" disabled="true">2주
						<input type="radio" name="frequency_info" id="frequncy_info3" value="MONTH" disabled="true">1달
						<input type="radio" name="frequency_info" id="frequncy_info4" value="YEAR" disabled="true">1년
						<br>
						<input type="checkbox" name="day_info" id="day_info1" value="MO" disabled="true">월
						<input type="checkbox" name="day_info" id="day_info2" value="TU" disabled="true">화
						<input type="checkbox" name="day_info" id="day_info3" value="WE" disabled="true">수
						<input type="checkbox" name="day_info" id="day_info4" value="TH" disabled="true">목
						<input type="checkbox" name="day_info" id="day_info5" value="FR" disabled="true">금
						<input type="checkbox" name="day_info" id="day_info6" value="SA" disabled="true">토
						<input type="checkbox" name="day_info" id="day_info7" value="SU" disabled="true">일
					</td>
					
				</tr>
				<tr>
					<td style="width:40px">범위</td>
					<td id="repeat2">
							<input type="text" style="width:10%;" name="untilYear" id="untilYear" disabled="true">년
							<input type="text" style="width:10%;" name="untilMonth" id="untilMonth" disabled="true">월
							<input type="text" style="width:10%;" name="untilDay" id="untilDay" disabled="true">일
					</td>
				</tr> 
			</tbody>
		</table>
		<br>
		<div>
			<input type="submit" value="스케쥴 등록하기">
		</div>
		</form>
</head>
<body>
</body>
</html>