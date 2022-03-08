<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 모임 - 사진첩</title>
<script src="js/board.js"></script>
<link rel="stylesheet" href="">
</head>
<body>
	
	<form action="PB_RegC?moimName=${param.moimName }" name="pRegForm" enctype="multipart/form-data" method="post" onsubmit="return pRegcheck();">
		<table id="myMoimTbl">
			<tr>
				<td id="myMoimTd1">글 제목</td>
				<td id="myMoimDetail2"><input name="p_title"></td>
			</tr>
			<tr>
				<td id="myMoimTd1">내용</td>
				<td id="myMoimDetail2"><textarea name="p_text"></textarea> </td>
			</tr>
			<tr>
				<td id="myMoimTd1">파일 선택 (필수선택)</td>
				<td id="myMoimFileTd">사진 1: <input name="p_thumb" type="file"></td>
			</tr>
			<tr>
				<td id="myMoimTd1">파일 선택</td>
				<td id="myMoimFileTd">사진 2: <input name="p_file1" type="file"></td>
			</tr>
			<tr>
				<td id="myMoimTd1">파일 선택</td>
				<td id="myMoimFileTd">사진 3: <input name="p_file2" type="file"></td>
			</tr>
			<tr>
				<td id="myMoimTd1">파일 선택</td>
				<td id="myMoimFileTd">사진 4: <input name="p_file3" type="file"></td>
			</tr>
			<tr>
				<td id="myMoimTd1">파일 선택</td>	
				<td id="myMoimFileTd">사진 5: <input name="p_file4" type="file"></td>
			</tr>
			<tr>
				<td colspan="2" style="height: 70px;">
					<button id="appBtnBtn">등록</button>
					<input name="name" type="hidden" value="${param.moimName }">	
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>