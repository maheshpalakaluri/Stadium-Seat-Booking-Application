package com.guvi.validators;

import com.guvi.validators.annotation.Password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value==null||value.trim().length()==0)return false;
		if(value.matches("[a-zA-Z0-9$@%-_#!]{8,}")) return true;
		return false;
	}

	
}
