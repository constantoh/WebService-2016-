<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>즐겨찾기 수정</title>
<!-- bootstrap -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
		<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<script>
</script>
<body>
<form action="ModifyFavorite2.do" method="post">
<center>
	<table class="table" style="width:80%" border="2" cellpadding="2" cellspacing="2">
		<thead>
			<tr>
				<th colspan="10" class="tb_title">
					<center>즐겨찾기 수정</center>
				</th>
			</tr>
		</thead>
		<tbody>
				<tr>
					<td>즐겨찾기 1</td>
				<td>
					<select class="form-control inputstl" style="width:180px;height:40px; float:left" id="f1" name="f1">
						<option value="daily">실적 조회</option>
						<option value="room">회의실 사용 조회</option>
						<option value="mail">E-Mail</option>
						<option value="addr">주소록</option>
						<option value="cust">고객 조회</option>
					</select>
				</td>
				</tr>
				<tr>
					<td>즐겨찾기 2</td>
				
					<td>
					<select class="form-control inputstl" style="width:180px;height:40px; float:left" id="f2" name="f2">
						<option value="daily">실적 조회</option>
						<option value="room">회의실 사용 조회</option>
						<option value="mail">E-Mail</option>
						<option value="addr">주소록</option>
						<option value="cust">고객 조회</option>
					</select>
				</td>
				</tr>
				<tr>
					<td>즐겨찾기 3</td>
				
					<td>
					<select class="form-control inputstl" style="width:180px;height:40px; float:left" id="f3" name="f3">
						<option value="daily">실적 조회</option>
						<option value="room">회의실 사용 조회</option>
						<option value="mail">E-Mail</option>
						<option value="addr">주소록</option>
						<option value="cust">고객 조회</option>
					</select>
				</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" class="btn btn-warning" value="수정"/></td>
				</tr>
			</tbody>
		</table>
		</center>
		
	</form>
	
</body>
</html>