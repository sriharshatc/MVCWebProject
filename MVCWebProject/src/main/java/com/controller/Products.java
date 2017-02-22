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
import com.model.BuildCart;
import com.model.Inventory;

@WebServlet("/products.do")
public class Products extends HttpServlet implements Constants {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.setAttribute(CON_PLIST, Inventory.getInventory());
		session.setAttribute(CON_SCART, BuildCart.getCart());
		
		Inventory.updateInventory();

		UserInfo user = (UserInfo) request.getSession().getAttribute(CON_USER);
		if(user.isAdmin())
			request.getRequestDispatcher("./admin.jsp").forward(request, response);
		else
			request.getRequestDispatcher("./products.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String checkout = (String) req.getAttribute(CON_COUT);
		if(checkout != null && checkout.equals(CON_COUT)) {
			req.setAttribute(ALERT, "Transaction Successful!");
			req.getRequestDispatcher("./products.jsp").forward(req, resp);
		}
		
		int pid = Integer.parseInt(req.getParameter(CON_PID));
		int qty = Integer.parseInt(req.getParameter(CON_QTY));
		
		BuildCart.buildCart(pid, qty);
		
		if(qty < 1 || qty > 5)
			resp.sendRedirect("./scart.do");
		else
			req.getRequestDispatcher("./products.jsp").forward(req, resp);
	}
}