package com.genesys.UserManagementSystem.repository;

import com.genesys.UserManagementSystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Syed Ali.
 * @createdAt 30/01/2022, Sunday, 14:30
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
}
