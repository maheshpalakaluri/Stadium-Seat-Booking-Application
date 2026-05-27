package com.guvi.validators.annotation;

import java.lang.annotation.ElementType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.guvi.validators.PasswordValidator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=PasswordValidator.class)
public @interface Password {

	String message() default "Password must contain alphabet, number and special character and min of 8 characters!!!";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default{};
}
