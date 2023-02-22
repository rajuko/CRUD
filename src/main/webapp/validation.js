function validateName(name){
	const regex=/^[a-zA-Z\s]+$/;
	if(name.trim() === ' '){
		return false;
	}
	else if(!regex.test(name)){
		return false;
		
	}else{
		return true;
	}
	
}