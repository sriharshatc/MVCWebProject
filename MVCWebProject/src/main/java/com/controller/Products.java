package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.data.Constants;
import com.data.UserInfo;
import com.model.ShopCart;
import com.model.Inventory;

@WebServlet("/products.do")
public class Products extends HttpServlet implements Constants {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.setAttribute(CON_PLIST, Inventory.getInventory());

		UserInfo user = (UserInfo) session.getAttribute(CON_USER);
		if(user.isAdmin()) {
			response.sendRedirect("./admin.do");
		} else {
			Inventory.updateInventory();
			session.setAttribute(CON_SCART, ShopCart.getCart());
			request.getRequestDispatcher("./products.jsp").forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int pid = Integer.parseInt(req.getParameter(CON_PID));
		int qty = Integer.parseInt(req.getParameter(CON_QTY));
		
		ShopCart.buildCart(pid, qty);
		
		if(qty < 1 || qty > 5)
			resp.sendRedirect("./scart.do");
		else
			req.getRequestDispatcher("./products.jsp").forward(req, resp);
	}
}