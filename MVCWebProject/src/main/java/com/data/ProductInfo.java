package com.data;

public final class ProductInfo {

	private final int pid, price;
	private final String pname;

	public ProductInfo(int pid, String pname, int price) {
		this.pid = pid;
		this.pname = pname;
		this.price = price;
	}

	public final int getPid() {
		return pid;
	}

	public final int getPrice() {
		return price;
	}

	public final String getPname() {
		return pname;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof ProductInfo) {
			return this.pid == ((ProductInfo) obj).pid;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return new Integer(this.pid).hashCode();
	}
}