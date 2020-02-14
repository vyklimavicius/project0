package com.klimavicius.dao;

import java.util.List;

import com.klimavicius.model.Account;

public interface AccountDao {
	
	public List<Account> getAccounts();
	public Account getAccountBy(int id);
	public int createAccount(Account a);
	public int updateAccount(Account a);
	public int deleteAccount(Account a);
}
