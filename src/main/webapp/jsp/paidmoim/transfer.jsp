<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이체내역</title>
</head>
<body>
	<a href="MailC">메일함</a>
	<a href="ApplyMailC">가입신청함</a>
	<a href="TransferC">이체내역</a>
	
	<table id="myMoimTbl">
		<tr>
			<td id="myMoimTh1">보낸사람</td>
			<td id="myMoimTh1">금액</td>
			<td id="myMoimTh1">날짜</td>
		</tr>
		
		<c:forEach var="t" items="${transfers }">
			<tr>
				<td id="myMoimTd1">${t.sendId }</td>
				<td id="myMoimTd1">${t.fee }</td>
				<td id="myMoimTd1">${t.payDate }</td>
			</tr>
		</c:forEach>
	
	</table>
</body>
</html>