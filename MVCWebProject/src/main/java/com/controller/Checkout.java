package com.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.CartItem;
import com.data.Constants;
import com.data.UserInfo;
import com.data.UserTxn;
import com.model.Inventory;
import com.model.ShopCart;
import com.model.Transactions;

@WebServlet("/checkout.do")
public class Checkout extends HttpServlet implements Constants {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserInfo user = (UserInfo) request.getSession().getAttribute(CON_USER);
		String fullname = user.getFname() + " " + user.getLname();
		
		for(CartItem item : ShopCart.getCart()) {
			Transactions.addTxn(new UserTxn(fullname, item.getPname(), item.getQty(), item.getTotal(),
					new Timestamp(System.currentTimeMillis())));
		}
		
		ShopCart.clearCart();
		Inventory.updateInventory();

		request.setAttribute(ALERT, "Transaction Successful!");
		request.getRequestDispatcher("./products.jsp").forward(request, response);
	}
}