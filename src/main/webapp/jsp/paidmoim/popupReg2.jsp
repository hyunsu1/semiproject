<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유료모임 가입 신청</title>
<link rel="stylesheet" href="css/popupReg.css">
</head>
<body>
	<table id="popupTbl">
		<tr>	
			<td colspan="2" id="popupTitle">모임 정보</td>
		</tr>
		
		<tr>	
			<td id="popupTd1">사진</td>
			<td id="popupImg"><img height="100px" width="100px" src="file/${moims.image }"></td>
		</tr>
		
		<tr>	
			<td id="popupTd1">이름</td>
			<td class="AppMailTd2">${moims.name }</td>
		</tr>
		
		<tr>	
			<td id="popupTd1">설명</td>
			<td class="AppMailTd2">${moims.introduce }</td>
		</tr>
		
		<tr>	
			<td id="popupTd1">현재 인원</td>
			<td class="AppMailTd2">${moims.currentMembers }</td>
		</tr>
		
		<tr>	
			<td id="popupTd1">최대 인원</td>
			<td class="AppMailTd2">${moims.maxMembers }</td>
		</tr>
		
		<tr>	
			<td id="popupTd1">가입비</td>
			<td class="AppMailTd2">${moims.fee }</td>
		</tr>
	</table>
	
	<form action="popupRegC2?name=${moims.name }" method="post" enctype="multipart/form-data">
		<table id="popupIntroTbl">
			<tr>	
				<td colspan="2" id="popupIntroTitle">자기소개</td>
			</tr>
			
			<tr>	
				<td id="popupTd1"><textArea name="introduce" style="width: 300px; height: 80px;"></textArea></td>
			</tr>
			
			<tr>	
				<td colspan="2" align="center"><button id="popupBtn">가입하기</button></td>
			</tr>
		</table>
	</form>
	
</body>
</html>