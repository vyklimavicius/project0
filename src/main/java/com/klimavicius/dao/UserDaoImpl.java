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
		
		try(Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getPassword());
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int updateUser(User u) {
//		String sql = "update user set "
		return 0;
	}

	@Override
	public int deleteUser(User u) {
		// TODO Auto-generated method stub
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

	

}
