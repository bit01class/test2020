package com.bit.framework.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EmpDao {

	public Connection getConnection() throws SQLException{
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="scott";
		String password="tiger1";
		conn=DriverManager.getConnection(url, user, password);
		
		return conn;
	}
}
