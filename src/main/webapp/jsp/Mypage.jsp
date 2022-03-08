<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPage</title>
<script type="text/javascript" src="../js/checkValue.js"></script>
<link rel="stylesheet" href="myPage.css">
</head>
<body>
	<table id="myPageTbl">
		<tr>
			<td colspan="2" id="title">내 정보</td>
		</tr>
		<tr>
			<td class="myPageTd1">이름</td>
			<td class="myPageTd2">${sessionScope.accountInfo.m_name }</td>
		</tr>
		<tr>
			<td class="myPageTd1">아이디</td>
			<td class="myPageTd2">${sessionScope.accountInfo.m_id }</td>
		</tr>
		<tr>
			<td class="myPageTd1">비밀번호</td>
			<td class="myPageTd2">${sessionScope.accountInfo.m_pw }</td>
		</tr>
		<tr>
			<td class="myPageTd1">성별</td>
			<td class="myPageTd2">${sessionScope.accountInfo.m_gender }</td>
		</tr>
		<tr>
			<td class="myPageTd1">생년월일</td>
			<td class="myPageTd2">${sessionScope.accountInfo.m_birth }</td>
		</tr>
		<tr>
			<td class="myPageTd1">이메일</td>
			<td class="myPageTd2">${sessionScope.accountInfo.m_email }</td>
		</tr>
		<tr>
			<td class="myPageTd1">핸드폰 번호</td>
			<td class="myPageTd2">${sessionScope.accountInfo.m_phone }</td>
		</tr>
		<tr>
			<td class="myPageTd1">지역</td>
			<td class="myPageTd2"><c:forEach var="a" items="${ar}">
				${a } 
			</c:forEach></td>
		</tr>
		<tr>
			<td class="myPageTd1">비밀번호 확인 질문1</td>
			<td class="myPageTd2">${qa1[0] }${qa1[1] }</td>
		</tr>
		<tr>
			<td class="myPageTd1">비밀번호 확인 질문2</td>
			<td class="myPageTd2">${qa2[0] }${qa2[1] }</td>
		</tr>
		<tr>
			<td colspan="2"><button class="loginBtn"
					onclick="location.href='UpdateAccountC?id=${sessionScope.accountInfo.m_id }'">수정</button></td>
		</tr>
		<tr>
			<td colspan="2"><button class="loginBtn"
					onclick="deleteAccount('${sessionScope.accountInfo.m_id }');">탈퇴하기</button></td>
		</tr>
	</table>
	<div id="div1">
	<table id="CreatedByMeTbl">
		<tr id="cbmTr1">
			<td colspan="10" id="title">내가 만든 유료 모임</td>
		</tr>
		<tr class="cbmTr2">
			<th class="cbmTd1">이름</th>
			<th class="cbmTd2">관심사</th>
			<th class="cbmTd3">지역</th>
			<th class="cbmTd4">최대인원</th>
			<th class="cbmTd5">소개</th>
			<th class="cbmTd6">사진</th>
			<th class="cbmTd7">현재인원</th>
			<th class="cbmTd8">만든날짜</th>
			<th class="cbmTd8"></th>
			<th class="cbmTd8"></th>
		</tr>
		<c:if test="${temp2 == 1 }">
			<c:forEach var="m" items="${moims_paid }">
				<tr class="cbmTr2">
					<td class="cbmTd1">${m.p_name }</td>
					<td class="cbmTd2">${m.p_interest }</td>
					<td class="cbmTd3">${m.p_region }</td>
					<td class="cbmTd4">${m.p_maxMembers }</td>
					<td class="cbmTd5">${m.p_introduce }</td>
					<td class="cbmTd6"><img height="100px" width="100px" src="file/${m.p_image }"></td>
					<td class="cbmTd7">${m.p_currentMembers }</td>
					<td class="cbmTd8">${m.p_createDate }</td>
					<td class="cbmTd1"><button onclick="moimUpdate('${m.p_name }','${temp2 }')">수정</button></td>
					<td class="cbmTd1"><button onclick="moimDelete('${m.p_name}')">삭제</button></td>
				</tr>
			</c:forEach>
		</c:if>
	</table>

	<table id="CreatedByMeTbl">
		<tr id="cbmTr1">
			<td colspan="10" id="title">내가 만든 무료 모임</td>
		</tr>
		<tr class="cbmTr2">
			<th class="cbmTd1">이름</th>
			<th class="cbmTd2">관심사</th>
			<th class="cbmTd3">지역</th>
			<th class="cbmTd4">최대인원</th>
			<th class="cbmTd5">소개</th>
			<th class="cbmTd6">사진</th>
			<th class="cbmTd7">현재인원</th>
			<th class="cbmTd8">만든날짜</th>
			<th class="cbmTd8"></th>
			<th class="cbmTd8"></th>
		</tr>

		<c:if test="${temp1 == 1 }">
			<c:forEach var="m" items="${moims_free }">
				<tr  class="cbmTr2">
					<td class="cbmTd1">${m.f_name }</td>
					<td class="cbmTd2">${m.f_interest }</td>
					<td class="cbmTd3">${m.f_region }</td>
					<td class="cbmTd4">${m.f_maxMembers }</td>
					<td class="cbmTd5">${m.f_introduce }</td>
					<td class="cbmTd6"><img height="100px" width="100px" src="file/${m.f_image }"></td>
					<td class="cbmTd7">${m.f_currentMembers }</td>
					<td class="cbmTd8">${m.f_createDate }</td>
					<td class="cbmTd1"><button onclick="moimUpdate2('${m.f_name }','${temp1 }')">수정</button></td>
					<td class="cbmTd1"><button onclick="moimDelete2('${m.f_name }')">삭제</button></td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</div>
</body>
</html>