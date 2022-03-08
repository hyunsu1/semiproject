<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<link rel="stylesheet" href="css/myMoim.css">
</head>
<body>

	<form action="B_RegC?moimName=${param.moimName }" name="bRegForm" method="post" onsubmit="return bRegcheck();">
		<table id="myMoimTbl">
			<tr id="myMoimTr">
				<td colspan="2" align="center"><h3>게시글 작성하기</h3></td>
			</tr>
			<tr>
				<td id="myMoimTd1">글 제목</td>
				<td id="myMoimTd2"><input name="title" style="width: 350px; height: 30px;"></td>
			</tr>
			<tr>
				<td id="myMoimTd1">내용</td>
				<td id="myMoimTd3"><textarea name="text" style="width: 350px; height: 200px;"></textarea> </td>
			</tr>
			<tr>
				<td colspan="2">
					<button id="appBtnBtn">등록</button>
					<input name="name" type="hidden" value="${param.moimName }">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>