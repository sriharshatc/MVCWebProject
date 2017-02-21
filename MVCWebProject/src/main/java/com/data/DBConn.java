package com.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.pool.OracleDataSource;

public final class DBConn {

	private static Connection conn;

	private DBConn() {}
		
	public static final Connection getConnection() throws SQLException {
		if(conn == null) {

			OracleDataSource ods = new OracleDataSource();
			ods.setUser("c##sriharsha");
			ods.setPassword("helloworld");
			ods.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
			ods.setDriverType("oracle.jdbc.driver.OracleDriver");

			conn = ods.getConnection();
			createUsers();
		}
		return conn;
	}

	public static final void closeConnection() throws SQLException {
		if(conn != null)
			conn.close();
	}
	
	private static final void createUsers() {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.execute("CREATE TABLE mvc_db_users ("
					+ "username VARCHAR2(20),"
					+ "password VARCHAR2(20) NOT NULL,"
					+ "fname VARCHAR2(25) NOT NULL,"
					+ "lname VARCHAR2(25) NOT NULL,"
					+ "adminuser NUMBER(1) DEFAULT 0 NOT NULL,"
					+ "CONSTRAINT mvc_db_users_pk PRIMARY KEY(username))");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
		}
	}
}