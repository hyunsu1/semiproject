<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/search.js"></script>
</head>
<body>
	<div>
		<h3>${m_name }${ment }</h3>
		<h3>${m_pw }</h3>
		<h3>${r}</h3>
		<c:choose>
			<c:when test="${not empty m_pw}">
			<button onclick="window.close()">확인!</button>
			</c:when>
			<c:otherwise>
			<button onclick="history.back()">다시시도</button>
			</c:otherwise>
		</c:choose>
		
	</div>
</body>
</html>