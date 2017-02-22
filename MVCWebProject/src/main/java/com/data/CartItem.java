package com.data;

public final class CartItem {

	private final int pid, qty, total;
	private final String pname;
	
	public CartItem(ProductInfo info, int qty) {
		this.qty = qty;
		this.pid = info.getPid();
		this.pname = info.getPname();
		this.total = qty * info.getPrice();
	}

	public final int getPid() {
		return pid;
	}

	public final int getQty() {
		return qty;
	}

	public final int getTotal() {
		return total;
	}

	public final String getPname() {
		return pname;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof CartItem) {
			return this.pid == ((CartItem) obj).pid;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return new Integer(this.pid).hashCode();
	}
}