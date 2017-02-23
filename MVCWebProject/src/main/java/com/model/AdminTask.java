package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.data.Constants;
import com.data.DBConn;

public class AdminTask implements Constants {

	private final String pid, pname, price;
	
	public AdminTask(String pid, String pname, String price) {

		this.pid = (pid == null ? null : pid.replaceAll("\\D+", ""));
		this.pname = pname;
		this.price = (price == null ? null : price.replaceAll("\\D+", ""));
	}
	
	public int removeProduct() {
		PreparedStatement stmt = null;
		try {
			Connection conn = DBConn.getConnection();
			stmt = conn.prepareStatement("DELETE FROM mvc_db_products WHERE pid = ?");
			stmt.setInt(1, Integer.parseInt(pid));
			
			if(stmt.executeUpdate() == 1)
				return SUCCESS;
			else
				return DB_FAILED;

		} catch (SQLException e) {
			e.printStackTrace();
			return DB_EXCP;
		} finally {
			try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
		}
	}

	public int addProduct() {
		boolean valid = checkString(pid) && checkString(pname) && checkString(price);
		
		if(!valid) {
			return EMPTY_FIELDS;
		} else if(pid.length() > 5) {
			return ERR_PID;
		} else if(pname.length() > 25) {
			return ERR_PNAME;
		} else if(price.length() > 5) {
			return ERR_PRICE;
		} else {
			return performAdd();
		}
	}
	
	private int performAdd() {

		int result = checkDB();
		if(result != SUCCESS) {
			return result;
		}

		PreparedStatement ps = null;
		try {
			Connection conn = DBConn.getConnection();
			ps = conn.prepareStatement("INSERT INTO mvc_db_products VALUES (?, ?, ?)");
			ps.setInt(1, Integer.parseInt(pid));
			ps.setString(2, pname);
			ps.setInt(3, Integer.parseInt(price));

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
	
	private int checkDB() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection conn = DBConn.getConnection();
			ps = conn.prepareStatement("SELECT pid FROM mvc_db_products WHERE pid = ? OR pname = ?");
			ps.setInt(1, Integer.parseInt(pid));
			ps.setString(2, pname);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return DB_EXISTS;
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