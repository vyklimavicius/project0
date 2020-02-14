package com.klimavicius.dao;

import java.util.List;

import com.klimavicius.model.User;

public interface UserDao {
	
	public List<User> getUsers();
	public User getUserBy(int id);
	public int createUser(User u);
	public int updateUser(User u);
	public int deleteUser(User u);
	public User createUserWithFunction(User u);

}
