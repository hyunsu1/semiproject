<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
</head>
<body>
	<form action="UpdateAccountC" name="regForm" method="post">
	
	<table id="reg">
		<tr>
			<td colspan="2" id="title">회원 정보</td>
		</tr>
		<tr>
			<td class="regTd1">ID</td>
			<td class="regTd2">${sessionScope.accountInfo.m_id }</td>
		</tr>
		<tr>
			<td class="regTd1">PW</td>
			<td class="regTd2"><input name="pw3"><input name="pw" type="hidden" value="${sessionScope.accountInfo.m_pw }"></td>
		</tr>
		<tr>
			<td class="regTd1">이름</td>
			<td class="regTd2"><input name="name" value="${sessionScope.accountInfo.m_name }"> </td>
		</tr>
		<tr>
			<td class="regTd1">성별</td>
			<td class="regTd2">${sessionScope.accountInfo.m_gender }</td>
		</tr>
		<tr>
			<td class="regTd1">생일</td>
			<td class="regTd2"><select name="birth1">
					<c:forEach var="i" begin="1900" end="2013">
					<option value="${i }" <c:if test="${i == br[0] }">selected</c:if>> ${i }</option>
					</c:forEach>
				</select>년&nbsp;
				<select name="birth2">
					<c:forEach var="i" begin="1" end="12">
					<option value="${i }" <c:if test="${i == br[1] }">selected</c:if>> ${i }</option>
					</c:forEach>
				</select>월
				<select name="birth3">
					<c:forEach var="i" begin="1" end="31">
					<option value="${i }" <c:if test="${i == br[2] }">selected</c:if>> ${i }</option>
					</c:forEach>
				</select>일<br>
			</td>
		</tr>
		<tr>
			<td class="regTd1">이메일</td>
			<td class="regTd2">
				<input type="text" name="email1" value="${em[0] }">@<input type="text" name="email2" id="email2" value="${em[1] }" readOnly>&nbsp;
				<select name="email3" onchange="checkMail(regForm.email3.options[this.selectedIndex].value)">
					<option value="no">이메일을입력하세요.</option>
					<option value="naver.com">naver.com</option>
					<option value="daum.net">daum.net</option>
					<option value="gmail.com">gmail.com</option>
					<option value="hanmail.net">hanmail.net</option>
					<option value="input">직접입력</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="regTd1">핸드폰 번호</td>
			<td class="regTd2"><select name="phone1">
					<option value="010">010</option>
					<option value="011">011</option>
					<option value="017">017</option>
					<option value="018">018</option>
					<option value="019">019</option>
				</select> - 
				<input type="text" name="phone2" size="5" maxlength="4" value="${ph[1] }"> - 
				<input type="text" name="phone3" size="5" maxlength="4" value="${ph[2] }">
		</tr>
		<tr>
			<td class="regTd1">지역</td>
			<td class="regTd2">
				<select name="sido" id="sido"><option value="${ar[0] }">-시도-</option></select>
				<select name="gugun" id="gugun"><option value="${ar[1] }">-구군-</option></select>
				<select name="dong" id="dong"><option value="${ar[2] }">-동-</option></select>
				<script>sojaeji('${ar[0] }', '${ar[1] }', '${ar[2] }');</script>
			</td>
		</tr>
		<tr>
				<td class="regTd1">비밀번호 확인 질문1</td>
				<td class="regTd2"><select name="q1">
					<option value="A">자신의 보물 제1호는?</option>
					<option value="B">가장 기억에 남는 선생님 성함은?</option>
					<option value="C">자신의 인생 좌우명은?</option>
				</select>
				<input type="text" name="a1" value="${qa1[1] }" size="20" maxlength="20">
				</td>
			</tr>
			<tr>
				<td class="regTd1">비밀번호 확인 질문2</td>
				<td class="regTd2"><select name="q2">
					<option value="A">인상깊게 읽은 책 이름은?</option>
					<option value="B">받았던 선물 중 기억에 남는 독특한 선물은?</option>
					<option value="C">다시 태어나면 되고 싶은 것은?</option>
				</select>
				<input type="text" name="a2" value="${qa2[1] }" size="20" maxlength="20">
				</td>
			</tr>
		<tr>
			<td colspan="2"><button class="loginBtn" name="id" value="${sessionScope.accountInfo.m_id }">수정</button></td>
		</tr>
	</table>
	</form>
</body>
</html>