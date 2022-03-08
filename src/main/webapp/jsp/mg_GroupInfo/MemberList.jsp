<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메일 보내기</title>
<link rel="stylesheet" href="css/myMoim.css">
</head>
<body>
	<div id="boardMenu">
		<a href='InfoC?moimName=${param.moimName }'>모임 정보</a>
		<a href='BoardC?moimName=${param.moimName }'>게시판</a>
	    <a href='PhotoBoardC?moimName=${param.moimName }'>사진첩</a>
		<a href='MemberListC?moimName=${param.moimName }'>채팅</a>
	</div>
	
	<table id="myMoimTbl">
		
		<tr>	
			<td id="myMoimTh1">직책</td>
			<td id="myMoimTh1">아이디</td>
			<td id="myMoimTh1"></td>
		</tr>
		
		<c:forEach var="m" items="${members }">
			<tr>	
				<td id="myMoimTh1">${m.position }</td>
				<td id="myMoimTh1">${m.userId }</td>
				<td id="myMoimTh1"><button onclick="location.href='MailWriteC?receiveId=${m.userId}'">쪽지</button></td>
			</tr>
		</c:forEach>
		
	</table>
</body>
</html>