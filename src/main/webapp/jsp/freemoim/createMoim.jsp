<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>무료모임 개설</title>
<link rel="stylesheet" href="css/createMoim.css">
</head>
<body>
	<h4 align="center">해피프렌드로 자기개발과 취미를 시작해보세요</h4>
	<div style="margin-bottom: 70px;">
	<form action="CreateMoimC" name="createMoim" method="post" enctype="multipart/form-data" onsubmit="return createMoimValid();">
		<table id="Tb1">
			<tr class="warning">
				<td class="crtMoimTd1" align="left">모임이름</td>
				<td class="crtMoimTd2" ><input name="name" placeholder="3글자 이상" id="crtMoimS"></td>
			</tr>
			
			<tr class="warning">
				<td class="crtMoimTd1" align="left">관심사</td>
				<td class="crtMoimTd2"> 
					<div align="left">
					<select name="interest" id="crtMoimS">
						<option value="여행">여행</option>
						<option value="운동">운동</option>
						<option value="게임">게임</option>
						<option value="음악">음악</option>
						<option value="스터디">스터디</option>
						<option value="사진">사진</option>
						<option value="반려동물">반려동물</option>
						<option value="자유주제">자유주제</option>
				    </select>
				    </div>
				</td>
			</tr>
			
			<tr class="warning">
				<td class="crtMoimTd1" align="left">지역선택</td>
				<td class="crtMoimTd2">
					<div align="left">
					<select name="sido" id="sido"><option value="">시도</option></select>
					<select name="gugun" id="gugun" ><option value="">군구</option></select>
					<select name="dong" id="dong"><option value="">동</option></select>
					</div>
					<script>
					sojaeji('', '', '');
					</script>
				</td>
			</tr>
			
			<tr class="warning">
				<td class="crtMoimTd1" align="left">인원수</td>
				<td class="crtMoimTd2" align="left">
					<select name="members" id="crtMoimS">
						<option value="10">10명</option>
						<option value="30">30명</option>
						<option value="50">50명</option>
						<option value="100">100명</option>
						<option value="9999">제한없음</option>
				    </select>
				</td>
			</tr>
			
			<tr class="warning">
				<td class="crtMoimTd1" align="left">모임설명</td>
				<td class="crtMoimTd2" align="left">
					<textArea name="introduce" placeholder="10글자 이상" style="width: 60%; height: 130px; resize: none;"></textArea>
				</td>
			</tr>
			
			<tr class="warning">
				<td class="crtMoimTd1" align="left">모임사진</td>
				<td class="crtMoimImg" align="left"> <input name="image" type="file"></td>
			</tr>
		
			<tr class="warning">
				<td colspan="2" align="center"><button id="crtMoimBtn">모임 만들기</button></td>
			<tr>
			
		</table>
	</form>
	</div>
</body>
</html>