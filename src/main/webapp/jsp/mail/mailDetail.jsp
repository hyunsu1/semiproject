<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메일함</title>
</head>
<body>
	<table id="myMoimTbl">
		<tr>
			<td id="myMoimDetail1">보낸사람</td>
			<td id="myMoimDetail2">${mailDetail.sendId }</td>
		</tr>
		
		<tr>
			<td id="myMoimDetail1">제목</td>
			<td id="myMoimDetail2">${mailDetail.title }</td>
		</tr>
		
		<tr>
			<td id="myMoimDetail1">내용</td>
			<td id="myMoimDetail2">${mailDetail.content }</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center" id="myMoimTdBtn">
				<button onclick="location.href='MailC'" id="appRejectBtn" style="margin-right: 15px;">목록으로</button>
				<button onclick="location.href='MailReplyC?receiveId=${mailDetail.sendId}'" id="appAcceptBtn">답장</button>
				<button onclick="mailDelete(${mailDetail.num});" id="appRejectBtn">삭제</button>
			</td>
		</tr>
	</table>
</body>
</html>