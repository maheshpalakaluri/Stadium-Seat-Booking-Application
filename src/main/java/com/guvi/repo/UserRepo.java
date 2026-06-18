package com.guvi.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.guvi.entity.User;
@Repository
public interface UserRepo extends JpaRepository<User,String> {
	Optional<User> findByVerificationToken(String token);
}
