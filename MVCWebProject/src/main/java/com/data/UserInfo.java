package com.data;

import java.io.Serializable;

public final class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private final int status;
	private final boolean admin;
	private final String fname, lname, uname;

	public UserInfo(int status, String fname, String lname, String uname, boolean admin) {
		this.status = status;
		this.fname = fname;
		this.lname = lname;
		this.uname = uname;
		this.admin = admin;
	}

	public final int getStatus() {
		return status;
	}

	public final boolean isAdmin() {
		return admin;
	}

	public final String getFname() {
		return fname;
	}

	public final String getLname() {
		return lname;
	}

	public final String getUname() {
		return uname;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof UserInfo) {
			return this.uname.equals(((UserInfo) obj).uname);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return uname.hashCode();
	}
}