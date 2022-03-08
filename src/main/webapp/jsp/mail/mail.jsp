<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메일함</title>
<link rel="stylesheet" href="css/myMoim.css">
</head>
<body>
	<a href="MailC">메일함</a>
	<a href="ApplyMailC">가입신청함</a>
	<a href="TransferC">이체내역</a>
	
	<table id="myMoimTbl">
		<tr>
			<td id="myMoimTh1">보낸사람</td>
			<td id="myMoimTh2">제목</td>
		</tr>
		<c:forEach var="m" items="${mails }">
			<tr>
				<td id="myMoimTd1">${m.sendId }</td>
				<td id="myMoimTd2" onClick="location.href='MailDetailC?num=${m.num}'" style="cursor:pointer;">${m.title }</td>
			</tr>
		</c:forEach>
		
		<tr>
			<td colspan="2" align="center" id="myMoimTdBtn"><button onclick="location.href='MailWriteC'" id="appRejectBtn">메일쓰기</button></td>
		</tr>
	</table>
</body>
</html>