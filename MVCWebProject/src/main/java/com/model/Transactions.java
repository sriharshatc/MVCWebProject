package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.data.DBConn;
import com.data.UserTxn;

public class Transactions {

	private static final List<UserTxn> txns = new ArrayList<>();
	
	private Transactions() {}
	
	public static List<UserTxn> getTransactions() {
		return txns;
	}
	
	public static void clearTransactions() {
		txns.clear();
	}

	public static void updateTransactions() {
		clearTransactions();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Connection conn = DBConn.getConnection();
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("SELECT * FROM mvc_db_txns");
			
			while(rs.next()) {
				txns.add(new UserTxn(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getTimestamp(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) {}
			try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
		}
	}
	
	public static void addTxn(UserTxn txn) {
		PreparedStatement stmt = null;
		try {
			Connection conn = DBConn.getConnection();
			stmt = conn.prepareStatement("INSERT INTO mvc_db_txns VALUES(?, ?, ?, ?, ?)");

			stmt.setString(1, txn.getFullname());
			stmt.setString(2, txn.getPname());
			stmt.setInt(3, txn.getQty());
			stmt.setInt(4, txn.getTotal());
			stmt.setTimestamp(5, txn.getStamp());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
		}
	}
}