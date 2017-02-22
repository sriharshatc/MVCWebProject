package com.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.data.DBConn;
import com.data.ProductInfo;

public class Inventory {

	private static final Set<ProductInfo> plist = new HashSet<>();
	
	private Inventory() {}
	
	public static Set<ProductInfo> getInventory() {
		return plist;
	}
	
	public static void updateInventory() {
		plist.clear();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Connection conn = DBConn.getConnection();
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("SELECT * FROM mvc_db_products");
			
			while(rs.next()) {
				plist.add(new ProductInfo(rs.getInt(1), rs.getString(2), rs.getInt(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) {}
			try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
		}
	}
}