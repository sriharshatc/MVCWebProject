package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.Constants;
import com.data.UserInfo;

@WebServlet("/products.do")
public class Products extends HttpServlet implements Constants {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserInfo user = (UserInfo) request.getSession().getAttribute(CON_USER);

		if(user.isAdmin())
			request.getRequestDispatcher("./admin.jsp").forward(request, response);
		else
			request.getRequestDispatcher("./products.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}