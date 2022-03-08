<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메일 작성</title>
</head>
<body>
	<form action="MailWriteC" method="post">
		<table id="myMoimTbl">
			<tr>
				<td id="myMoimDetail1">받는 사람</td>
				<td id="myMoimDetail2"><input name="receiveId" value="${param.receiveId }"></td>
			</tr>
			
			<tr>
				<td id="myMoimDetail1">제목</td>
				<td id="myMoimDetail2"><input name="title"></td>
			</tr>
			
			<tr>
				<td id="myMoimDetail1">내용</td>
				<td id="myMoimDetail2"><textArea name="content"></textArea></td>
			</tr>
			
			<tr>
				<td colspan="2"  id="myMoimTdBtn"><button id="appRejectBtn">보내기</button></td>
			</tr>
		
		</table>
	</form>
</body>
</html>