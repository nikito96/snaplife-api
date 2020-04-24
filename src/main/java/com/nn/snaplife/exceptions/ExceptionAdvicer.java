package com.nn.snaplife.exceptions;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nn.snaplife.beans.responses.RequestResponse;
import com.nn.snaplife.beans.responses.Response;

@ControllerAdvice
public class ExceptionAdvicer {

	@ResponseBody
	@ExceptionHandler(PasswordViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	Response passwordValidationHandler(PasswordViolationException e) {
		RequestResponse response = new RequestResponse();
		response.setSuccess(false);
		response.setMessage(e.getMessage());
		return response;
	}
	
	@ResponseBody
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	Response constraintValidationHandler(ConstraintViolationException e) {
		RequestResponse response = new RequestResponse();
		response.setSuccess(false);
		response.setMessage(e.getMessage());;
		return response;
	}
	
	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	Response generalExceptionHandler(Exception e) {
		RequestResponse response = new RequestResponse();
		response.setSuccess(false);
		response.setMessage(e.getMessage());
		return response;
	}
	
	@ResponseBody
	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	Response badCredentialsExceptionHandler(Exception e) {
		RequestResponse response = new RequestResponse();
		response.setSuccess(false);
		response.setMessage(e.getMessage());
		return response;
	}
}
