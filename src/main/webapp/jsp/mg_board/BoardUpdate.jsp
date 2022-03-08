<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<script src="js/board.js"></script>
<link rel="stylesheet" href="css/myMoim.css">
</head>
<body>

	<form action="B_UpdateC?moimName=${param.moimName }" method="post">
		<table id="myMoimTbl">
			<tr id="myMoimTr">
				<td colspan="2" align="center"><h3>게시글 수정</h3></td>
			</tr>
			<tr>
				<td id="myMoimTd1">글 번호</td>
				<td id="myMoimTd2">${board.b_no }</td>
			</tr>
			<tr>
				<td id="myMoimTd1">글 제목</td>
				<td id="myMoimTd2"><input name="title" value="${board.b_title }" style="width: 350px; height: 30px;"></td>
			</tr>
			<tr>
				<td id="myMoimTd1">내용</td>
				<td id="myMoimTd2"><textarea name="text" style="width: 350px; height: 200px;">${board.b_text }</textarea></td>
			</tr>
			<tr>
				<td id="myMoimTd1">등록일</td>
				<td id="myMoimTd2"><fmt:formatDate type="both" dateStyle="short"
						timeStyle="short" value="${board.b_date }" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="button" onclick="location.href='BoardC?moimName=${param.moimName }'" id="appBtnBtn">목록으로</button>
					<button name="no" value="${board.b_no }" id="appAcceptBtn">수정</button>
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>