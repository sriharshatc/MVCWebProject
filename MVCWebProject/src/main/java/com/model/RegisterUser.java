package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.data.DAO;

public class RegisterUser {
	
	public static final int EMPTY_FIELDS = 100;
	public static final int INVALID_PASSWORD = 200;
	public static final int FNAME = 300;
	public static final int LNAME = 400;
	public static final int USERNAME = 500;
	public static final int PASSWORD = 600;

	public static final int SUCCESS = 0;
	public static final int SQL_FAILED = 10;
	public static final int SQL_UNAME = 20;
	public static final int SQL_EX = 30;

	private final String fname, lname, username, password, confirmpass;

	public RegisterUser(String fname, String lname, String username, String password, String confirmpass) {
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
		this.confirmpass = confirmpass;
	}
	
	public int checkUser() {
		boolean valid = checkString(fname) && checkString(lname) && checkString(username)
				&& checkString(password) && checkString(confirmpass);
		
		if(!valid) {
			return EMPTY_FIELDS;
		} else if (!password.equals(confirmpass)) {
			return INVALID_PASSWORD;
		} else if (fname.length() > 25) {
			return FNAME;
		} else if (lname.length() > 25) {
			return LNAME;
		} else if (username.length() > 20) {
			return USERNAME;
		} else if (password.length() > 20) {
			return PASSWORD;
		} else {
			return addUser();
		}
	}
	
	private int addUser() {

		int result = usernameCheck();
		if(result != SUCCESS) {
			return result;
		}

		PreparedStatement ps = null;
		try {
			Connection conn = DAO.getConnection();
			ps = conn.prepareStatement("INSERT INTO mvc_db_users VALUES (?, ?, ?, ?, 0)");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, fname);
			ps.setString(4, lname);
			result = ps.executeUpdate();
			
			if(result == 1)
				return SUCCESS;
			else
				return SQL_FAILED;

		} catch (SQLException e) {
			e.printStackTrace();
			return SQL_EX;
		} finally {
			try { if (ps != null) ps.close(); } catch (SQLException e) {}
		}
	}
	
	private int usernameCheck() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection conn = DAO.getConnection();
			ps = conn.prepareStatement("SELECT * FROM mvc_db_users WHERE username = ?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return SQL_UNAME;
			} else {
				return SUCCESS;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return SQL_EX;
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) {}
			try { if (ps != null) ps.close(); } catch (SQLException e) {}
		}
	}
	
	private boolean checkString(String s) {
		return (s != null) && (s.length() > 0);
	}
}