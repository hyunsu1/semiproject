<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/search.js"></script>
</head>
<body>
	<form name="idfindscreen" method="post">
		<div>
			<h3>핸드폰 본인확인</h3>
		</div>
		<section class = "form-search">
			<div class = "find-name">
				<label>이름</label>
				<input type="text" name="member_name" class = "btn-name" placeholder = "등록한 이름"><br>
			</div>
			<div class = "find-phone">
				핸드폰 번호
				<select name="phone1">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="017">017</option>
						<option value="018">018</option>
						<option value="019">019</option></select> - 
					<input type="text" name="phone2" size="5" maxlength="4"> - 
					<input type="text" name="phone3" size="5" maxlength="4">
				
			</div>
		</section>
		<div class="btnSearch">
			<input type="button" name="enter" value="찾기" onclick="id_searchGo()">
			<input type="button" name="cancle" value="취소" onclick="window.close()">
			
		</div>
	</form>
</body>
</html>