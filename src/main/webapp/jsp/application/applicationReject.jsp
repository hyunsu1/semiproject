<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입 신청</title>
<link rel="stylesheet" href="css/answer.css">
<script>
	function popupClose() {
		self.close();
	}
</script>
</head>
<body>
	<table id="answerTbl">
		<tr>
			<td>거절되었습니다<p></td>
		</tr>
		<tr>
			<td align="center"><button onclick="popupClose(); opener.location.reload();" id="accBtn">확인</button></td>
		</tr>
	</table>
</body>
</html>