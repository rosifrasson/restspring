package br.rosi.com.restspring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BankSlipNotFoundException extends RuntimeException {

	public BankSlipNotFoundException(String exception) {
		super(exception);
	}

}

