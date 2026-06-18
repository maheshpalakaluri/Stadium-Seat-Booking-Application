package com.guvi.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guvi.dto.RegisterDto;
import com.guvi.dto.TokenDto;
import com.guvi.entity.User;
import com.guvi.exception.ValidationException;
import com.guvi.repo.UserRepo;
import com.guvi.security.AuthRequest;
import com.guvi.security.JwtService;
import com.guvi.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = { "http://localhost:5173" })
public class AuthController {

	@Autowired
	private UserService uservice;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserRepo userrepo;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterDto dto, BindingResult br) {
		System.out.println("REGISTER API HIT");
		if (br.hasErrors()) {
			throw new ValidationException(br.getFieldErrors());
		}
		uservice.register(dto);
		return new ResponseEntity<>("Registered successfully", HttpStatus.CREATED);
	}

	@GetMapping("/verify")
	public ResponseEntity<String> verifyEmail(@RequestParam String token) {

		User user = userrepo.findByVerificationToken(token).orElseThrow(() -> new RuntimeException("Invalid Token"));

		user.setEnabled(true);
		user.setVerificationToken(null);

		userrepo.save(user);

		return ResponseEntity.ok("Email verified successfully!");
	}

	@PostMapping("/generateToken")
	public TokenDto authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

			List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

			String token = jwtService.generateToken(authRequest.getUsername(), roles);

			TokenDto dto = new TokenDto();
			dto.setToken(token);
			dto.setRoles(roles.toArray(String[]::new));
			dto.setUserName(authRequest.getUsername());

			return dto;
		} catch (DisabledException ex) {
			throw new RuntimeException("Please verify your email before logging in.");
		} catch (BadCredentialsException ex) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
	}
}
