package com.genesys.UserManagementSystem.exceptions;

/**
 * @author Syed Ali.
 * @createdAt 30/01/2022, Sunday, 18:46
 */
public class InvalidPasswordException extends Exception{
	public InvalidPasswordException(String message){
		super(message);
	}
}
