function dp_menu() {
	let click = document.getElementById("drop-content");
	if (click.style.display === "none") {
		click.style.display = "block";
	} else {
		click.style.display = "none";
	}
}

function DelBoard(no, moimName) {
	let ok = confirm('정말 삭제하시겠습니까?');

	if (ok) {
		location.href = 'B_DelC?no=' + no + '&moimName=' + moimName;
	}
}

function UpdateBoard(no, moimName) {
	let ok = confirm('수정하시겠습니까?');

	if (ok) {
		location.href = 'B_UpdateC?no=' + no + '&moimName=' + moimName;
	}
}

function UpdatePhotoBoard(no, moimName) {
	let ok = confirm('수정하시겠습니까?');

	if (ok) {
		location.href = 'PB_UpdateC?no=' + no + '&moimName=' + moimName;
	}
}
/*
function DelPhotoBoard(num) {
	let right = confirm('정말 삭제하시겠습니까?');

	if (right) {
		location.href = 'PB_DelC?no=' + num;
	}
	
	let form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "PB_DelC");
	document.body.appendChild(form);
	
	let no = document.createElement("input");
	no.setAttribute("type", "hidden");
	no.setAttribute("name", "num");
	no.setAttribute("value", num);
	form.appendChild(no);
	
	form.submit();
}
*/

function DelPhotoBoard(no, moimName) {
	let ok = confirm('정말 삭제하시겠습니까?');

	if (ok) {
		location.href = 'PB_DelC?no=' + no + '&moimName=' + moimName;
	}
}

function search() {
	let searchInput = document.getElementById('search');
	let searchVal = searchInput.value;

	location.href = 'B_SearchC?search=' + searchVal;
}

function thumbcheck() {
	let pInput = document.pRegForm.p_thumb;
	if (isEmpty(pInput) || isNotType(pInput, "jpg") && isNotType(pInput, "png")) {
		alert('jpg, png 파일만 가능합니다');

		fInput.value = "";
		fInput.focus();

		return false;
	}
}