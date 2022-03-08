function checkValue() {
	var form = documnet.regForm;
	
	if(!form.id.value){
		alert("아이디를 입력하세요.");
		return false;
	}
	
	if(form.idDuplication.value != "idCheck"){
		alert("아이디 중복체크를 해주세요.");
		return false;
	}
	
	if(!form.password.value){
		alert("비밀번호를 입력하세요.");
		return false;
	}
	
	if(form.password.value != form.passwordcheck.value){
		return false;
	}
	
	if(!form.name.value){
		alert("이름을 입력하세요.");
		return false;
	}
}

// 아이디 중복체크 화면열기
function openIdChk(){
	window.name = "parentForm";
	window.open("jsp/IdCheckForm.jsp", "chkForm", "width=500, height=300, resizable = no, scrollbars = no");
}
/*
function inputIdChk(){
	document.userInfo.idDuplication.value="idUncheck";
}
*/
// 회원가입창의 아이디 입력란의 값을 가져온다.
function pValue(){
	document.getElementById("userId").value=opener.document.userInfo.id.value;
}

// 아이디 중복체크
function idCheck(){
	var idInput = document.getElementById("userId");
		
	location.href='../checkC?id=' + idInput.value;
	
}
/*
function callback(){
	if(httpRequest.readyState == 4){
		var resultText = httpRequest.responseText;
		if(resultText == 0){
			alert("사용할 수 없는 아이디입니다.");
			document.getElementById("cancelBtn").style.visibility='visible';
			document.getElementById("useBtn").style.visibility='visible';
			document.getElementById("msg").innnerHTML = "사용 가능한 아이디입니다.";
		} else if(resultText == 1){
			document.getElementById("cancelBtn").style.visibility='hidden';
			document.getElementById("useBtn").style.visibility='visible';
			document.getElementById("msg").innerHTML = "사용 가능한 아이디입니다.";
		}
	}
}
*/
function sendCheckValue(){
	//opener.document.getElementById("idDuplication").value="idCheck";
	
	if(document.getElementById("useBtn").value == 0){
	window.opener.document.getElementById("idd").value = document.getElementById("userId").value;
	self.close();
	}else{
		
		document.getElementById("userId").focus();
		document.getElementById("userId").value = "";
	}
	/*
	if(opener != null){
		opener.chkForm = null;
		self.close();
	}
	*/
}

// 회원탈퇴
function deleteAccount(i){

	let ok = confirm('정말 탈퇴 하시겠습니까?')
	
	if(ok){
		location.href='DeleteAccountC?id=' + i;
	}
}


// 내가 만든 유료모임 수정
function moimUpdate(i,j){

	let ok = confirm('수정 하시겠습니까?')
	
	if(ok){
		location.href='UpdatePmoimC?name=' +i + '&temp2=' + j;
	}
}

// 내가 만든 유료모임 삭제
function moimDelete(i){
	
	let ok = confirm('정말 모임을 삭제 하시겠습니까?')
	
	if(ok){
		location.href='DeletePmoimC?pname=' + i;
	}
}

// 내가 만든 무료모임 수정
function moimUpdate2(i,j){
	
	let ok = confirm('수정 하시겠습니까?')
	
	if(ok){
		location.href='UpdateFmoimC?name=' + i + '&temp1=' + j;
	}
}

// 내가 만든 무료모임 삭제
function moimDelete2(i){
	
	let ok = confirm('정말 모임을 삭제 하시겠습니까?')
	
	if(ok){
		location.href='DeleteFmoimC?fname=' + i;
	}
}