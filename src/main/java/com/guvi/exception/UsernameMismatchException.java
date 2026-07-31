package com.guvi.exception;

public class UsernameMismatchException extends RuntimeException {
    public UsernameMismatchException(String message) {
    	super(message); 
    }
}
