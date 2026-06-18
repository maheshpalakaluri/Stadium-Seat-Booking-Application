package com.guvi.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.guvi.dto.RegisterDto;
import com.guvi.entity.Role;
import com.guvi.entity.RolePK;
import com.guvi.entity.User;
import com.guvi.repo.RoleRepo;
import com.guvi.repo.UserRepo;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
	
//	@Override
//	public boolean register(@Valid RegisterDto dto) {
//		User user=new User();
//		user.setUserName(dto.getUserName());
//		user.setPassword(passwordEncoder.encode(dto.getPassword()));
//		user.setEmail(dto.getEmail());
//		urepo.save(user);
//		return true;
//	}
	
	@Override
	public boolean register(RegisterDto dto) {
		User user=new User();
		user.setUserName(dto.getUserName());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setEnabled(Boolean.FALSE);
		user.setVerificationToken(UUID.randomUUID().toString());
		user.setEmail(dto.getEmail());
		userrepo.save(user);
		String verificationLink =
		        "http://localhost:8080/api/auth/verify?token="
		                + user.getVerificationToken();

		String body =
		        "Welcome to StadiumBook!\n\n"
		        + "Please verify your email by clicking:\n"
		        + verificationLink;

		emailService.sendEmail(
		        user.getEmail(),
		        "Verify your Email",
		        body
		);
		
		Role role=new Role();
		RolePK key=new RolePK();
		key.setUserName(dto.getUserName());
		key.setRoleName("ROLE_USER");
		role.setKey(key);
		rolerepo.save(role);
		return true;
	}

}
