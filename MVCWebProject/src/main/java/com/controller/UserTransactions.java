package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.Constants;
import com.model.Transactions;

@WebServlet("/transactions.do")
public class UserTransactions extends HttpServlet implements Constants {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Transactions.updateTransactions();
		if(Transactions.getTransactions().isEmpty()) {
			request.setAttribute(ALERT, "No Transactions to Display!");
			request.getRequestDispatcher("./admin.jsp").forward(request, response);
		} else {
			request.setAttribute(CON_TXNS, Transactions.getTransactions());
			request.getRequestDispatcher("./txns.jsp").forward(request, response);
		}
	}
}