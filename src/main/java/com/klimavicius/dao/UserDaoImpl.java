package com.klimavicius.dao;

import java.sql.*;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.klimavicius.model.Account;
import com.klimavicius.model.User;
import com.klimavicius.util.ConnectionUtil;

public class UserDaoImpl implements UserDao{

	@Override
	public List<User> getUsers() {
		String sql = "select * from users";
		List<User> users = new ArrayList <>();
		
		try(Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);
				){
			while(rs.next()) {
				int userId = rs.getInt("user_id");
				String username = rs.getString("username");
				String email = rs.getString("email");
				String password = rs.getString("password");
				User fetchUser = new User(userId, email, username, password);
				users.add(fetchUser);			
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User getUserBy(int id) {
		String sql = "Select * from users where user_id = ?";
		User u = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery(sql);
			while(rs.next()) {
				int userId = rs.getInt("user_id");
				String username = rs.getString("username");
				String email = rs.getString("email");
				String password = rs.getString("password");
				u = new User(userId, email, username, password);
			}
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		
		return u;
	}

	@Override
	public int createUser(User u) {
		String sql = "insert into users (username, email, password) values (?, ?, ?)";
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getPassword());
			rs = ps.executeQuery();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public User updateUser(String column, User u) {
		int user_id = u.getUserId();
		ResultSet rs = null;
		String sql = null;
		switch(column) {
		case "username":
			sql = "update users set username = ? where user_id = ?";
			try(Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
				ps.setString(1, u.getUsername());
				ps.setInt(2, user_id);
			    ps.executeUpdate();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			break;
		case "email":
			sql = "update users set email = ? where user_id = ?";
			try(Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
				ps.setString(1, u.getEmail());
				ps.setInt(2, user_id);
				ps.executeUpdate();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			break;
		case "password":
			sql = "update users set password = ? where user_id = ?";
			try(Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
				ps.setString(1, u.getPassword());
				ps.setInt(2, user_id);
				ps.executeUpdate();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		return u;
	}

	@Override
	public int deleteUser(User u) {
		String sql = "delete from users where user_id = ?";
		int user_id = u.getUserId();
		try(Connection c = ConnectionUtil.getConnection(); 
			PreparedStatement ps = c.prepareStatement(sql)) { 
			ps.setInt(1,user_id);
			ps.execute(sql);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public User createUserWithFunction(User u) {
		String sql = "{call name of sql function(?,?,?)";
		
		try(Connection c = ConnectionUtil.getConnection();
				CallableStatement cs = c.prepareCall(sql)){
			cs.setString(1, u.getEmail());
			cs.setString(2, u.getUsername());
			cs.setString(3, u.getPassword());
			
			cs.execute();
			
			ResultSet rs = cs.getResultSet();
			
			while(rs.next()) {
				int newId = rs.getInt("user_id");
				// Create the setter
//				u.setId = newId;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public User getUserByUsername(String s) {
		String sql = "Select * from users where username = ?";
		User u = null;

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int userId = rs.getInt("user_id");
				String username = rs.getString("username");
				String email = rs.getString("email");
				String password = rs.getString("password");
				u = new User(userId, email, username, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}

	

}
