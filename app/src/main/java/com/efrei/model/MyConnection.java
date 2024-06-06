package com.efrei.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/4menic";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	public MyConnection() {
		super();
	}

	public static Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("tout correct");
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			System.err.println("error connection");
			throw new SQLException("MySQL JDBC Driver not found: " + e.getMessage());
		}
	}
}
