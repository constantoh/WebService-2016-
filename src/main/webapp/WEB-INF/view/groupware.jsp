<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
		<title>groupware</title>
		<!-- <meta charset="UTF-8"/>  -->
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
	System.out.println("***groupware.jsp***"); 
%>
	 <script type="text/javascript">
	 function modifyFavorite(){
			console.log("modify");
			window.open("ModifyFavorite.do", "네이버", "top=100, left=100, width=400, height=400");
		};
	 function popUp(room){
		 console.log(room)
			//window.open("PopUp.do?roomNo="
			//		+ room + "startpop", "top=0, left=0, width=800, height=500, scrollbars=no, resizable=no ,status=no ,toolbar=no");
		 window.open("PopUp.do?roomNo=" + room + "", "startpop", "top=100, left=100, width=600, height=500, scrollbars=no, resizable=no ,status=no ,toolbar=no");

	 	};
	 $(function(){
		$('#buttonCROOMa').click(function(){
				$.post('SearchConferenceRoom.do',
						{ room : 2645 },
						function(data){
							var roomNo = 2645;

							console.log("buttonCROOM clicked");
							$('#GROUPBody').html(calendar(data, roomNo));		
						});
				});
		$('#buttonCROOMb').click(function(){
			$.post('SearchConferenceRoom.do',
					{ room : 2646 },
					function(data){
						var roomNo = 2646;
						console.log("buttonCROOM clicked");
						$('#GROUPBody').html(calendar(data, roomNo));		
					});
			});
		$('#buttonCROOMc').click(function(){
			$.post('SearchConferenceRoom.do',
					{ room : 4160 },
					function(data){
						var roomNo = 4160;

						console.log("buttonCROOM clicked");
						$('#GROUPBody').html(calendar(data, roomNo));		
					});
			});
		$('#buttonCROOMd').click(function(){
			$.post('SearchConferenceRoom.do',
					{ room : 4161 },
					function(data){
						var roomNo = 4161;

						console.log("buttonCROOM clicked");
						$('#GROUPBody').html(calendar(data, roomNo));		
					});
			});
		$('#buttonCROOMe').click(function(){
			$.post('SearchConferenceRoom.do',
					{ room : 4162 },
					function(data){
						var roomNo = 4162;

						console.log("buttonCROOM clicked");
						$('#GROUPBody').html(calendar(data, roomNo));		
					});
			});
		
		function calendar(data, roomNo){
			
			
			var jsonData = JSON.parse(data);
			var length = jsonData.resources.length;
			var i=0;
			var now = new Date ();
	        var year = now.getFullYear();
	        var month = now.getMonth();
	        var thId=null;
	        if(month<10){
	        	thId=year+"0"+month;
	        }else{
	        	thId=year+""+month;
	        }
	        var temp='<h5>#회의실 예약 조회</h5><h6>#'+ jsonData.resources[0].room+'</h6>'
	        +'<a href="javascript:popUp('+ roomNo +')"><input type="button" class="btn btn-warning" value="예약"/></a>'
	        +'<br><table class="table" border=1 id="maintab" align="center" width="100%"'
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
		        		temp+='<td class="calHead" style="text-align:center; height="4px" vertical-align: top"><font color="red">'+strWeek[i]+'</font></td>';
		        	}else{
		        	temp+='<td class="calHead" style="text-align:center; height="4px" vertical-align: top">'+strWeek[i]+'</td>';
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
		        	temp+=i+' name="day" onmouseover="this.style.background=\'#e9e8ed\'" textSize="9px" width="10%" onmouseout="this.style.background=\'#ffffff\'" style="text-align:left; font-size:12px; vertical-align: top;cursor: pointer; height: 90px; background: rgb(255, 255, 255);"><font color="red">'+i+'</font><br>';
		        	}else{
		        		temp+=i+' name="day" onmouseover="this.style.background=\'#e9e8ed\'" textSize="9px" width="10%" onmouseout="this.style.background=\'#ffffff\'" style="text-align:left; font-size:12px; vertical-align: top;cursor: pointer; height: 90px; background: rgb(255, 255, 255);">'+i+'<br>';
		        	}
		        	var t=1;
		        	for(var k=0; k<length; k++){
		        		//console.log(k);
		        		var h = jsonData.resources[k];
		        		//console.log(h.date);
		        		if(h.date==i){
		        			//console.log("h date : " + h.date + ", i : " + i);
		        			temp+="(" + t++ + ")  " + h.title + " (" + h.startTime + " ~ " + h.endTime + ")<br><br>";
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
		$('#buttonADDR').click(function(){
			$.post('ShowAddr.do',
					function(data){
						console.log("buttonADDR clicked");
						$('#GROUPBody').html(table(data));		
					});
			});
		function table(data){
			
			var jsonData = JSON.parse(data);
			var length = jsonData.addresses.length;
			var i=0;
			
			var temp='<h5>#직원 주소록</h5><center><table class="table">'
					+'<tr align="center"><td width="10%">이름</td><td width="15%">부서</td>'
					+'<td width="10%">직위</td><td width="30%">메일주소</td><td width="25%">휴대전화</td></tr>';
			for(i=0; i<length; i++){
				var a = jsonData.addresses[i];
				temp+="<tr align='center'><td>" + a.name + "</td><td>" + a.dept +"</td><td>" 
				+ a.position +"</td><td>" + a.email + "</td><td>" + a.phoneNo +"</td></tr>";	
			}
			temp+="</table></center>";
			
			return temp;
		};
		
		
	 	});
	 
	 </script>
	 
	 	
	<body>
      <div id="login_container" class="window_center">
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
			<input id="button" class="btn btn-info" type="submit" value="GROUPWARE" >
		</form>
		<form>
				<input id="button" class="btn btn-info" type="submit" onclick="modifyFavorite()"value="FAVORITE">
		</form>
	 </div>
	 <div id="siedMenus">
	 	
	 	<input type="button" class="btn btn-success" id="buttonCROOM" style="font-size:15px" value="회의실 예약">
      	<form span style="float:left" >
			<input type="button" class="btn btn-success btn-sm" id="buttonCROOMa" value="소회의실1">
			<input type="button" class="btn btn-success btn-sm" id="buttonCROOMb" value="소회의실2">
			<input type="button" class="btn btn-success btn-sm" id="buttonCROOMc" value="가변회의실1">
			<input type="button" class="btn btn-success btn-sm" id="buttonCROOMd" value="가변회의실2">
			<input type="button" class="btn btn-success btn-sm" id="buttonCROOMe" value="가변회의실">
		</form>
		
      	<form span style="float:left">
			<input id="buttonADDR" class="btn btn-success" type="button" style="font-size:15px" value="주소록">
		</form>
		
	 </div>
	 
	 <div id="GROUPBody">
	 
	 </div>
</html>