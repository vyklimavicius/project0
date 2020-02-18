package com.klimavicius.model;

import java.io.Serializable;

import org.mindrot.jbcrypt.BCrypt;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	// Fields
	/**
	 * Takes four variables user_id, email, username and password.
	 */
	private int userId;
	private String email, username; 
	private String password;

	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", username=" + username + ", password=" + password
				+ "]";
	}

	public User(int userId, String email, String username, String password) {
		this.userId = userId;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	public User(String email, String username, String password) {
		String encryptedHash = BCrypt.hashpw(password, BCrypt.gensalt());
		this.email = email;
		this.username = username;
		this.password = encryptedHash;
	}

	public int getUserId() {
		return this.userId;
	}
	
	public void setUserID(int userID) {
		this.userId = userID;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	// With JDBC
	/**
	 * Make setter and getters and make the fields private
	 * int User_id field
	 * 
	 * SETTERS
	 */
	
	

}
