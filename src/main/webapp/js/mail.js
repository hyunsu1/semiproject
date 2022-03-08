function mailSuccess() {
	let url = "mailSuccess"
	let name = "mailSuccess";
	
	window.open(url, name, "width=400, height=600, toolbar=no, status=no, location=no, status=no, scrollbar=no, menubar=no, resizable=no, left=400, right=400");
	
}

function mailFail() {
	let url = "mailFail"
	let name = "mailFail";
	
	window.open(url, name, "width=400, height=600, toolbar=no, status=no, location=no, status=no, scrollbar=no, menubar=no, resizable=no, left=400, right=400");
	
}

function mailDelete(num) {
	let ok = confirm('정말 삭제합니까?');
	
	if(ok) {
		location.href="MailDeleteC?num=" +num;
	}
}