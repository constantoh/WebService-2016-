<%@page import="com.mocomsys.sangsoo.vo.Daily"%>
<%@page import="com.mocomsys.sangsoo.vo.User"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<% System.out.println("main.jsp");
		HttpSession mySession = request.getSession(true);
		User user = (User)mySession.getAttribute("user");
		String f1 = user.getFavorite1();
		String f2 = user.getFavorite2();
		String f3 = user.getFavorite3();
		System.out.println("f1 : " + f1);
		System.out.println("f2 : " + f2);
		System.out.println("f3 : " + f3);
		
		%>
	<head>
		<title>Welcome</title>
		<!-- <meta charset="UTF-8"/>  -->
		<link href="resourse/css/Login.css" rel="stylesheet">
		<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
		<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
		<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js"></script>
		<!-- bootstrap -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
		<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	</head>
	 
	<body>
	
	<script type="text/javascript">
	function modifyFavorite(){
		console.log("modify");
		window.open("ModifyFavorite.do", "네이버", "top=100, left=100, width=400, height=400");
	};
	//daily
		function daily(){
				var s = '<div id="daily">최근 3일 실적조회  <input type="button" class="btn btn-warning" id="searchButton" onclick="searchButtonClicked();" value="조회"><br><br>'
				+ '<center><table class="table" border="1" cellpadding="0" cellspacing="0" width="98%"><tr align="center"><td width="20%">날짜</td>'
				+ '<td width="20%">이름</td><td>제목</td></tr></table><table class="table" id="tNode" border="1" cellpadding="0" cellspacing="0" width="98%"></table>'
				+ '</center></div>';
				var htmlDaily = $(s);
				return htmlDaily;
		};
		function searchButtonClicked(){
			console.log("hello Daily");
			var table = document.getElementById("tNode");
				
			$.post('DailySearchById.do',
			function(data){
				createMyTable(table,data);
			});
			function clearBody(table,data) {
				console.log(table.childNodes.length);
				if(table.childNodes.length==0){	
				}else{
					while(table.childNodes[0].childNodes.length > 0)
						table.deleteRow(table.childNodes.length.length -1);
				}
			}
			function getAgoDate(yyyy, mm, dd)
			{
				var today = new Date();
				var year = today.getFullYear();
				var month = today.getMonth();
				var day = today.getDate();
				  
				var resultDate = new Date(yyyy+year, month+mm, day+dd);
					year = resultDate.getFullYear();
				    month = resultDate.getMonth() + 1;
				    day = resultDate.getDate();
	
				    if (month < 10)
				    	month = "0" + month;
				    if (day < 10)
				    	day = "0" + day;
		       return year + "" + month + "" + day;
			}
			function createMyTable(table,data){
				clearBody(table,data);
				var jsonData = JSON.parse(data);
				var length = jsonData.tr.length;
				var i=0;
				for(var i=0; i<jsonData.tr.length; i++){
					var h = jsonData.tr[i];
		
					var row = table.insertRow(i);
					var today = getAgoDate(0,0,0);
					var yesterday = getAgoDate(0,0,-1);
					var yesterday2 = getAgoDate(0,0,-2);
								
					console.log(today + yesterday + yesterday2);
					if((h.date == today) || (h.date == yesterday) || (h.date == yesterday2)){
						row.align = "center";
						var cell1 = row.insertCell(0);
						cell1.width="20%";
									
						var cell2 = row.insertCell(1);
						cell2.width="20%";
									
						var cell3 = row.insertCell(2);
									
						cell1.innerHTML = h.date;
						cell2.innerHTML = h.name;
						cell3.innerHTML = h.note;
					}
				}
			}
		};
		//E-mail
		function mail(){
			console.log("hello Mail");

			var s = '<div id="mail">E-mail<br><input type="button" class="btn btn-warning" id="receiveMailBox" onclick="receiveMailBoxButtonClicked();" value="받은 메일함">'
				+ '<a href="javascript:transferMail()"><input type="submit" class="btn btn-warning" style="margin-left:10px" id="transferMail" name="transferMail" value="메일 보내기"></a>'
				+ '<div id="mailBox"><center><table class="table" id="menuMail" border="1" cellpadding="0" cellspacing="0" width="98%" '
				+ 'style="font-size: 14px"></table></center></div></div>';
				var htmlDaily = $(s);
				return htmlDaily;
		};
		function transferMail(){
			console.log("transferMail clicked popup go");
			window.open("goTransferMail.do", "startpop", "top=100, left=100, width=600, height=500, scrollbars=no, resizable=no ,status=no ,toolbar=no");
		};
      	function popUpMail(mailNo){
	 		 window.open("MailShowPopUp.do?mailNo="+ mailNo +"", "startpop", "top=100, left=100, width=600, height=500, scrollbars=no, resizable=no ,status=no ,toolbar=no");
	 	};
		function receiveMailBoxButtonClicked(){
					
			var menuTable = document.getElementById("menuMail");
				$.post('ReceiveMail.do',
					function(data){
						createMyTable(menuTable,data);
					});
				function clearBody(table,data) {
					console.log("clearTable");
					if(table.childNodes.length==0){
						
					}else{
						while(table.childNodes[0].childNodes.length > 0)
							table.deleteRow(table.childNodes.length.length -1);
					}
				}
				function createMyTable(table,data){
					clearBody(table,data);
					console.log("createTable");

					var jsonData = JSON.parse(data);
					var length = jsonData.mail.length;
					var i=0;
					for(var i=-1; i<(length); i++){
						
						var row = table.insertRow();
						row.align = "center";
						var cell0 = row.insertCell(0);
						cell0.width="10%";
						
						var cell1 = row.insertCell(1);
						cell1.width="10%";
						
						var cell2 = row.insertCell(2);
						cell2.width="20%";

						var cell3 = row.insertCell(3);
						cell3.width="30%";

						var cell4 = row.insertCell(4);
						cell4.width="20%";

						var cell5 = row.insertCell(5);
						cell5.width="10%";
						
						if(i==-1){
							cell0.innerHTML = "순번";
							cell1.innerHTML = "읽음";
							cell2.innerHTML = "이름";
							cell3.innerHTML = "제목";
							cell4.innerHTML = "받은시간";
							cell5.innerHTML = "첨부";
						}else{
							var h = jsonData.mail[i];
							cell0.innerHTML = (h.index+1);
							cell1.innerHTML = "<font color='#FF0000'>" + h.newMail + "</font>";
							cell2.innerHTML = h.name;
							cell3.innerHTML = '<a href="javascript:popUpMail('+ ((length-1) - h.index) +')">' + h.title + "</a>";
							cell4.innerHTML = h.date;
							cell5.innerHTML = "<font color='#0000FF'>" + h.attach + "</font>";
						}
					}
				}
			};
		//addr
		function addr(){
			console.log("hello addr");

			var s =  '<span><label style="font-size: 24px; float:left">이름 : </label><input type="text" style="width:200px; '
				+ 'float:left; margin-left:10px" class="form-control" id="inputName" placeholder="name" >'
				+ '<input type="button" style="margin-left:10px" class="btn btn-warning" onclick="searchNameButtonClicked()" '
				+ 'id="searchAddr" value="조회"><center>'
				+ '<table class="table" id="addrBox" border="1" cellpadding="0" cellspacing="0" width="98%" style="font-size: 14px">'
				+ '</table></center></span>';
			var htmlDaily = $(s);
			return htmlDaily;
		};
		function searchNameButtonClicked(){
			console.log("addr clicked");
			var menuTable = document.getElementById("addrBox");
			var name = document.getElementById("inputName");
			$.post('SearchAddrByName.do',
					{ name : $('#inputName').val()},
				function(data){
					createMyTable(menuTable,data);
				});
			function clearBody(table,data) {
				console.log("clearTable");
				if(table.childNodes.length==0){
					
				}else{
					while(table.childNodes[0].childNodes.length > 0)
						table.deleteRow(table.childNodes.length.length -1);
				}
			}
			function createMyTable(table,data){
				clearBody(table,data);
				console.log("createTable");
				

				var jsonData = JSON.parse(data);
				if(jsonData.name=="none"){
					alert("해당 사원은 없습니다");
					return false;
				}
			
				var row = table.insertRow();
				row.align = "center";
				
				var cell0 = row.insertCell(0);
				cell0.width="10%";
					
				var cell1 = row.insertCell(1);
				cell1.width="15%";
					
				var cell2 = row.insertCell(2);
				cell2.width="10%";

				var cell3 = row.insertCell(3);
				cell3.width="30%";

				var cell4 = row.insertCell(4);
				cell4.width="25%";

				cell0.innerHTML = "이름";
				cell1.innerHTML = "부서";
				cell2.innerHTML = "직위";
				cell3.innerHTML = "메일";
				cell4.innerHTML = "휴대전화";
				
				
				var row2 = table.insertRow();
				row2.align = "center";
				
				var cell20 = row2.insertCell(0);
				cell0.width="10%";
					
				var cell21 = row2.insertCell(1);
				cell1.width="15%";
					
				var cell22 = row2.insertCell(2);
				cell2.width="10%";

				var cell23 = row2.insertCell(3);
				cell3.width="30%";

				var cell24 = row2.insertCell(4);
				cell4.width="25%";
				
				cell20.innerHTML = jsonData.name;
				cell21.innerHTML = jsonData.dept;
				cell22.innerHTML = jsonData.position;
				cell23.innerHTML = jsonData.email;
				cell24.innerHTML = jsonData.phoneNo;
				
				}
			};
			
			//room
			function room(){
				console.log("hello room");

				var s =  '<div id="daily">현재 시간의 회의실 예약 현황  <input type="button" class="btn btn-warning" id="searchRoomButton1" onclick="searchRoom1ButtonClicked();" value="조회1"> <input type="button" class="btn btn-warning" id="searchRoomButton2" onclick="searchRoom2ButtonClicked();" value="조회2"><br><br>'
					+ '<center><table class="table" border="1" cellpadding="0" cellspacing="0" width="98%"><tr align="center"><td width="50%">회의실</td>'
					+ '<td width="50%">예약 현황</td></tr></table><table class="table" id="roomBox" border="1" cellpadding="0" cellspacing="0" width="98%"></table>'
					+ '</center></div>';
				var htmlDaily = $(s);
				return htmlDaily;
			};
			function searchRoom1ButtonClicked(){
				console.log("room1 clicked");
				var menuTable = document.getElementById("roomBox");
				var name = document.getElementById("inputName");
				$.post('SearchAvailableRoom.do',
						{ startTime : "160708110000"},
					function(data){
						createMyTable(menuTable,data);
					});
				function clearBody(table,data) {
					console.log("clearTable");
					if(table.childNodes.length==0){
						
					}else{
						while(table.childNodes[0].childNodes.length > 0)
							table.deleteRow(table.childNodes.length.length -1);
					}
				}
				function createMyTable(table,data){
					clearBody(table,data);
					console.log("createTable");

					var jsonData = JSON.parse(data);
					var length = jsonData.room.length;
					console.log("length : " + length);
					var i=0;
					for(var i=0; i<5; i++){
						
						var row = table.insertRow();
						row.align = "center";
						var cell0 = row.insertCell(0);
						cell0.width="50%";
						
						var cell1 = row.insertCell(1);
						cell1.width="50%";
						cell1.innerHTML="<font color='blue'>사용 가능</font>";
						
						
						if(i==0){
							cell0.innerHTML = "소회의실1";
							for(var j=0; j<length; j++){
								var h = jsonData.room[j];
								var k = h.roomName;
								console.log("room : " + k)
								if(k=="소회의실1"){
									cell1.innerHTML = "사용중";
								}
							}
						}else if(i==1){
							cell0.innerHTML = "소회의실2";
							for(var j=0; j<length; j++){
								var h = jsonData.room[j];
								var k = h.roomName;
								console.log("room : " + k)
								if(k=="소회의실2"){
									cell1.innerHTML = "사용중";

								}
							}
						}else if(i==2){
							cell0.innerHTML = "가변회의실1";
							for(var j=0; j<length; j++){
								var h = jsonData.room[j];
								var k = h.roomName;
								console.log("room : " + k)
								if(k=="가변회의실1"){
									cell1.innerHTML = "사용중";
								}
							}
						}else if(i==3){
							cell0.innerHTML = "가변회의실2";
							for(var j=0; j<length; j++){
								var h = jsonData.room[j];
								var k = h.roomName;
								console.log("room : " + k)
								if(k=="가변회의실2"){
									cell1.innerHTML = "사용중";

								}
							}
						}else if(i==4){
							cell0.innerHTML = "가변회의실";
							for(var j=0; j<length; j++){
								var h = jsonData.room[j];
								var k = h.roomName;
								console.log("room : " + k)
								if(k=="가변회의실"){
									cell1.innerHTML = "사용중";

								}
							}
						}else{
							
						}
						
					}
				}
				}
				function searchRoom2ButtonClicked(){
					console.log("room2 clicked");
					var menuTable = document.getElementById("roomBox");
					var name = document.getElementById("inputName");
					$.post('SearchAvailableRoom.do',
							{ startTime : "160708133000"},
						function(data){
							createMyTable(menuTable,data);
						});
					function clearBody(table,data) {
						console.log("clearTable");
						if(table.childNodes.length==0){
							
						}else{
							while(table.childNodes[0].childNodes.length > 0)
								table.deleteRow(table.childNodes.length.length -1);
						}
					}
					function createMyTable(table,data){
						clearBody(table,data);
						console.log("createTable");

						var jsonData = JSON.parse(data);
						var length = jsonData.room.length;
						console.log("length : " + length);
						var i=0;
						for(var i=0; i<5; i++){
							
							var row = table.insertRow();
							row.align = "center";
							var cell0 = row.insertCell(0);
							cell0.width="50%";
							
							var cell1 = row.insertCell(1);
							cell1.width="50%";
							cell1.innerHTML="<font color='blue'>사용 가능</font>";
							
							if(i==0){
								cell0.innerHTML = "소회의실1";
								for(var j=0; j<length; j++){
									var h = jsonData.room[j];
									var k = h.roomName;
									console.log("room : " + k)
									if(k=="소회의실1"){
										cell1.innerHTML = "사용중";
									}
								}
							}else if(i==1){
								cell0.innerHTML = "소회의실2";
								for(var j=0; j<length; j++){
									var h = jsonData.room[j];
									var k = h.roomName;
									console.log("room : " + k)
									if(k=="소회의실2"){
										cell1.innerHTML = "사용중";
									}
								}
							}else if(i==2){
								cell0.innerHTML = "가변회의실1";
								for(var j=0; j<length; j++){
									var h = jsonData.room[j];
									var k = h.roomName;
									console.log("room : " + k)
									if(k=="가변회의실1"){
										cell1.innerHTML = "사용중";
									}
								}
							}else if(i==3){
								cell0.innerHTML = "가변회의실2";
								for(var j=0; j<length; j++){
									var h = jsonData.room[j];
									var k = h.roomName;
									console.log("room : " + k)
									if(k=="가변회의실2"){
										cell1.innerHTML = "사용중";
									}
								}
							}else if(i==4){
								cell0.innerHTML = "가변회의실";
								for(var j=0; j<length; j++){
									var h = jsonData.room[j];
									var k = h.roomName;
									console.log("room : " + k)
									if(k=="가변회의실"){
										cell1.innerHTML = "사용중";
									}
								}
							}else{
								
							}
						}
					}
				};
				//customer
				function cust(){
					console.log("hello cust");

					var s =  '<span><label style="font-size: 24px; float:left">고객 조회 : </label><input type="text" style="width:200px; '
						+ 'float:left; margin-left:10px" class="form-control" id="inputCust" placeholder="customer" >'
						+ '<input type="button" style="margin-left:10px" class="btn btn-warning" onclick="searchCustButtonClicked()" '
						+ 'id="searchAddr" value="조회"><center>'
						+ '<table class="table" id="custBox" border="1" cellpadding="0" cellspacing="0" width="98%" style="font-size: 14px">'
						+ '</table></center></span>';
					var htmlDaily = $(s);
					return htmlDaily;
				};
				function searchCustButtonClicked(){
					console.log("cust clicked");
					var menuTable = document.getElementById("custBox");
					var name = document.getElementById("inputCust");
					$.post('SearchCustomerByName.do',
							{ name : $('#inputCust').val()},
						function(data){
							createMyTable(menuTable,data);
						});
					function clearBody(table,data) {
						console.log("clearTable");
						if(table.childNodes.length==0){
							
						}else{
							while(table.childNodes[0].childNodes.length > 0)
								table.deleteRow(table.childNodes.length.length -1);
						}
					}
					function createMyTable(table,data){
						clearBody(table,data);
						console.log("createTable");
						
						var jsonData = JSON.parse(data);
						if(jsonData.name=="none"){
							alert("해당 고객은 없습니다");
							return false;
						}
					
						var row = table.insertRow();
						row.align = "center";
						
						var cell0 = row.insertCell(0);
						cell0.width="10%";
							
						var cell1 = row.insertCell(1);
						cell1.width="15%";
						
						cell0.innerHTML = "이름";
						cell1.innerHTML = "정보 조회";
						
						var row2 = table.insertRow();
						row2.align = "center";
						
						var cell20 = row2.insertCell(0);
						cell0.width="10%";
							
						var cell21 = row2.insertCell(1);
						cell1.width="15%";
						
						cell20.innerHTML = jsonData.name;
//						'<a href="javascript:transferMail()"><input type="submit" class="btn btn-warning" style="margin-left:10px" id="transferMail" name="transferMail" value="메일 보내기"></a>'
						 //<input type="button" class="btn btn-warning" id="searchRoomButton1" onclick="searchRoom1ButtonClicked();" value="조회1">
						 cell21.innerHTML = jsonData.link + "<input type='button' class='btn btn-warning' value='상세 조회'></a>";
						 //;<input type='button'> ;
						
						}
					};
					
		//select button
		function dropButton(option, id){
			if(id=="select1"){
				$('#main1').empty();
				if(option=="edoc"){
					 console.log("select1's option : edoc");
					 
				}else if(option=="daily"){
					console.log("select1's option : daily");
					 $('#main1').append(daily());
					 
				}else if(option=="room"){
					console.log("select1's option : room");
					 $('#main1').append(room());

				}else if(option=="mail"){
					console.log("select1's option : mail");
					 $('#main1').append(mail());
					 
				}else if(option=="addr"){
					console.log("select1's option : addr");
					 $('#main1').append(addr());
					 
				}else if(option=="cust"){
					console.log("select1's option : cust");
					 $('#main1').append(cust());

					
				}else{
					
				}
			}else if(id=="select2"){
				$('#main2').empty();
				if(option=="edoc"){
					console.log("select2's option : edoc");
					
				}else if(option=="daily"){
					console.log("select2's option : daily");
					 $('#main2').append(daily());
					 
				}else if(option=="room"){
					console.log("select2's option : room");
					 $('#main2').append(room());

				}else if(option=="mail"){
					console.log("select2's option : mail");
					 $('#main2').append(mail());
					 
				}else if(option=="addr"){
					console.log("select2's option : addr");
					 $('#main2').append(addr());
					 
				}else if(option=="cust"){
					console.log("select2's option : cust");
					 $('#main2').append(cust());

				}else{
					
				}				
			}else if(id=="select3"){
				$('#main3').empty();

				if(option=="edoc"){
					console.log("select3's option : edoc");
					
				}else if(option=="daily"){
					console.log("select3's option : daily");
					 $('#main3').append(daily());
					 
				}else if(option=="room"){
					console.log("select3's option : room");
					 $('#main3').append(room());

				}else if(option=="mail"){
					console.log("select3's option : mail");
					 $('#main3').append(mail());
					 
				}else if(option=="addr"){
					console.log("select3's option : addr");
					 $('#main3').append(addr());
					 
				}else if(option=="cust"){
					console.log("select3's option : cust");
					 $('#main3').append(cust());

				}else{
					
				}				
			}else if(id=="select4"){
				$('#main4').empty();
				if(option=="edoc"){
					console.log("select4's option : edoc");
					
				}else if(option=="daily"){
					console.log("select4's option : daily");
					 $('#main4').append(daily());
					 
				}else if(option=="room"){
					console.log("select4's option : room");
					 $('#main4').append(room());

				}else if(option=="mail"){
					console.log("select4's option : mail");
					 $('#main4').append(mail());
					 
				}else if(option=="addr"){
					console.log("select4's option : addr");
					 $('#main4').append(addr());
					 
				}else if(option=="cust"){
					console.log("select4's option : cust");
					 $('#main4').append(cust());

					
				}else{
					
				}				
			}else{
				
			}
		}
		
		function initMain(){
			console.log("initMain");
			for(var i=0; i<3; i++){
				var m;
				if(i==0){
					m =  "<%= f1%>";
					console.log("hello " + <%=f1%>);
					if(m=="daily"){
						$('#main1').append(daily());
					}else if(m=="room"){
						$('#main1').append(room());
					}else if(m=="mail"){
						$('#main1').append(mail());
					}else if(m=="addr"){
						$('#main1').append(addr());
					}else if(m=="cust"){
						$('#main1').append(cust());
					}
				}else if(i==1){
					m = "<%= f2%>";
					if(m=="daily"){
						$('#main2').append(daily());
					}else if(m=="room"){
						$('#main2').append(room());
					}else if(m=="mail"){
						$('#main2').append(mail());
					}else if(m=="addr"){
						$('#main2').append(addr());
					}else if(m=="cust"){
						$('#main2').append(cust());
					}
				}else if(i==2){
					m = "<%= f3%>";
					if(m=="daily"){
						$('#main3').append(daily());
					}else if(m=="room"){
						$('#main3').append(room());
					}else if(m=="mail"){
						$('#main3').append(mail());
					}else if(m=="addr"){
						$('#main3').append(addr());
					}else if(m=="cust"){
						$('#main3').append(cust());
					}
				}else{
					
				}
			}
		};
		
	</script>
	
      <div id="login_container" class="window_center">
         <img src="resourse/css/logo.png" id="Logo">
      </div>
      
      <div id="menus">
      
      	<form action=MemberCheck.do method="post" style="float:left">
			<input id="button" class="btn btn-info" type="submit" value="main">
		</form>
     
		<form action=GoWTS.do method="post" style="float:left">
			<input id="button" class="btn btn-info" type="submit" value="WTS">
		</form>
		
		<form action=GoGROUPWARE.do method="post" style="float:left">
			<input id="button" class="btn btn-info" type="submit" value="GROUPWARE">
		</form>
		<form>
				<input id="button" class="btn btn-info" type="submit" onclick="modifyFavorite()"value="FAVORITE">
		</form>
		
		
	 </div>
	<div id="selecbox1" >
		 <select class="form-control inputstl" style="width:180px;height:40px; float:left" id="select1" 
			onchange="dropButton(this.options[this.selectedIndex].value, id)">
			<option value="edoc">-------</option>
			<option value="daily">실적 조회</option>
			<option value="room">회의실 사용 조회</option>
			<option value="mail">E-Mail</option>
			<option value="addr">주소록</option>
			<option value="cust">고객 조회</option>
		</select>
		<select class="form-control inputstl" style="width:180px;height:40px; margin-left:50%" id="select2" 
			onchange="dropButton(this.options[this.selectedIndex].value, id)">
			<option value="edoc">-------</option>
			<option value="daily">실적 조회</option>
			<option value="room">회의실 사용 조회</option>
			<option value="mail">E-Mail</option>
			<option value="addr">주소록</option>
			<option value="cust">고객 조회</option>
		</select>
	</div>
	
	
	
