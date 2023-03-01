function validateName(name){
	const regex=/^[0-9]{10}$/;
	if(name.trim() === ' '){
		return false;
	}
	else if(!regex.test(name)){
		return false;
		
	}else{
		return true;
	}
	
}