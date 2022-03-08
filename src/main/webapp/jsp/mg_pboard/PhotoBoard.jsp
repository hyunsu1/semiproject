<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 모임 - 사진첩</title>
<link rel="stylesheet" href="css/moimList.css">
</head>
<body>
	
	<div id="boardMenu">
	<a href='InfoC?moimName=${param.moimName }'>모임 정보</a>
	<a href='BoardC?moimName=${param.moimName }'>게시판</a>
    <a href='PhotoBoardC?moimName=${param.moimName }'>사진첩</a>
	<a href='MemberListC?moimName=${param.moimName }'>채팅</a>
	</div>
	
	<table>
		<tr>
			<td>
				<c:choose>
					<c:when test="${curPageNo ==1 }">
						<p id="arrowBtn">◀</p>
					</c:when>
					<c:otherwise>
						<a href="PB_PageC?p=${curPageNo-1 }" id="arrowBtn">◀</a>
					</c:otherwise>
				</c:choose>
			</td>
			<td>
				<form action="PhotoC" enctype="multipart/form-data" method="post">
					<div id="moimBack">
					<table id="photoTbl">
						<tr>
							<td id="photoTh1">사진첩</td>
							<td id="photoTh2"><a href="PB_RegC?moimName=${param.moimName }">글쓰기</a></td>
						</tr>
					</table>
						<c:forEach var="p" items="#{photos }">
							<div class="card-photo">
								<img src="file/${p.p_thumb }" class="card-img-top" style="height: 250px; width: 250px;" alt="..." onclick="location.href='PB_DetailC?no=${p.p_no}&moimName=${param.moimName }'">
								<div class="card-body-photo">
									<h5 onclick="location.href='PB_DetailC?no=${p.p_no}&moimName=${param.moimName }'" class="card-title">${p.p_title }</h5>
									<p onclick="location.href='PB_DetailC?no=${p.p_no}&moimName=${param.moimName }'" class="card-text">${p.p_text }</p>
									<p onclick="location.href='PB_DetailC?no=${p.p_no}&moimName=${param.moimName }'" class="card-text"><fmt:formatDate value="${p.p_date }" type="both" dateStyle="short" timeStyle="short"/></p>
								</div>
							</div>
						</c:forEach>
					</div>
					
					
				</form>
			</td>
			<td>
				<c:choose>
					<c:when test="${curPageNo == pageCount }">
						<p id="arrowBtn">▶</p>
					</c:when>
					<c:otherwise>
						<a href="PB_PageC?p=${curPageNo+1 }" id="arrowBtn">▶</a>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table>
	
</body>
</html>