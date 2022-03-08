<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/checkValue.js"></script>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $(function(){
    	
        $("#btn").click(function(){
					console.log('zzz');					
            $.ajax({
                url: "./id_check.jsp", 
                data:{userid:document.frm.userid.value},
                dataType:'json',
                success: function(data) {
					console.log(data);					
					console.log(data.asd);					
                    if(data.asd == 1){
                        document.getElementById("ck").innerText="사용불가능 아이디";
                        $("#useBtn").val(data.asd);
                    } else{
                        document.getElementById("ck").innerText="사용가능 아이디";
                        $("#useBtn").val(data.asd);
                    }
                },
            });
        });
    });

</script>
<body>

    <form name="frm" method="post" action="member_in_ok.jsp">

        아이디<input name="userid" id="userId">

        <input type="button" id="btn" value="아이디 중복 체크">

        <span id="ck"></span>

        <p>

       <div id="msg">
			<input id="cancelBtn" type="button" value="취소" onclick="window.close()"><br>
			<button id="useBtn" type="button" onclick="sendCheckValue()">사용하기</button>
		</div>

    </form>

</body>
<%-- 
<body onload="pValue()">
<c:if test="${rr == 2 }">
<script type="text/javascript">
let ok = confirm('가입 불가합니다.');
if(ok){
	window.history.back();
}else{
	window.close();
}
</script>
</c:if>
	<div id="wrap">
		<br>
		<h3>아이디 중복확인 </h3>
		<h2 style="color: red;" id="kkk">${r }</h2>
		<div id="chk">
			아이디 : <input type="text" name="idinput" id="userId">
			<button onclick="idCheck()">중복확인</button>
		<div id="msg"></div><br>
			<input id="cancelBtn" type="button" value="취소" onclick="window.close()"><br>
			<input id="useBtn" type="button" value="사용하기" onclick="sendCheckValue()">
		</div>
	</div> 
</body>
	--%>
</html>