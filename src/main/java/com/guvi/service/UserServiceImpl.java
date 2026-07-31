package com.guvi.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.guvi.dto.RegisterDto;
import com.guvi.entity.Role;
import com.guvi.entity.RolePK;
import com.guvi.entity.User;
import com.guvi.exception.EmailAlreadyRegisteredException;
import com.guvi.exception.UsernameMismatchException;
import com.guvi.repo.RoleRepo;
import com.guvi.repo.UserRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepo userrepo;

	@Autowired
	private RoleRepo rolerepo;

	@Autowired
	private EmailService emailService;

	@Override
	public boolean register(RegisterDto dto) {

		Optional<User> existingOpt = userrepo.findByEmail(dto.getEmail());
		User user;

		if (existingOpt.isPresent()) {
			user = existingOpt.get();

			if (Boolean.TRUE.equals(user.getEnabled())) {
				throw new EmailAlreadyRegisteredException(
						"Email already registered and verified.");
			}

			// Unverified stale account — don't allow username to change
			// (username is still the PK, so this can't be safely updated)
			if (!user.getUserName().equals(dto.getUserName())) {
				throw new UsernameMismatchException(
						"An unverified account with this email already exists under username '"
								+ user.getUserName()
								+ "'. Please use that username to continue, or wait for it to expire.");
			}

			user.setPassword(passwordEncoder.encode(dto.getPassword()));

		} else {
			user = new User();
			user.setUserName(dto.getUserName());
			user.setEmail(dto.getEmail());
			user.setPassword(passwordEncoder.encode(dto.getPassword()));
			user.setEnabled(Boolean.FALSE);
		}

		String token = UUID.randomUUID().toString();
		user.setVerificationToken(token);
		user.setTokenExpiry(LocalDateTime.now().plusHours(24));
		userrepo.save(user);

		String verificationLink =
				"http://localhost:8080/api/auth/verify?token=" + token;
		String body =
				"Welcome to StadiumBook!\n\n"
				+ "Please verify your email by clicking:\n"
				+ verificationLink;
		emailService.sendEmail(user.getEmail(), "Verify your Email", body);

		// Only create the Role row if it doesn't already exist
		RolePK key = new RolePK();
		key.setUserName(dto.getUserName());
		key.setRoleName("ROLE_USER");

		if (!rolerepo.existsById(key)) {
			Role role = new Role();
			role.setKey(key);
			rolerepo.save(role);
		}

		return true;
	}
	
	public boolean verifyUser(String token) {
	    Optional<User> userOpt = userrepo.findByVerificationToken(token);

	    if (userOpt.isEmpty()) {
	        return false; // token doesn't exist — either never valid, or already used/overwritten
	    }

	    User user = userOpt.get();

	    if (user.getTokenExpiry() == null || user.getTokenExpiry().isBefore(LocalDateTime.now())) {
	        return false; // expired
	    }

	    user.setEnabled(true);
	    user.setVerificationToken(null);
	    user.setTokenExpiry(null);
	    userrepo.save(user);
	    return true;
	}
}