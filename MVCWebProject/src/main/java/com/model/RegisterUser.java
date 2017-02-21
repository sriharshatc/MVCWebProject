package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.data.Constants;
import com.data.DBConn;

public class RegisterUser implements Constants {
	
	private final String fname, lname, uname, pass, cnfm;

	public RegisterUser(String fname, String lname, String uname, String pass, String cnfm) {
		this.fname = fname;
		this.lname = lname;
		this.uname = uname;
		this.pass = pass;
		this.cnfm = cnfm;
	}
	
	public int addUser() {
		boolean valid = checkString(fname) && checkString(lname) && checkString(uname)
				&& checkString(pass) && checkString(cnfm);
		
		if(!valid) {
			return EMPTY_FIELDS;
		} else if (!pass.equals(cnfm)) {
			return PASS_MISMATCH;
		} else if (fname.length() > 25) {
			return ERR_FNAME;
		} else if (lname.length() > 25) {
			return ERR_LNAME;
		} else if (uname.length() > 20) {
			return ERR_UNAME;
		} else if (pass.length() > 20) {
			return ERR_PASS;
		} else {
			return performDB();
		}
	}
	
	private int performDB() {

		int result = usernameCheckDB();
		if(result != SUCCESS) {
			return result;
		}

		PreparedStatement ps = null;
		try {
			Connection conn = DBConn.getConnection();
			ps = conn.prepareStatement("INSERT INTO mvc_db_users VALUES (?, ?, ?, ?, 0)");
			ps.setString(1, uname);
			ps.setString(2, pass);
			ps.setString(3, fname);
			ps.setString(4, lname);
			result = ps.executeUpdate();
			
			if(result == 1)
				return SUCCESS;
			else
				return DB_FAILED;

		} catch (SQLException e) {
			e.printStackTrace();
			return DB_EXCP;
		} finally {
			try { if (ps != null) ps.close(); } catch (SQLException e) {}
		}
	}
	
	private int usernameCheckDB() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection conn = DBConn.getConnection();
			ps = conn.prepareStatement("SELECT * FROM mvc_db_users WHERE username = ?");
			ps.setString(1, uname);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return DB_UNAME;
			} else {
				return SUCCESS;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return DB_EXCP;
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) {}
			try { if (ps != null) ps.close(); } catch (SQLException e) {}
		}
	}
	
	private boolean checkString(String s) {
		return (s != null) && (s.length() > 0);
	}
}