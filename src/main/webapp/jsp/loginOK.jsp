<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<table id="loginAreaTbl">
			<tr>
				<td id="logInTd3">${r } </td>
			</tr>
			<tr>
				<td id="logInTd3">${sessionScope.accountInfo.m_name } (${sessionScope.accountInfo.m_id}) 님 안녕하세요! </td>
			</tr>
			<tr>
				<td colspan="2"> 
				<button  class="loginBtn" onclick="location.href='MailC'">편지함</button>
				<button  class="loginBtn" onclick="location.href='LogoutC'">로그아웃</button>
				</td>
			</tr>
		</table>
</body>
</html>