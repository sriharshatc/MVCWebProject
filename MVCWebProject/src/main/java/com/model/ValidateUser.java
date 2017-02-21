package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.data.Constants;
import com.data.DBConn;
import com.data.UserInfo;

public class ValidateUser implements Constants{

	private final String uname;
	private final String pass;

	public ValidateUser(String uname, String pass) {
		this.uname = uname;
		this.pass = pass;
	}
	
	public UserInfo loginUser() {
		boolean valid = checkString(uname) && checkString(pass);
		
		if(!valid) {
			return new UserInfo(EMPTY_FIELDS, null, null, null, false);
		} else {
			return performDB();
		}
	}
	
	private UserInfo performDB() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection conn = DBConn.getConnection();
			ps = conn.prepareStatement("SELECT fname, lname, adminuser"
					+ " FROM mvc_db_users WHERE username = ? AND password = ?");
			ps.setString(1, uname);
			ps.setString(2, pass);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String fname = rs.getString(1);
				String lname = rs.getString(2);

				boolean admin;
				if(rs.getInt(3) == 1)
					admin = true;
				else
					admin = false;
				
				return new UserInfo(SUCCESS, fname, lname, uname, admin);
			} else {
				return new UserInfo(DB_FAILED, null, null, null, false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new UserInfo(DB_EXCP, null, null, null, false);
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) {}
			try { if (ps != null) ps.close(); } catch (SQLException e) {}
		}
	}

	private boolean checkString(String s) {
		return (s != null) && (s.length() > 0);
	}
}