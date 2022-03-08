//일반적으로 하는 유효성 검사들

/*
function isEmpty(input) {
	if(input.value == "") {
		return true;
	} else {
		return false;
	}
}
*/

function isEmpty(input) {
	return !input.value;
}

//<input>과 글자 수를 넣으면 
//그 글자수보다 적으면 true, 아니면 false

function lessThan(input, length) {
	return input.value.length < length;
}

// 한글/특수문자 들어있으면 true, 없으면 false
function containKR(input) {
	let ok = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890@_.";
	
	for(let i=0; i<input.value.length; i++) {
		if(ok.indexOf(input.value[i]) == -1) {
			return true;
		}
	}
}

//Test
//<input> x 2 넣으면
//내용이 다르면 true, 같으면 false

function notEquals(input1, input2) {
	return input1.value != input2.value;
}

//<input>, 문자열 세트
//그 문자열 세트가 포함 안되어있으면 true 들어있으면 false
function notContains(input, set) {
	//input : 123qwe
	//set : 1234567890 > 숫자를 반드시 포함시키고 싶음
	for(let i=0; i<set.length; i++) {
		if(input.value.indexOf(set[i]) != -1) {
			return false;
		}
	}
	return true;
	
}

//<input>을 넣어서 숫자가 아니면 true, 숫자면 false
function isNotNumber(input) {
	return isNaN(input.value);
}

//<input>, 확장자를 넣게
//최대한 넓은 범위로 활용하려 하는데
//사이트마다 다를수 있음
//설정한 확장자가 아니면 false 맞으면 true
function isNotType(input, type) {
	type = '.' +type;
	return input.value.indexOf(type) == -1
}
