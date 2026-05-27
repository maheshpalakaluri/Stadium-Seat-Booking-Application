package com.guvi.exception;

import java.util.*;

import org.springframework.validation.FieldError;

public class ValidationException extends RuntimeException{

	public ValidationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ValidationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public ValidationException(List<FieldError> errors) {
		super();
		this.errors=errors;
	}


	private List<FieldError> errors;

	public List<FieldError> getErrors() {
		return errors;
	}

	public void setErrors(List<FieldError> errors) {
		this.errors = errors;
	}
	
	
}
