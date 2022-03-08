<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유료모임</title>
</head>
<body>

	<div style="margin-top: 50px;">
	<a href="PaidC?" id="aListTag">전체</a>
	<a href="TravelC2?interest=여행" id="aListTag">여행</a>
	<a href="ExerciseC2?interest=운동" id="aListTag">운동</a>
	<a href="GameC2?interest=게임" id="aListTag">게임</a>
	<a href="MusicC2?interest=음악" id="aListTag">음악</a>
	<a href="StudyC2?interest=스터디" id="aListTag">스터디</a>
	<a href="PhotoC2?interest=사진" id="aListTag">사진</a>
	<a href="PetC2?interest=반려동물" id="aListTag">반려동물</a>
	<a href="FreetalkC2?interest=자유주제" id="aListTag">자유주제</a>   
	</div>

	<jsp:include page="${moimListPage }"></jsp:include>	

	<p>
	<a href="CreateMoimC2" id="a">모임 개설</a>
</body>
</html>