<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 모임 - 사진첩</title>
<script src="js/board.js"></script>
<link rel="stylesheet" href="css/myMoim.css">
</head>
<body>
	<form action="PB_UpdateC?moimName=${param.moimName }" name="pUpForm" enctype="multipart/form-data" method="post">
		<table id="myMoimTbl">
			<tr>
				<td id="myMoimTd1">글 제목</td>
				<td id="myMoimDetail2"><input name="np_title" value="${photo.p_title }">
				</td>
			</tr>
			<tr>
				<td id="myMoimTd1">내용</td>
				<td id="myMoimDetail2"><textarea name="np_text">${photo.p_text }</textarea>
				<input name="hiddenNo" type="hidden" value="${photo.p_no }"></td>
			</tr>
			<tr>
				<td id="myMoimTd1"><img src="file/${photo.p_thumb }" height="150px" width="150px"></td>
				<td id="myMoimFileTd">
					사진 1: <input name="np_thumb" type="file">
						   <input name="hp_thumb" type="hidden" value="${photo.p_thumb }">
				</td>
			</tr>
			
			<c:forEach var="f" items="${files }" varStatus="Status">
				<tr>
					<td id="myMoimTd1">
						<c:choose>
	 						<c:when test="${not empty f }">
								<img src="file/${f }" height="150px" width="150px">
							</c:when>
							<c:otherwise>
								파일 선택
							</c:otherwise>
						</c:choose>
					</td>
 					<td id="myMoimFileTd">
 						사진	${Status.index + 2 } : <input type="file" name="newFile${Status.index + 2 }">
 												   <input type="hidden" value="${f }" name="oldFile${Status.index + 2 }">
 					</td>
				</tr>  
			</c:forEach>		
			<tr>
				<td colspan="2" style="height: 70px;">
					<button type="button" onclick="location.href='PhotoBoardC?moimName=${param.moimName }'" id="appBtnBtn">목록으로</button>
					<button name="no" value="${photo.p_no }" id="appAcceptBtn">수정</button>
				</td>
			</tr>
		</table>
	</form>
		
</body>
</html>