<!-- 1 -->
    <div id="main1" style="border: 2px solid; ">
    
    </div>
<!-- -------------------------------------------------------------------------------------------------------------------------- -->
    
<!-- 2 -->
      <div id="main2" style="border: 2px solid; ">
      </div>
<!-- -------------------------------------------------------------------------------------------------------------------------- -->

      <div id="selecbox2" >
		 <select class="form-control inputstl" style="width:180px;height:40px; float:left" id="select3" 
			onchange="dropButton(this.options[this.selectedIndex].value, id)">
			<option value="edoc">-------</option>
			<option value="daily">실적 조회</option>
			<option value="room">회의실 사용 조회</option>
			<option value="mail">E-Mail</option>
			<option value="addr">주소록</option>
			<option value="cust">고객 조회</option>
		</select>
		<div id="naver_id_login" style="width:150px;height:40px; margin-left:50%"></div>
		<div id="naver_id_logout"></div>
	 </div>   
<!-- -------------------------------------------------------------------------------------------------------------------------- -->

<!-- 3 -->
      <div id="main3" style="border: 2px solid; clear:both">
      </div>
<!-- 4 -->
      <div id="main4" style="border: 2px solid; ">
      	
		<script type="text/javascript">
		var naver_id_login = new naver_id_login("COKNVHCQPO0R3Dba6p6D", "http://10.10.1.115:8080/WebServiceThird/MemberCheck.do");
		

		var createButton = document.createElement("button");
		var createButtonText = document.createTextNode("일정 생성");
		
		function startCalendar(){
			var obj = document.createElement("input");
			var accessToken	= naver_id_login.getAccessToken();
			console.log("accessToken : " + accessToken);
			console.log(accessToken);
			if(accessToken == null){
				console.log("not naver login");
				var state = naver_id_login.getUniqState();
				naver_id_login.setButton("green", 2,40);
				naver_id_login.setDomain("WebServiceThird/");
				naver_id_login.setState(state);
				naver_id_login.init_naver_id_login();	
			}else{
				console.log("naver login");
				obj.src = "resourse/logout.PNG";
				obj.style.marginLeft="33%";
				obj.style.height="40";
				obj.type="image";
				obj.id="logoutButton"
				obj.style.clear="both";
				//main4.appendChild(obj);
				document.getElementById('naver_id_logout').appendChild(obj);
				//naver_id_logout.append(obj);
				
				createButton.id="createButton";
				createButton.className= "btn btn-warning";
				createButton.style.clear="both";
				createButton.appendChild(createButtonText);
				
				main4.appendChild(createButton);


				//var goButton = document.createElement("button");
				//var goButtonText = document.createTextNode("일정 조회");
				

				
				//goButton.id="goButton";
				//goButton.className= "btn btn-warning";
				//goButton.style.marginLeft="10px";
				//goButton.appendChild(goButtonText);
				//main4.appendChild(goButton);
				
				var iframe = document.createElement("iframe");
				iframe.src='http://naver.me/xTxUlHlJ';
				iframe.width='480';
				iframe.height='420';
				iframe.frameborder='0'
      			main4.appendChild(iframe);
				
				//main4.appendChilde("<iframe src='http://naver.me/xTxUlHlJ' width='600' height='500' frameborder='0'></iframe>");
			}
			$(function(){
				$('#goButton').click(
						function(data){
							window.open("http://naver.me/xTxUlHlJ", "네이버", "top=100, left=100, width=600, height=500");  	
						});
				});
			$(function(){
				$('#createButton').click(
						function(data){
				 			window.open("CreateSchedule.do?token=" + accessToken +"", "startpop", "top=100, left=100, width=600, height=500, scrollbars=no, resizable=no ,status=no ,toolbar=no");
						});
				});
		}
		startCalendar();
		initMain();

		</script> 
      
      </div>
<!--  footer -->
      <div id="footer" style="border-top: 2px solid; ">
         	MOCOMSYS 2016 SANGSOO
      </div>
</body>
</html>