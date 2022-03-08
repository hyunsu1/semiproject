<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="LoginC" method="post">

		<table id="loginAreaTbl">
			<tr>
				<td class="logInTd1">ID </td>
				<td class="logInTd2"><input name="id" id="id"></td>
			</tr>
			<tr>
				<td class="logInTd1">PW</td>
				<td class="logInTd2"><input name="pw" id="pw"></td>
			</tr>
			<tr>
				<td colspan="2"> 
					<button class="loginBtn">로그인</button>
					<button class="loginBtn" type="button" onclick='location.href="RegAccountC"'>회원가입</button>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>