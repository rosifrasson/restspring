package br.rosi.com.restspring.exceptions;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.rosi.com.restspring.utils.MessageDetails;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		MessageDetails messageDetails = new MessageDetails("Invalid bankslip provided.The possible reasons are: A field of the provided bankslip was null or with invalid values");
		return new ResponseEntity<>(messageDetails, HttpStatus.UNPROCESSABLE_ENTITY);
	} 
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		MessageDetails messageDetails = new MessageDetails("Invalid bankslip provided.The possible reasons are: A field of the provided bankslip was null or with invalid values");
		return new ResponseEntity<>(messageDetails, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		MessageDetails messageDetails = new MessageDetails("Invalid id provided - it must be a valid UUID");
		return new ResponseEntity<>(messageDetails, HttpStatus.BAD_REQUEST);
	}
	

	
}
