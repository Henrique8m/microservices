package com.rodrigues.hruser.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rodrigues.hruser.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query
	Optional<User> findByEmail(String email);

}
