package com.nn.snaplife.exceptions;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nn.snaplife.beans.LoginResponse;

@ControllerAdvice
public class ExceptionAdvicer {

	@ResponseBody
	@ExceptionHandler(PasswordViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String passwordValidationHandler(PasswordViolationException e) {
		return e.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String constraintValidationHandler(ConstraintViolationException e) {
		return e.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String generalExceptionHandler(Exception e) {
		return e.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	LoginResponse badCredentialsExceptionHandler(Exception e) {
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setSuccess(false);
		loginResponse.setMessage(e.getMessage());
		loginResponse.setUser(null);
		return loginResponse;
	}
}
