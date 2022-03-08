<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글</title>
<link rel="stylesheet" href="css/myMoim.css">
</head>
<body>

		<table id="myMoimTbl">
			<tr id="myMoimTr">
				<td colspan="2" align="center"><h3>상세 페이지</h3></td>
			</tr>
			<tr>
				<td id="myMoimTd1">글 제목</td>
				<td id="myMoimTd2">${board.b_title }</td>
			</tr>
			<tr>
				<td id="myMoimTd1">내용</td>
				<td id="myMoimTd3">${board.b_text }</td>
			</tr>
			<tr>
				<td id="myMoimTd1">등록일</td>
				<td id="myMoimTd2"><fmt:formatDate type="both" dateStyle="short"
						timeStyle="short" value="${board.b_date }" /></td>
			</tr>
			<tr">
				<td id="myMoimTd1">조회수</td>
				<td id="myMoimTd2">${board.b_cnt }</td>
			</tr>
			<tr>
				<td colspan="2" id="myMoimTdBtn">
					<button onclick="location.href='BoardC?moimName=${param.moimName }'" id="appBtnBtn" style="margin-right: 20px;">목록으로</button>
					<button onclick="UpdateBoard('${board.b_no}','${param.moimName }')" id="appAcceptBtn">수정</button>
					<button onclick="DelBoard('${board.b_no}','${param.moimName }')" id="appBtnBtn" style="margin-left: 10px;">삭제</button>
				</td>
			</tr>
		</table>
</body>
</html>