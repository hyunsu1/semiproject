<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 모임 - 사진첩</title>
<script src="js/board.js"></script>
<link rel="stylesheet" href="css/myMoim.css">
</head>
<body>

	<!-- <form action="PB_DetailC" name="form" enctype="multipart/form-data" method="post"> -->
		<table id="myphotoTbl">
			<tr id="myMoimTr">
				<td colspan="2" align="center"><h3>상세 페이지</h3></td>
			</tr>
			<tr>
				<td id="myphotoTd1">글 제목</td>
				<td id="myphotoTd2">${photo.p_title }</td>
			</tr>
			<tr>
				<td id="myphotoTd1">내용</td>
				<td id="myphotoTd2">${photo.p_text }</td>
			</tr>
			<tr>
				<td  colspan="2" >
					<img src="file/${photo.p_thumb }" height="150px" width="150px">
					<c:forEach var="f" items="${files }">
						<c:if test="${f ne null}">
							<img src="file/${f }" height="150px" width="150px">							       
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td id="myphotoTd1">등록일</td>
				<td id="myphotoTd2"><fmt:formatDate type="both" dateStyle="short"
						timeStyle="short" value="${photo.p_date }" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<button onclick="location.href='PhotoBoardC?moimName=${param.moimName }'" id="appBtnBtn" style="margin-right: 20px;">목록으로</button>
					<button onclick="location.href='PB_UpdateC?no=${photo.p_no}'" id="appAcceptBtn">수정</button>
					<button onclick="DelPhotoBoard('${photo.p_no}','${param.moimName }')" id="appBtnBtn" style="margin-left: 10px;">삭제</button>
				</td>
			</tr>
		</table>
	

</body>
</html>