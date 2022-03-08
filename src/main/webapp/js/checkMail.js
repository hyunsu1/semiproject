function checkMail(value){

	let varEmail2 = regForm.email2;

	if( value == 'no'){

		varEmail2.value = '';

		varEmail2.readOnly=true;

		return;

	} else if(value == 'input' ){

		varEmail2.value = '';

		varEmail2.readOnly=false;

		varEmail2.focus();

		return;

	} else {

		varEmail2.value = value;

		varEmail2.readOnly = true;

	}

}