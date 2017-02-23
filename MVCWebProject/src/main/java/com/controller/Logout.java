package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DBConn;
import com.model.ShopCart;
import com.model.Inventory;
import com.model.Transactions;

@WebServlet("/logout.do")
public class Logout extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShopCart.clearCart();
		Inventory.clearInventory();
		Transactions.clearTransactions();
		DBConn.closeConnection();

		request.getSession().invalidate();

		response.sendRedirect("./");
	}
}