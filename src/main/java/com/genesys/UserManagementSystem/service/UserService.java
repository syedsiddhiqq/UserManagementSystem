package com.genesys.UserManagementSystem.service;

import com.genesys.UserManagementSystem.beans.UserRequest;
import com.genesys.UserManagementSystem.entities.User;
import com.genesys.UserManagementSystem.exceptions.InvalidPasswordException;
import com.genesys.UserManagementSystem.exceptions.UserAlreadyFoundException;
import com.genesys.UserManagementSystem.exceptions.UserNotFoundException;
import com.genesys.UserManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import static com.genesys.UserManagementSystem.constants.Constants.PASSWORD_INCORRECT;
import static com.genesys.UserManagementSystem.constants.Constants.SUCCESS;
import static com.genesys.UserManagementSystem.constants.Constants.USER_ALREADY_FOUND;
import static com.genesys.UserManagementSystem.constants.Constants.USER_NOT_FOUND;

/**
 * @author Syed Ali.
 * @createdAt 30/01/2022, Sunday, 14:23
 */
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public User createUser(UserRequest userRequest) throws UserAlreadyFoundException {
		User user;
		if (!ifUserAlreadyPresent(userRequest)) {
			user = userRepository.save(createUserFromRequest(userRequest));
		} else {
			throw new UserAlreadyFoundException(USER_ALREADY_FOUND);
		}
		return user;
	}

	private boolean ifUserAlreadyPresent(UserRequest userRequest) {
		boolean userPresent = false;
		if (Objects.nonNull(userRequest.getId())) {
			Optional<User> optionalUser = userRepository.findById(userRequest.getId());
			userPresent = optionalUser.isPresent();
		}

		if (Objects.nonNull(userRequest.getEmail())) {
			Optional<User> optionalUser = userRepository.findByEmail(userRequest.getEmail());
			userPresent = optionalUser.isPresent();
		}
		return userPresent;
	}

	private User createUserFromRequest(UserRequest userRequest) {
		return new User(userRequest.getId(), userRequest.getName(), userRequest.getEmail(), userRequest.getPassword());
	}

	public User getUser(Integer userId) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent())
			throw new UserNotFoundException(USER_NOT_FOUND);
		return user.get();
	}

	public String deleteUser(UserRequest userRequest) throws UserNotFoundException {
		Optional<User> user = userRepository.findByEmail(userRequest.getEmail());
		if (!user.isPresent())
			throw new UserNotFoundException(USER_NOT_FOUND);
		userRepository.delete(user.get());
		return SUCCESS;
	}

	public User loginUser(UserRequest userRequest) throws UserNotFoundException, InvalidPasswordException {
		Optional<User> optionalUser = userRepository.findByEmail(userRequest.getEmail());
		if (!optionalUser.isPresent())
			throw new UserNotFoundException(USER_NOT_FOUND);

		User user = optionalUser.get();
		if (!userRequest.getPassword().equals(user.getPassword()))
			throw new InvalidPasswordException(PASSWORD_INCORRECT);
		return user;
	}

	public User updateUser(UserRequest userRequest) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(userRequest.getId());
		if (!optionalUser.isPresent())
			throw new UserNotFoundException(USER_NOT_FOUND);
		User user = optionalUser.get();
		user.setEmail(userRequest.getEmail());
		user.setPassword(userRequest.getName());
		user.setName(userRequest.getName());
		user.setUpdatedTime(new Date());
		return userRepository.save(user);
	}
}
