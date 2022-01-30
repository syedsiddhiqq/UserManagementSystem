package com.genesys.UserManagementSystem.controller;

import com.genesys.UserManagementSystem.beans.UserRequest;
import com.genesys.UserManagementSystem.exceptions.InvalidPasswordException;
import com.genesys.UserManagementSystem.exceptions.UserAlreadyFoundException;
import com.genesys.UserManagementSystem.exceptions.UserNotFoundException;
import com.genesys.UserManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Syed Ali.
 * @createdAt 30/01/2022, Sunday, 14:20
 */
@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "create")
	public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
		try {
			return ResponseEntity.ok(userService.createUser(userRequest));
		} catch (UserAlreadyFoundException ex) {
			return ResponseEntity.status(HttpStatus.OK).body(ex.getMessage());
		}
	}

	@GetMapping(value = "get/{userId}")
	public ResponseEntity<?> getUser(@PathVariable Integer userId) {
		try {
			return ResponseEntity.ok(userService.getUser(userId));
		} catch (UserNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.OK).body(ex.getMessage());
		}
	}

	@DeleteMapping(value = "delete/")
	public ResponseEntity<?> deleteUser(@RequestBody UserRequest userRequest) {
		try {
			return ResponseEntity.ok(userService.deleteUser(userRequest));
		} catch (UserNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.OK).body(ex.getMessage());
		}
	}

	@PostMapping(value = "update")
	public ResponseEntity<?> updateUser(@RequestBody UserRequest userRequest) {
		try {
			return ResponseEntity.ok(userService.updateUser(userRequest));
		} catch (UserNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.OK).body(ex.getMessage());
		}
	}

	@PostMapping(value = "login")
	public ResponseEntity<?> loginUser(@RequestBody UserRequest userRequest) {
		try {
			return ResponseEntity.ok(userService.loginUser(userRequest));
		} catch (UserNotFoundException | InvalidPasswordException ex) {
			return ResponseEntity.status(HttpStatus.OK).body(ex.getMessage());
		}
	}
}
