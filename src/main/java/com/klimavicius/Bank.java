package com.klimavicius;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.klimavicius.util.ConnectionUtil;
import com.klimavicius.dao.*;
import com.klimavicius.model.Account;
import com.klimavicius.model.User;
import com.klimavicius.ui.UserUI;

public class Bank {



    public static void main(String[] args) {
   	
   	// try {
   	// 	Connection c = ConnectionUtil.getConnection();
   	// 	String driverName = c.getMetaData().getDriverName();
   	// 	System.out.println(driverName);
   	// } catch (SQLException e) {
   	// 	e.printStackTrace();
   	// }
    	  UserUI userUI = new UserUI();
    	  userUI.startUI();
    }
}

