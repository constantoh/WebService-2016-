<%@page import="com.mocomsys.sangsoo.vo.Daily"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<% System.out.println("index.jsp"); %>
	<head>
		<title>Welcome</title>
		<meta charset="UTF-8"/>
		<link href="resourse/css/Login.css" rel="stylesheet">
		<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
		<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
		<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js"></script>
	</head>
	
	 
	<body>
      <div id="login_container" class="window_center">
         <img src="resourse/css/logo.png" id="Logo">
      </div>
 <!-- 1 -->
      <div id="main" style="border: 2px solid; ">
      	전자결재
      </div>
<!-- 2 -->
      <div id="main" style="border: 2px solid; ">
      <script type="text/javascript">
		$(function(){
			$('#searchButton').click(function(){
				var table = document.getElementById("tNode");
				
				$.post('DailySearchByName.do', { name : $('#name').val()},
				function(data){
					createMyTable(table,data);
				});
			});
			function clearBody(table,data) {
				//$('#message2').html("delete");
				console.log(table.childNodes.length);
				if(table.childNodes.length==0){
					
				}else{
					while(table.childNodes[0].childNodes.length > 0)
						table.deleteRow(table.childNodes.length.length -1);
				}
			}
			 function createMyTable(table,data){
					clearBody(table,data);
					//$('#message1').html("create");

					var jsonData = JSON.parse(data);
					var length = jsonData.tr.length;
					var i=0;
					for(var i=0; i<jsonData.tr.length; i++){
						
						var h = jsonData.tr[i];

						var row = table.insertRow(i);
						row.align = "center";
						
						var cell1 = row.insertCell(0);
						cell1.width="20%";
						var cell2 = row.insertCell(1);
						
						cell1.innerHTML = h.date;
						cell2.innerHTML = h.note;
					}
				}
		});
		</script>
      	실적조회  
      		이름
      		<input type="text" id="name">
      		<input type="button" value="검색" id="searchButton">
      		<br>
      		<div id="message1"></div>
      		<div id="message2"></div>
      		<br>
      		<center>
	      		<table border="1" cellpadding="0" cellspacing="0" width="98%">
					<tr align="center">
						<td width="20%">날짜</td>
						<td>제목</td>
					</tr>
				</table>
				
				<table id="tNode" border="1" cellpadding="0" cellspacing="0" width="98%"></table>
      		</center>
      </div>
         
<!-- 3 -->
      <div id="main" style="border: 2px solid; ">
      	E-mail
      </div>
<!-- 4 -->
      <div id="main4" style="border: 2px solid; ">
      	NAVER 캘린더
      	<div id="naver_id_login"></div>
		<script type="text/javascript">
		var naver_id_login = new naver_id_login("COKNVHCQPO0R3Dba6p6D", "http://10.10.1.115:8080/WebServiceThird/index.jsp");
		function startCalendar(){
			var obj = document.createElement("input");
			var accessToken =  naver_id_login.getAccessToken();
			console.log(accessToken);
			if(accessToken == null){
				var state = naver_id_login.getUniqState();
				naver_id_login.setButton("green", 2,40);
				naver_id_login.setDomain("WebServiceThird/");
				naver_id_login.setState(state);
				naver_id_login.init_naver_id_login();	
			}else{
				obj.src = "resourse/logout.PNG";
				obj.style.height="40";
				obj.type="image";
				obj.id="logoutButton"
				main4.appendChild(obj);
				
				$.post('Index.do', { token : accessToken},
						function(data){
							alert(accessToken);
						});
			}
		}
		startCalendar();
		
		</script>   
      </div>
      <div id="footer" style="border-top: 2px solid; ">
         	MOCOMSYS 2016 SANGSOO
      </div>
</body>
</html>
