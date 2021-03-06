package com.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.pool.OracleDataSource;

public final class DBConn {

	private static Connection conn;

	private DBConn() {}
		
	public static final Connection getConnection() throws SQLException {
		if(conn == null || conn.isClosed()) {

			OracleDataSource ods = new OracleDataSource();
			ods.setUser("c##sriharsha");
			ods.setPassword("helloworld");
			ods.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
			ods.setDriverType("oracle.jdbc.driver.OracleDriver");

			conn = ods.getConnection();
			createUsers();
			createProducts();
			createTxns();
		}
		return conn;
	}

	public static final void closeConnection() {
		try {
			if(conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
			
			stmt.execute("INSERT INTO mvc_db_users VALUES('admin', 'admin', 'Admin', 'User', 1)");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
		}
	}
	
	private static final void createProducts() {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.execute("CREATE TABLE mvc_db_products ("
					+ "pid NUMBER(5),"
					+ "pname VARCHAR2(25) NOT NULL,"
					+ "price NUMBER(5) NOT NULL,"
					+ "CONSTRAINT mvc_db_products_pk PRIMARY KEY(pid))");
			
			stmt.execute("INSERT INTO mvc_db_products VALUES(101, 'Snickers', 50)");
			stmt.execute("INSERT INTO mvc_db_products VALUES(102, 'Kit Kat', 35)");
			stmt.execute("INSERT INTO mvc_db_products VALUES(103, 'Hersheys', 65)");
			stmt.execute("INSERT INTO mvc_db_products VALUES(104, 'Twix', 45)");
			stmt.execute("INSERT INTO mvc_db_products VALUES(105, 'Cadbury', 80)");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
		}
	}

	private static final void createTxns() {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.execute("CREATE TABLE mvc_db_txns ("
					+ "fullname VARCHAR2(50) NOT NULL,"
					+ "pname VARCHAR2(25) NOT NULL,"
					+ "qty NUMBER(1) NOT NULL,"
					+ "total NUMBER(6) NOT NULL,"
					+ "stamp TIMESTAMP(0) NOT NULL)");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
		}
	}
}