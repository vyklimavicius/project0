package com.klimavicius;

import org.mindrot.jbcrypt.BCrypt;

public class User {

	// Fields
	/**
	 * Takes three variables email, username and password.
	 */
	String email, username; 
	private String userPassword;

	public User(String email, String username, String userPassword) {
		String encryptedHash = BCrypt.hashpw(userPassword, BCrypt.gensalt());
		this.email = email;
		this.username = email;
		this.userPassword = encryptedHash;
	}

	public String getPassword(){
		return this.userPassword;
	}

}
