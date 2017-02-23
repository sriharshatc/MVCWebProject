package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.Constants;
import com.model.ShopCart;

@WebServlet("/scart.do")
public class SCart extends HttpServlet implements Constants {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("./cart.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(ShopCart.getCart().isEmpty()) {
			req.setAttribute(ALERT, "Your Cart is Empty!");
			req.getRequestDispatcher("./products.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("./cart.jsp").forward(req, resp);
		}
	}
}