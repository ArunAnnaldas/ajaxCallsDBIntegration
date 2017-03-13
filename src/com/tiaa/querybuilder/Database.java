package com.tiaa.querybuilder;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

	private static Database dbInstance;
	private static Connection con;

	private Database() {
	}

	public static Database getInstance() {
		if (dbInstance == null) {
			dbInstance = new Database();
		}
		return dbInstance;
	}

	public Connection getConnection() {
		if (con == null) {
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				String connectionString = "jdbc:ucanaccess://C:\\Users\\aannaldas\\Documents\\EmployeeData.accdb";
				con = DriverManager.getConnection(connectionString);

			//	System.out.println("This Servlet's database connection was created on "
				//		+ new java.util.Date(System.currentTimeMillis()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//System.out.println("This Servlet's database connection is re-using the connection created on "
			//	+ new java.util.Date(System.currentTimeMillis()));
		return con;
	}

}
