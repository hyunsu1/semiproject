<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>무료모임 - 게임</title>
<link rel="stylesheet" href="css/moimList.css">
</head>
<body>
	<form action="GameC" style="margin-left: 1200px; margin-top: 50px;">
		<select name="sido" id="sido"><option value="">전체</option></select>
		<select name="gugun" id="gugun"><option value="">전체</option></select>
	
		<script>
		sojaeji("${sido}", "${gugun}");
		</script>
		
		<input name="searchName" id="searchName">
		<input name="interest" type="hidden" value="게임">
	    <button id="searchBtn">검색</button>
	</form> 
	
	<div id="moimBack">
		<c:forEach var="m" items="${moims }"> 
		<c:set var="interest" value="게임" /> 
		<c:if test="${m.interest eq '게임'}">
		
			<div class="card">
				<img src="file/${m.image }" class="card-img-top" style="height: 250px; width: 250px;" alt="...">
				<div class="card-body">
					<h5 class="card-title">${m.name }</h5>
					<p class="card-text">${m.introduce }</p>
					<button onclick="popup('${m.name}')" id="crtBtnBtn">가입</button>
				</div>
			</div>
			
		</c:if>
		</c:forEach>
	
		<div align="center" id="moimBlock">
			<c:if test="${currentBlock ne 0}">
				<a href="FreeC?currentBlock=${currentBlock-1 }&page=${(currentBlock-1)*10+1 }&sido=${sido }&gugun=${gugun }&searchName=${searchName}" class="btn btn-outline-dark">◀</a>
			</c:if>
			
			<c:forEach begin="${blockStartNum }" end="${blockLastNum }" step="1" var="index">
				<a href="FreeC?page=${index }&currentBlock=${currentBlock}&sido=${sido }&gugun=${gugun }&searchName=${searchName}" class="btn btn-outline-dark">${index }</a>
			</c:forEach>
			
			<c:if test="${currentBlock ne maxBlock}">
				<a href="FreeC?currentBlock=${currentBlock+1 }&page=${(currentBlock+1)*10+1 }&sido=${sido }&gugun=${gugun }&searchName=${searchName}" class="btn btn-outline-dark">▶</a>
			</c:if>
		</div>
	</div>
</body>
</html>