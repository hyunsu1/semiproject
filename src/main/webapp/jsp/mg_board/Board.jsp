<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 모임</title>
<link rel="stylesheet" href="css/myMoim.css">
</head>
<body>
	<div id="boardMenu">
	<a href='InfoC?moimName=${param.moimName }'>모임 정보</a>
	<a href='BoardC?moimName=${param.moimName }'>게시판</a>
    <a href='PhotoBoardC?moimName=${param.moimName }'>사진첩</a>
	<a href='MemberListC?moimName=${param.moimName }'>채팅</a>
	</div>
	
	<table id="boardTbl1">
		<tr id="myMoimTr">
			<td>게시판</td>
			<td><a href="B_RegC?moimName=${param.moimName }">글쓰기</a></td>
		</tr>
	</table>

	<table id="boardTbl2">
		<tr>
			<th class="boardThTitle">제목</th>
			<th class="boardThTitle">등록일</th>
			<th class="boardThTitle">조회수</th>
		</tr>
		
	<c:forEach var="b" items="${boards }">
		<tr class="boardTr1">
			<td class="boardTd1" onclick="location.href='B_DetailC?no=${b.b_no}&moimName=${param.moimName }'">${b.b_title }</td>
			<td class="boardTd1" onclick="location.href='B_DetailC?no=${b.b_no}&moimName=${param.moimName }'"> <fmt:formatDate value="${b.b_date }" type="both" dateStyle="short" timeStyle="short"/> </td>
			<td class="boardTd1"> ${b.b_cnt } </td>
		</tr>
	</c:forEach>			
	</table>
	
	<table style="width: 500px; height: 50px; margin-left: auto; margin-right: auto;">
		<tr>
			<td align="center">
				<a href="B_PageC?p=1">[처음으로]</a>
				<a href="B_PageC?p=${curPageNo -1 }">[이전]</a>
				<c:forEach var="p" begin="1" end="${pageCount }">
					<a href="B_PageC?p=${p }"> [${p }] </a>
				</c:forEach>
				<a href="B_PageC?p=${curPageNo +1 }">[다음]</a>
				<a href="B_PageC?p=${pageCount }">[끝으로]</a>
			</td>
		</tr>
	</table>
	
	<table style="width: 400px; height: 50px; margin-left: auto; margin-right: auto;">
		<tr>
			<td> <input style="width: 300px; height: 30px;" id="search"> </td>
			<td> <button onclick="search(${param.moimName });" id="searchBtn">검색</button> </td>
		</tr>
	</table>	


</body>
</html>