<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div id="boardMenu">
		<a href='InfoC?moimName=${param.moimName }'>모임 정보</a>
		<a href='BoardC?moimName=${param.moimName }'>게시판</a>
    	<a href='PhotoBoardC?moimName=${param.moimName }'>사진첩</a>
		<a href='MemberListC?moimName=${param.moimName }'>채팅</a>
	</div>
	
   <table id="myMoimTbl">
      <tr>   
         <td id="title" colspan="2">모임 정보</td>
      </tr>
      
      <tr>   
         <td id="myMoimTd1">사진</td>
         <td id="myMoimTd2"><img height="100px" width="100px" src="file/${moim.image }"></td>
      </tr>
      
      <tr>   
         <td id="myMoimTd1">이름</td>
         <td id="myMoimTd2">${moim.name }</td>
      </tr>
      
      <tr>   
         <td id="myMoimTd1">설명</td>
         <td id="myMoimTd2">${moim.introduce }</td>
      </tr>
      
      <tr>   
         <td id="myMoimTd1">관심사</td>
         <td id="myMoimTd2">${moim.interest }</td>
      </tr>
      
      <tr>   
         <td id="myMoimTd1">지역</td>
         <td id="myMoimTd2">${moim.region }</td>
      </tr>
      
      <tr>   
         <td id="myMoimTd1">현재 인원</td>
         <td id="myMoimTd2">${moim.currentMembers }</td>
      </tr>
      
      <tr>   
         <td id="myMoimTd1">최대 인원</td>
         <td id="myMoimTd2">${moim.maxMembers }</td>
      </tr>
      
      <tr>   
         <td id="myMoimTd1">개설날짜</td>
         <td id="myMoimTd2">${moim.createDate }</td>
      </tr>
   </table>

		
</body>
</html>