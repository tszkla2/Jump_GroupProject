package com.cognixia.jump.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionManager {
	
	// Windows: jdbc:mysql://localhost:3306/crud_db
	// Mac/Linux: jdbc:mysql://localhost:3306/crud_db?serverTimezone=EST5EDT
	private static final String URL = "jdbc:mysql://localhost:3306/library?serverTimezone=EST5EDT";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "pebbles10"; // Windows: root | Mac/Linux: Root@123
	
	private static Connection connection = null;
	
	private static void makeConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() {
		
		if(connection == null) {
			makeConnection();
		}
		
		return connection;
	}
	
	

}
