function id_search() {
	window.name = "parentForm";
	window.open("jsp/IdSearchForm.jsp", "searchForm", "width=500, height=300, resizable = no, scrollbars = no");
	
	var frm = document.idfindscreen;
	
	if(frm.member_name.value.length < 1){
	alert("이름을 입력해주세요");
	return;
	}
	
	if(frm.member_phone.value.length != 13){
		alert("핸드폰번호를 정확하게 입력해주세요");
		return;
	}
}


function id_searchGo(){
	let name = document.idfindscreen.member_name;
	let phone1 = document.idfindscreen.phone1;
	let phone2 = document.idfindscreen.phone2;
	let phone3 = document.idfindscreen.phone3;
	
	if(name.value.length < 1){
		alert('이름을 입력해주세요');
		name.focus();
	return;
	}
	if(phone2.value.length < 1){
		alert('핸드폰번호를 정확하게 입력해주세요');
		phone2.focus();
	return;
	}
	if(phone3.value.length < 1){
		alert('핸드폰번호를 정확하게 입력해주세요')
		phone3.focus();
	return;
	}
	
	location.href='../FindId?name='+name.value+"&phone1=" + phone1.value+"&phone2=" + phone2.value+"&phone3=" + phone3.value;
}


function pw_search(){
	window.name = "parentForm";
	window.open("jsp/PwSearchForm.jsp", "searchForm", "width=500, height=300, resizable = no, scrollbars = no");
	
	var frm = document.pwfindscreen;
	
	if(frm.member_id.value.length < 1){
	alert("아이디를 입력해주세요");
	return;
	}
	
	if(frm.member_phone.value.length != 13){
		alert("핸드폰번호를 정확하게 입력해주세요");
		return;
	}
}

function pw_searchGo(){
	let id = document.pwfindscreen.member_id;
	let q1 = document.pwfindscreen.q1;
	let a1 = document.pwfindscreen.a1;
	let q2 = document.pwfindscreen.q2;
	let a2 = document.pwfindscreen.a2;
	
	location.href='../FindPw?id='+id.value+"&q1=" + q1.value + "&a1=" + a1.value + "&q2=" + q2.value + "&a2=" + a2.value;
}



function pw_searchGo2(){
	let id = document.idfindscreen.member_id;
	let email1 = document.idfindscreen.email1;
	let email2 = document.idfindscreen.email2;
	let email3 = document.idfindscreen.email3;
	
	if(id.value.length < 1){
		alert('등록되지 않은 아이디입니다.');
		id.focus();
	return;
	}
	if(emial1.value.length < 1){
		alert('등록되지 않은 이메일입니다.');
		email1.focus();
	return;
	} else if(emial2.value.length < 1){
		alert('등록되지 않은 이메일입니다.');
		email2.focus();
	} else{
		alert('등록되지 않은 이메일입니다.');
		email3.focus();
	}
	
	location.href='../FindPw?id='+id.value+"&email1=" + email1.value+"&email2=" + email2.value+"&email3=" + email3.value;
}