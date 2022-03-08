function createMoimValid() {
	let sido = document.createMoim.sido;
	let gugun = document.createMoim.gugun;
	let dong = document.createMoim.dong;
	let name = document.createMoim.name;
	let introduce = document.createMoim.introduce;
	
	if(lessThan(name, 3)) {
		alert('모임이름을 다시 입력해주세요');
		name.focus();
		
		return false;
	}
	
	if(isEmpty(sido) || isEmpty(gugun) || isEmpty(dong)) {
		alert('지역을 선택해주세요');
		
		return false;
	}	
	
	
	if(lessThan(introduce, 10)) {
		alert('모임설명을 다시 입력해주세요');
		introduce.focus();
		
		return false;
	}
}








