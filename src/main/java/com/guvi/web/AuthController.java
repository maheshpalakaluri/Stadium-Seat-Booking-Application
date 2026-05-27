package com.guvi.web;
 


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guvi.dto.RegisterDto;
import com.guvi.dto.TokenDto;
import com.guvi.exception.ValidationException;
import com.guvi.security.AuthRequest;
import com.guvi.security.JwtService;
import com.guvi.service.UserService;

import jakarta.validation.Valid;
 
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins= {"http://localhost:5173"})
public class AuthController {
	
	@Autowired
	private UserService uservice;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtService jwtService;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterDto dto,BindingResult br){
		if(br.hasErrors()) {
			throw new ValidationException(br.getFieldErrors());
		}
		uservice.register(dto);
		return new ResponseEntity<>("Registered successfully",HttpStatus.CREATED);
	}
	
	
	
	@PostMapping("/generateToken")
	public TokenDto authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
	    Authentication authentication = authManager.authenticate(
	        new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
	    );
	    if (authentication.isAuthenticated()) {
	    	List<String> roles = authentication.getAuthorities()
	    			.stream()
	    			//.map(e->e.getAuthority())
	    			.map(GrantedAuthority::getAuthority)
	    			.toList();
	    	String token = jwtService.generateToken(authRequest.getUsername(),roles);
	    	TokenDto dto = new TokenDto();
	    	dto.setToken(token);
	    	dto.setRoles(roles.toArray(String[]::new));
	    	dto.setUserName(authRequest.getUsername());
	    	return dto;
//	        return jwtService.generateToken(authRequest.getUsername(),roles);
	    } else {
	        throw new UsernameNotFoundException("Invalid user request!");
	    }
	}

 
}