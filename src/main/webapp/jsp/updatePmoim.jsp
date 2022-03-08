<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유료모임 수정</title>
</head>
<body>


	<form action="UpdatePmoimC" name="createMoim" method="post" enctype="multipart/form-data">


		<table id="cbmUpdateTbl">
			<tr>
				<td class="cbmUpdateTd1">모임이름</td>
				<td class="cbmUpdateTd2">${moims.name }</td>
				<input type="hidden" name="id" value="${sessionScope.accountInfo.m_id }">
				<input type="hidden" name="name" value="${moims.name }">
			</tr>
			
			<tr>
				<td class="cbmUpdateTd1">관심사 선택</td>
				<td class="cbmUpdateTd2">
					<select name="interest">
						<option value="A" <c:if test="${moims.interest eq '여행'}">selected</c:if>>여행</option>
						<option value="B" <c:if test="${moims.interest eq '운동'}">selected</c:if>>운동</option>
						<option value="C" <c:if test="${moims.interest eq '게임'}">selected</c:if>>게임</option>
						<option value="D" <c:if test="${moims.interest eq '음악'}">selected</c:if>>음악</option>
						<option value="E" <c:if test="${moims.interest eq '스터디'}">selected</c:if>>스터디</option>
						<option value="F" <c:if test="${moims.interest eq '사진'}">selected</c:if>>사진</option>
						<option value="G" <c:if test="${moims.interest eq '반려동물'}">selected</c:if>>반려동물</option>
						<option value="H" <c:if test="${moims.interest eq '자유주제'}">selected</c:if>>자유주제</option>
				    </select>
				</td>
			</tr>
			
			<tr>
				<td class="cbmUpdateTd1">지역 선택</td>
				<td class="cbmUpdateTd2">
					<select name="sido" id="sido"><option>시도</option></select>
					<select name="gugun" id="gugun"><option>군구</option></select>
					<select name="dong" id="dong"><option>동</option></select>
				
					<script>
					sojaeji('${rg[0] }', '${rg[1] }', '${rg[2] }');
					</script>
					
				</td>
			</tr>
			
			<tr>
				<td class="cbmUpdateTd1">인원수 설정</td>
				<td class="cbmUpdateTd2">
					<select name="maxMembers">
						<option value="10" <c:if test="${moims.maxMembers eq 10}">selected</c:if>>10명</option>
						<option value="30" <c:if test="${moims.maxMembers eq 30}">selected</c:if>>30명</option>
	 					<option value="50" <c:if test="${moims.maxMembers eq 50}">selected</c:if>>50명</option>
						<option value="100" <c:if test="${moims.maxMembers eq 100}">selected</c:if>>100명</option>
						<option value="9999" <c:if test="${moims.maxMembers eq 9999}">selected</c:if>>제한없음</option>
				    </select>
				</td>
			</tr>
			
			<tr>
				<td class="cbmUpdateTd1">모임 설명</td>
				<td class="cbmUpdateTd2">
					<textArea name="introduce">${moims.introduce }</textArea>
				</td>
			</tr>
			
			<tr>
				<td class="cbmUpdateTd1">모임 사진</td>
				<td class="cbmUpdateTd2"> <input name="image" type="hidden" value="${moims.image }">
					 <input name="image2" type="file">
				
				</td>
			</tr>
			
			<tr>
            	<td class="cbmUpdateTd1" align="left">가입비</td>
            	<td class="cbmUpdateTd2" align="left"><input name="fee" type="hidden" value="${moims.fee }">
            		<input name="fee2" type="number" step="1000">
            	</td>
         	</tr>
			
			<tr>
				<td colspan="2" align="center"><button class="cbmUpdateBtn">수정</button></td>
			<tr>
		</table>
	</form>
</body>
</html>