package com.klimavicius.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	private static Connection connection;

//	public static Connection getConnection() throws SQLException{
//		String url = "jdbc:postgresql://localhost:5432/BankTest";
//		String username = "postgres";
//		String password = "Cotiweco1";
//		
//		if(connection == null || connection.isClosed()) {
//			//Start connection
//			connection = DriverManager.getConnection(url, username, password);
//		}
//		
//		return connection;
//	}
	
	public static Connection getConnection() throws SQLException{
		String url = System.getenv("JDBC_DB_HOST");
		String username = System.getenv("JDBC_DB_USER");
		String password = System.getenv("JDBC_DB_PASSWORD");
		
		if(connection == null || connection.isClosed()) {
			//Start connection
			connection = DriverManager.getConnection(url, username, password);
		}
		
		return connection;
	}
}
