<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입 신청자 정보</title>
<link rel="stylesheet" href="css/myMoim.css">
</head>
<body>
	<table id="myMailTbl2">
		<tr>	
			<td colspan="2" align="center">가입 정보</td>
		</tr>
		
		<tr>	
			<td class="AppMailTd1">id</td>
			<td class="AppMailTd2">${mApp.m_id }</td>
		</tr>
		
		<tr>	
			<td class="AppMailTd1">이름</td>
			<td class="AppMailTd2">${mApp.m_name }</td>
		</tr>
		
		<tr>	
			<td class="AppMailTd1">생년월일</td>
			<td class="AppMailTd2">${mApp.m_birth }</td>
		</tr>
		
		<tr>	
			<td class="AppMailTd1">성별</td>
			<td class="AppMailTd2">${mApp.m_gender }</td>
		</tr>
		
		<tr>	
			<td class="AppMailTd1">지역</td>
			<td class="AppMailTd2">
				<c:forEach var="a" items="${dar}">
				${a } 
				</c:forEach>
			</td>
		</tr>
		
		<tr>	
			<td class="AppMailTd1">자기소개</td>
			<td class="AppMailTd2">${applications.introduce }</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<button onclick="location.href='ApplicationAcceptC?id=${id}&moimName=${applications.moimName }&result=accept'" id="appAcceptBtn">수락</button>
				<button onclick="location.href='ApplicationRejectC?id=${id}&moimName=${applications.moimName }&result=reject'" id="appRejectBtn">거절</button>
			</td>
		</tr>
	</table>
</body>
</html>