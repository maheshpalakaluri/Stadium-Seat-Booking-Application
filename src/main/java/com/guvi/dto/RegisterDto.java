package com.guvi.dto;
import com.guvi.validators.annotation.Password;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterDto {
	
	@NotBlank
	private String userName;
	
	@Email
	private String email;
	
	@NotBlank
	@Password
	private String password;
	
	public RegisterDto(String userName, String password,String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.email=email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
	
	
	
}
