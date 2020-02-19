package com.klimavicius;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import com.klimavicius.dao.AccountDaoImpl;
import com.klimavicius.dao.UserDaoImpl;
import com.klimavicius.model.Account;
import com.klimavicius.model.User;
import com.klimavicius.ui.UserUI;
import com.klimavicius.util.ConnectionUtil;

import org.junit.*;

public class UITest {

	@Test
	public void checkNegativeBalance() {
		AccountDaoImpl aController = new AccountDaoImpl();
		boolean check = aController.checkAnyNegativeBalance() == null ? true : false;
		assertTrue(check);
	}

	@Test
	public void checkConnection() {
		String actual = "";
		try {
			Connection c = ConnectionUtil.getConnection();
			actual = c.getMetaData().getDriverName();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String expected = "PostgreSQL JDBC Driver";
		assertEquals(expected, actual);
	}

	@Test
	public void createUser(){
		UserDaoImpl userController = new  UserDaoImpl();
		User johnDoe = new User(200, "john", "john@test.com", "test");
		int actual = userController.createUser(johnDoe);
		int expected = 0;
		assertEquals(expected, actual);
	}

	@Test
	public void deleteUser(){
		UserDaoImpl userController = new UserDaoImpl();
		User johnDoe = new User(200, "john", "john@test.com", "test");
		int actual = userController.deleteUser(johnDoe);
		int expected = 0;
		assertEquals(expected, actual);
	}

	@Test
	public void createAccount(){
		AccountDaoImpl accountController = new  AccountDaoImpl();
		Account johnDoe = new Account(200, 200.22);
		int actual = accountController.createAccount(johnDoe);
		int expected = 0;
		assertEquals(expected, actual);
	}

	@Test
	public void deleteAccount(){
		AccountDaoImpl accountController = new AccountDaoImpl();
		Account johnDoe = new Account(200, 200.22);
		int actual = accountController.deleteAccount(johnDoe);
		int expected = 0;
		assertEquals(expected, actual);
	}

	@Test
	public void depositBalance(){
		AccountDaoImpl accountController = new AccountDaoImpl();
		Account johnDoe = new Account(200, 200.22);
		int actual = accountController.deposit(johnDoe, 200.22);
		int expected = 0;
		assertEquals(expected, actual);
	}

	@Test
	public void withdrawBalance(){
		AccountDaoImpl accountController = new AccountDaoImpl();
		Account johnDoe = new Account(200, 200.22);
		int actual = accountController.withdraw(johnDoe, 200.22);
		int expected = 0;
		assertEquals(expected, actual);
	}

	




	
	



}
