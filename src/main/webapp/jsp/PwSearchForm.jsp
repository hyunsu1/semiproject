<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/search.js"></script>
</head>
<body>
	<form name="pwfindscreen" method="post">
		<div>
			<h3>비밀번호 찾기</h3>
		</div>
		<section class = "form-search">
			<div class = "find-id">
				<label>아이디</label>
				<input type="text" name="member_id" class = "btn-name" placeholder = "아이디 입력"><hr>
			</div>
			<div class = "showQnA">
				<select name="q1">
					<option value="A">자신의 보물 제1호는?</option>
					<option value="B">가장 기억에 남는 선생님 성함은?</option>
					<option value="C">자신의 인생 좌우명은?</option>
				</select>
				<input type="text" name="a1" value="${qa1[1] }" size="20" maxlength="20"><p>
				<select name="q2">
					<option value="A">인상깊게 읽은 책 이름은?</option>
					<option value="B">받았던 선물 중 기억에 남는 독특한 선물은?</option>
					<option value="C">다시 태어나면 되고 싶은 것은?</option>
				</select>
				<input type="text" name="a2" value="${qa2[1] }" size="20" maxlength="20">
			</div>
		</section>
		<div class="btnSearch">
			<input type="button" name="enter" value="찾기" onclick="pw_searchGo()">
			<input type="button" name="cancle" value="취소" onclick="window.close()">
			
		</div>
	</form>
</body>
</html>