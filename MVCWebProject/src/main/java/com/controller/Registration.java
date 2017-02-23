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
public class Registration extends HttpServlet implements Constants{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("./register.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RegisterUser register = new RegisterUser(
				req.getParameter(CON_FNAME),
				req.getParameter(CON_LNAME),
				req.getParameter(CON_UNAME),
				req.getParameter(CON_PASS),
				req.getParameter(CON_CNFM));

		String msg = getStatus(register);
		
		if(msg == null) {
			req.setAttribute(ALERT, "Registration Successful!");
			req.getRequestDispatcher("./login.jsp").forward(req, resp);
		} else {
			req.setAttribute(ALERT, msg);
			req.getRequestDispatcher("./register.jsp").forward(req, resp);
		}
	}
	
	private String getStatus(RegisterUser register) {
		switch (register.addUser()) {
		case SUCCESS:
			return null;
		case EMPTY_FIELDS:
			return "Input Fields are Empty!";
		case PASS_MISMATCH:
			return "Passwords Do Not Match!";
		case ERR_FNAME:
			return "First Name should not be more than 25 characters";
		case ERR_LNAME:
			return "Last Name should not be more than 25 characters";
		case ERR_UNAME:
			return "Username should not be more than 20 characters";
		case ERR_PASS:
			return "Password should not be more than 20 characters";
		case DB_EXISTS:
			return "Username already Exists!";
		case DB_FAILED:
			return "Registration Failed! Try Again.";
		case DB_EXCP:
			return "Database Error! Try Again.";
		default:
			return "Unknow Error! Try Again.";
		}
	}
}