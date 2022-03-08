<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table id="myMoimTbl">
		<tr>
			<td id="MoimListTitle">가입한 모임</td>
		</tr>
      
		<c:forEach var="m" items="${moims }">
			<tr>
				<td id="myMoimListTd" onclick="location.href='InfoC?moimName=${m.name }'">${m.name }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>