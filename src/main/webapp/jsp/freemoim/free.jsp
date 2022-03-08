<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>무료모임</title>
<link rel="stylesheet" href="css/moimList.css">
</head>
<body>

	<div style="margin-top: 50px;">
	<a href="FreeC?" id="aListTag">전체</a>
	<a href="TravelC?interest=여행" id="aListTag">여행</a>
	<a href="ExerciseC?interest=운동" id="aListTag">운동</a>
	<a href="GameC?interest=게임" id="aListTag">게임</a>
	<a href="MusicC?interest=음악" id="aListTag">음악</a>
	<a href="StudyC?interest=스터디" id="aListTag">스터디</a>
	<a href="PhotoC?interest=사진" id="aListTag">사진</a>
	<a href="PetC?interest=반려동물" id="aListTag">반려동물</a>
	<a href="FreetalkC?interest=자유주제" id="aListTag">자유주제</a>   
	</div>
	
	<jsp:include page="${moimListPage }"></jsp:include>	

	<p>
	<a href="CreateMoimC" id="a">모임 개설</a>
</body>
</html>