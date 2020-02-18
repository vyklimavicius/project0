package com.klimavicius.dao;

import java.sql.*;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import com.klimavicius.model.Account;
import com.klimavicius.model.User;
import com.klimavicius.util.ConnectionUtil;

/**
 * Dao implementation handles the SQL statements	
 *
 */

public class AccountDaoImpl implements AccountDao{

	

	@Override
	public List<Account> getAccounts() {
		String sql = "select * from accounts";
		List <Account> accounts = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql))
		{
			while(rs.next()) {
				int accountId = rs.getInt("account_id");
				double balance = rs.getDouble("balance");
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
		String sql = "Select * from accounts where account_id = ?";
		Account d = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery(sql);
			while(rs.next()) {
				int accountId = rs.getInt("account_id");
				double balance = rs.getDouble("balance");
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
		String sql = "insert into accounts (user_id, balance) values (?, ?)";
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, a.getUserId());
			ps.setDouble(2, a.getBalance());
			rs = ps.executeQuery();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int deleteAccount(Account a) {
		String sql = "delete from accounts where account_id = ?";
		int accountId = a.getAccountId();
		try(Connection c = ConnectionUtil.getConnection(); 
			PreparedStatement ps = c.prepareStatement(sql)) { 
			ps.setInt(1,accountId);
			ps.execute(sql);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int deposit(Account a, double deposit) {
		int user_id = a.getUserId();
//		ResultSet rs = null;
		String sql = null;
		sql = "update accounts set balance = balance + ? where user_id = ?";
		try(Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setDouble(1, deposit);
			ps.setInt(2, user_id);
		    ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int withdraw(Account a, double withdraw) {
		int user_id = a.getUserId();
		if (a.getBalance() < withdraw) {
			return 1;
		} else {
			String sql = null;
			sql = "update accounts set balance = balance - ? where user_id = ?";
			try(Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
				ps.setDouble(1, withdraw);
				ps.setInt(2, user_id);
			    ps.executeUpdate();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public Account getAccountByUser(int u) {
		int userQuery = u;
		Account d = null;
		ResultSet rs = null;
		String sql = "select * from accounts where user_id = ?";
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, userQuery);
			rs = ps.executeQuery();

			while(rs.next()) {
				int accountId = rs.getInt("account_id");
				int userId = rs.getInt("user_id");
				double balance = rs.getDouble("balance");
				d = new Account(accountId, userId, balance);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return d;
		
	}

	@Override
	public Account checkAnyNegativeBalance() {
		String sql = "{call negativeBalance()}";
		Account negativeAccount = null;

		try (Connection c = ConnectionUtil.getConnection(); CallableStatement cs = c.prepareCall(sql)) {

			cs.execute();

			ResultSet rs = cs.getResultSet();

			while (rs.next()) {
				int accountId = rs.getInt("account_id");
				int userId = rs.getInt("user_id");
				double balance = rs.getDouble("balance");
				negativeAccount = new Account(accountId, userId, balance);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return negativeAccount;
	}

}
