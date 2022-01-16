package br.com.finance.cdd.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RequestNotFoundException extends RuntimeException {

	/**
	* 
	*/
	private static final long serialVersionUID = 4985653617425536806L;

	public RequestNotFoundException(String message) {
		super(message);
	}
}
