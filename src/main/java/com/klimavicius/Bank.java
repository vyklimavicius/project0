package com.klimavicius;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.klimavicius.util.ConnectionUtil;
import com.klimavicius.dao.*;
import com.klimavicius.model.User;
import com.klimavicius.ui.UserUI;

public class Bank {



    public static void main(String[] args) {


//        User vytautas = new User("vyklimavicius@gmail.com", "vyklima", "test");
//
//        System.out.println(vytautas.getPassword());
    	
    	try {
    		Connection c = ConnectionUtil.getConnection();
    		String driverName = c.getMetaData().getDriverName();
    		System.out.println(driverName);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
//    	
    	UserDaoImpl users = new UserDaoImpl();
    	
        List <User> userList = users.getUsers();
//        
        for(User u: userList) {
        	System.out.println(u);
        }
//        
//        System.out.println(userList.get(0).getUserId());
//        System.out.println(userList.get(0).getUsername());
//        System.out.println(userList.get(0).getEmail());
//        System.out.println(userList.get(0).getPassword());
    	
//    	  UserUI userUI = new UserUI();
//    	  userUI.startUI();
    	  

    }
}

