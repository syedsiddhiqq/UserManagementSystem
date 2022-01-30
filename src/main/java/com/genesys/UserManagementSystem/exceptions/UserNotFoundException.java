package com.genesys.UserManagementSystem.exceptions;

/**
 * @author Syed Ali.
 * @createdAt 30/01/2022, Sunday, 18:25
 */
public class UserNotFoundException extends Exception {
	public UserNotFoundException(String message){
		super(message);
	}
}
