package com.klimavicius.dao;

import java.util.List;

import com.klimavicius.model.Account;
import com.klimavicius.model.User;

public interface AccountDao {
	
	public List<Account> getAccounts();
	public Account checkAnyNegativeBalance();
	public Account getAccountBy(int id);
	public Account getAccountByUser(int u);
	public int createAccount(Account a);
	public int deposit(Account a, double deposit);
	public int deleteAccount(Account a);
	public int withdraw(Account a, double withdraw);
	
}
