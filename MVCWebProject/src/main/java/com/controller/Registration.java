package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.Constants;
import com.model.RegisterUser;

@WebServlet("/registration.do")
public class Registration extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("./register.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String confirmpass = req.getParameter("confirmpass");

		String msg = registerNewUser(new RegisterUser(fname, lname, username, password, confirmpass));
		
		if(msg == null) {
			req.setAttribute(Constants.ALERT, "Registration Successful!");
			req.getRequestDispatcher("./login.jsp").forward(req, resp);
		} else {
			req.setAttribute(Constants.ALERT, msg);
			req.getRequestDispatcher("./register.jsp").forward(req, resp);
		}
	}
	
	private String registerNewUser(RegisterUser ru) {
		switch (ru.checkUser()) {
		case RegisterUser.SUCCESS:
			return null;
		case RegisterUser.EMPTY_FIELDS:
			return "Input Fields are Empty";
		case RegisterUser.INVALID_PASSWORD:
			return "Passwords Do Not Match!";
		case RegisterUser.FNAME:
			return "First Name should not be more than 25 characters";
		case RegisterUser.LNAME:
			return "Last Name should not be more than 25 characters";
		case RegisterUser.USERNAME:
			return "Username should not be more than 20 characters";
		case RegisterUser.PASSWORD:
			return "Password should not be more than 20 characters";
		case RegisterUser.SQL_FAILED:
			return "Registration Failed. Try Again.";
		case RegisterUser.SQL_UNAME:
			return "Username already Exists!";
		case RegisterUser.SQL_EX:
			return "Database Error. Try Again.";
		default:
			return "Unknow Error. Try Again.";
		}
	}
}