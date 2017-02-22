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
import com.model.ValidateUser;

@WebServlet("/login-validation.do")
public class LoginValidation extends HttpServlet implements Constants {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ValidateUser validate = new ValidateUser(
				request.getParameter(CON_UNAME),
				request.getParameter(CON_PASS));
		
		UserInfo user = validate.loginUser();
		
		String msg = getStatus(user.getStatus());

		if(msg == null) {
			HttpSession session = request.getSession(true);
			session.setAttribute(CON_USER, user);
			response.sendRedirect("./products.do");
		} else {
			request.setAttribute(ALERT, msg);
			request.getRequestDispatcher("./login.jsp").forward(request, response);
		}
	}
	
	private String getStatus(int status) {
		switch (status) {
		case SUCCESS:
			return null;
		case EMPTY_FIELDS:
			return "Login Fields are Empty";
		case DB_FAILED:
			return "Login Failed. Invalid Username/Password.";
		case DB_EXCP:
			return "Database Error. Try Again.";
		default:
			return "Unknow Error. Try Again.";
		}
	}
}