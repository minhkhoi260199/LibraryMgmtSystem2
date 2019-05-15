package models;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
	public static Connection getConnection(){
		Connection connection = null;
		try { 
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root", "");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		connection = null;
		}
		return connection;
		}
}
