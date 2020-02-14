package com.klimavicius.dao;

import java.sql.*;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import com.klimavicius.model.Account;
import com.klimavicius.util.ConnectionUtil;

/**
 * Dao implementation handles the SQL statements	
 *
 */

public class AccountDaoImpl implements AccountDao{

	

	@Override
	public List<Account> getAccounts() {
		String sql = "select * from account";
		List <Account> accounts = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql))
		{
			while(rs.next()) {
				int accountId = rs.getInt("account_id");
				Long balance = rs.getLong("balance");
				int userId = rs.getInt("user_id");
//				Account d = new Account(accountId, userId, balance);
				accounts.add(new Account(accountId, userId, balance));
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public Account getAccountBy(int id) {
		//Prepared statement you need to pass parameters to the query
		String sql = "Select * from account where account_id = ?";
		Account d = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery(sql);
			while(rs.next()) {
				int accountId = rs.getInt("account_id");
				Long balance = rs.getLong("balance");
				int userId = rs.getInt("user_id");
				d = new Account(accountId, userId, balance);
			}
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		
		return d;
	}

	@Override
	public int createAccount(Account a) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAccount(Account a) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAccount(Account a) {
		// TODO Auto-generated method stub
		return 0;
	}

}
