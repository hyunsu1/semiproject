function popup(name) {
	let url = "popupRegC?name=" +name;
	let name2 = "popupReg";
	
	window.open(url, name2, "width=400, height=600, toolbar=no, status=no, location=no, status=no, scrollbar=no, menubar=no, resizable=no, left=400, right=400");
	
}

function popup2(name) {
	let url = "popupRegC2?name=" +name;
	let name2 = "popupReg";
	
	window.open(url, name2, "width=400, height=600, toolbar=no, status=no, location=no, status=no, scrollbar=no, menubar=no, resizable=no, left=400, right=400");
	
}

function popupClose() {
	self.close();
}

function applicationDetail(num) {
	let url = "ApplicationDetailC?num=" +num;
	let name = "applicationDetail";
	
	window.open(url, name, "width=400, height=600, toolbar=no, status=no, location=no, status=no, scrollbar=no, menubar=no, resizable=no, left=400, right=400");
}

function isPopup() {
	if(opener) {
		console.log(123);
		self.close();
	} else {
		console.log(1234);
		location.href='HC';
	}
}

function numberMaxLength(e){
        if(e.value.length > e.maxLength){
            e.value = e.value.slice(0, e.maxLength);
        }
    }

