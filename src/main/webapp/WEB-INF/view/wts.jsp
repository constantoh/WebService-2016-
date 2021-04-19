<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>wts</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="resourse/css/Login.css" rel="stylesheet">
		<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
		<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
		<!-- bootstrap -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
		<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	</head>
	 <% 
	 	System.out.println("*** wts.jsp ***");
	 	response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
	 %>
	<body>
	<script type="text/javascript">
	function modifyFavorite(){
		console.log("modify");
		window.open("ModifyFavorite.do", "네이버", "top=100, left=100, width=400, height=400");
	};
		$(function(){
			function forSyntax(data, many, jsonData){
				var t= "";
				for(var i=0; i<many; i++){
					var h = jsonData.customers[i];

					if(i%6==0)
						t+="<tr>";
					console.log(h.name);
					t+="<td style='width:15px; height:30px; text-align: center'>"
						+ (i+1) + "</td><td style='width:150px; height:30px; cursor:pointer; font size:8px;'"
						+ h.uid + "')';>"
						+"<a href='CustomInfo.do?cus_uid="
						+ h.uid + "'>" 
						+ h.name + "</a></td>";
						
					if(i%6==5)
						t+="</tr>";
				}
				return t;
			}
			$('#buttonCustomer').click(function(){
				$.post('SearchCustomer.do',
						function(data){
							
							var jsonData = JSON.parse(data);
							var many = jsonData.many;
							
							console.log("buttonCustomer clicked"); 
							
							$('#WTSBody').html("<h5>#고객 정보 관리</h1>" + 
							"<div>" +
								"<table class='table' border='2' cellpadding='5' cellspacing='5'>"
								+"<thead><tr><th colspan='12'>"
								+"<center>고객 정보</center></th></tr></thead><tbody>"
								+ forSyntax(data, many, jsonData) +"</tbody></table>")				
						});
				});
			$('#buttonDaily').click(function(){
				$.post('DailySearchById.do',
						function(data){
							console.log("buttonDaily clicked");
							$('#WTSBody').html(calendar(data));
						});
			});
			function calendar(data){
				
				var jsonData = JSON.parse(data);
				var length = jsonData.tr.length;
				var i=0;
				var now = new Date ();
		        var year = now.getFullYear();
		        var month = now.getMonth() +1;
		        var thId=null;
		        if(month<10){
		        	thId=year+"0"+month;
		        }else{
		        	thId=year+""+month;
		        }
		        var temp='<h5>#실적 조회</h1>&nbsp&nbsp&nbsp[]: 공통업무,  ():등록자, {}: 제목<center><table class="table" border=1 id="maintab" align="center"'
		        + 'margin-left: 25px;"><tr><th colspan="7" name="calday" id='+thId+' style="text-align: center">';
			        var total=(year-1)*365+(year-1)/4
		            -(year-1)/100
		            +(year-1)/400;
			        
			        var lastDay=[31,28,31,30,31,30,31,31,30,31,30,31];
			        if((year%4==0 && year%100!=0)||(year%400==0))
			        	lastDay[1]=29;
			        else
			        	lastDay[1]=28;
			        for(var i=0;i<month-1;i++)
			        	total+=lastDay[i];
			        
			        total++;
			        
			        var week=Math.floor(total%7);
			        
			        var strWeek=['일','월','화','수','목','금','토'];
			        temp+=year+'년'+month+'월</th></tr>';
			        temp+='<tr>';
			        for(var i=0;i<7;i++){
			        	if(i==0){
			        		temp+='<td class="calHead" style="font size:8px; text-align:center; height="4px" vertical-align: top"><font color="red">'+strWeek[i]+'</font></td>';
			        	}else{
			        	temp+='<td class="calHead" style="font size:8px; text-align:center; height="4px" vertical-align: top">'+strWeek[i]+'</td>';
			        	}
			        }
			        temp+='</tr><tr>';
			        for(var i=1;i<=lastDay[month-1];i++){
			        	if(i==1){
			        		for(var j=0;j<week;j++){
			        			temp+='<td></td>';
			        		}
			        	}
			        	if(i<10){
			        		temp+='<td class="calBody" id=0';
			        	}else{
			        		temp+='<td class="calBody" id=';
			        	}
			        	if(week==0){
			        		temp+=i+' name="day" onmouseover="this.style.background=\'#e9e8ed\'" textSize="7px" width="10%" onmouseout="this.style.background=\'#ffffff\'" style="text-align:left; vertical-align: top;cursor: pointer; height: 90px; background: rgb(255, 255, 255);"><font color="red">'+i+'</font><br>';
			        	}else{
			        		temp+=i+' name="day" onmouseover="this.style.background=\'#e9e8ed\'" textSize="7px" width="10%" onmouseout="this.style.background=\'#ffffff\'" style="text-align:left; vertical-align: top;cursor: pointer; background: rgb(255, 255, 255);">'+i+'<br>';
			        	}
			        	for(var k=0; k<length; k++){
			        		//console.log(k);
			        		var h = jsonData.tr[k];
			        		//console.log(h.date);
			        		if(h.day==i){
			        			//console.log("h date : " + h.date + ", i : " + i);
			        			temp+=h.req_dept + " (" + h.name + "){" + h.note +"}<br>";
			        		}
			        	}
			        	temp+='</td>';
			        	week++;
			        	if(week>6){
			        		week=0;
			        		temp+='</tr>';
			        	}
			        	if((i==lastDay[month-1])&&week!=0){
			        		for(var k=week;k<7;k++){
			        			temp+='<td>&nbsp;&nbsp;</td>';
			        		}
			        		
			        	}
			        }
			        
			        return temp;
			};
			});
		</script>
	
      <div id="login_container" >
         <img src="resourse/css/logo.png" id="Logo">
      </div>
      
      <div id="menus" >
      
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
	 <div id="siedMenus">
	 
	 	<form span style="float:left">
			<input id="buttonDaily" class="btn btn-success" type="button" style="font-size:15px" value="실적 조회">
		</form>
	 	
      	<form span style="float:left">
			<input id="buttonCustomer" class="btn btn-success" type="button" style="font-size:15px" value="고객 조회">
		</form>
	 	
	 </div>
	 
	 <div id="WTSBody">
	 
	 </div>
</html>