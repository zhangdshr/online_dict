/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs472;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author air
 */
public class DbConnection {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/entries?useSSL=false";

	private static final String username = "root";
	private static final String password = "Xiaofantuan.520";
	private static Connection conn = null;

	public static Connection getConnection() throws SQLException {
		if (conn == null) {
			conn = DriverManager.getConnection(DB_URL, username, password);
		}
		return conn;
	}
}
