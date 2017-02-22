package com.model;

import java.util.HashSet;
import java.util.Set;

import com.data.CartItem;
import com.data.ProductInfo;

public class BuildCart {

	private static final Set<CartItem> scart = new HashSet<>();
	private static final Set<ProductInfo> plist = Inventory.getInventory();

	private BuildCart() {}
	
	public static Set<CartItem> getCart() {
		return scart;
	}
	
	public static void clearCart() {
		scart.clear();
	}

	public static void buildCart(int pid, int qty) {
		if(qty < 1 || qty > 5)
			removeItem(pid);
		else
			addItem(pid, qty);
	}
	
	private static void removeItem(int pid) {
		scart.remove(new CartItem(getProduct(pid), 0));
	}
	
	private static void addItem(int pid, int qty) {
		CartItem item = new CartItem(getProduct(pid), qty);
		scart.remove(item);
		scart.add(item);
	}

	private static ProductInfo getProduct(int pid) {
		for(ProductInfo product : plist) {
			if(pid == product.getPid())
				return product;
		}
		return null;
	}
}