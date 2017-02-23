package com.data;

import java.sql.Timestamp;
import java.util.Date;

public final class UserTxn {

	private final String fullname, pname;
	private final int qty, total;
	private final Timestamp stamp;

	public UserTxn(String fullname, String pname, int qty, int total, Timestamp stamp) {
		this.fullname = fullname;
		this.pname = pname;
		this.qty = qty;
		this.total = total;
		this.stamp = stamp;
	}

	public final String getFullname() {
		return fullname;
	}

	public final String getPname() {
		return pname;
	}

	public final int getQty() {
		return qty;
	}

	public final int getTotal() {
		return total;
	}

	public final Timestamp getStamp() {
		return stamp;
	}
	
	public final String getDate() {
		return new Date(stamp.getTime()).toString();
	}
}