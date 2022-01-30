package com.genesys.UserManagementSystem.exceptions;

/**
 * @author Syed Ali.
 * @createdAt 30/01/2022, Sunday, 18:20
 */
public class UserAlreadyFoundException extends Exception{
	public UserAlreadyFoundException(String message){
		super(message);
	}
}
