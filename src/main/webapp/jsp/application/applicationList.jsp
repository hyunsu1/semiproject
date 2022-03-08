<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입신청함</title>
<link rel="stylesheet" href="css/myMoim.css">
</head>
<body>
	<div id="bordMenu2">
	<a href="MailC">메일함</a>
	<a href="ApplyMailC">가입신청함</a>
	<a href="TransferC">이체내역</a>
	</div>
	
	<table id="myMoimTbl2">
		<tr>
			<td id="myMailListTd" colspan="2" align="center">가입 신청 목록</td>
		</tr>
		<c:forEach var="a" items="${applications }">
			<tr>
				<td id="myMailListTd2">${a.sendId}님의 '${a.moimName }' 모임 가입신청 입니다.</td>
				<td id="myMailListTd2"><button onclick="applicationDetail(${a.num});" id="appBtnBtn">보기</button></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>