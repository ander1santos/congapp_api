package org.cong.congapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RegistroNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public RegistroNotFoundException(String mensagem) {
		super(mensagem);
	}

}
