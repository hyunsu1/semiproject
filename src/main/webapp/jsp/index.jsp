<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HappyFriend</title>
<script src="js/sojaeji.js"></script>
<script src="js/popup.js"></script>
<script src="js/mail.js"></script>
<script src="js/board.js"></script>
<script src="js/checkMail.js"></script>
<script src="js/checkValue.js"></script>
<script src="js/check.js"></script>
<script src="js/validCheck.js"></script>
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/myPage.css">
<link rel="stylesheet" href="css/reg.css">
<link rel="stylesheet" href="css/myMoim.css">
<link rel="stylesheet" href="css/createMoim.css">
<link rel="stylesheet" href="css/moimList.css">
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
%>
	<div id="title"><a href="HC"><img src="Image/titleImage.jpg" id="titleImage">Happy Friend</a></div>
	<div id="loginArea">
	<jsp:include page="${loginPage }"></jsp:include>
	</div>
	
	<table id="site">
		<tr>
			<td id="menu"> 
				<a href="PaidC" id="a">유료모임</a>
				<a href="FreeC" id="a">무료모임</a>
				<a href="MyGroupC" id="a">내모임</a>
				<a href="MypageC" id="a">마이페이지</a>
			</td>
		</tr>
		
		<tr>
			<td id="content">
				<jsp:include page="${contentPage }"></jsp:include>	
			</td>
		</tr>
		
	</table>
	
</body>
</html>