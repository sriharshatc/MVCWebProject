package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.Constants;
import com.data.UserInfo;
import com.model.AdminTask;

@WebServlet("/add-product.do")
public class AddProduct extends HttpServlet implements Constants {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("./add-prod.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserInfo user = (UserInfo) request.getSession().getAttribute(CON_USER);

		if(user.isAdmin()) {
			AdminTask task = new AdminTask(
					request.getParameter(CON_PID),
					request.getParameter(CON_PNAME),
					request.getParameter(CON_PRICE));

			String msg = getStatus(task.addProduct());

			if(msg == null)
				response.sendRedirect("./admin.do");
			else {
				request.setAttribute(ALERT, msg);
				this.doGet(request, response);
			}
		}
		else
			response.sendRedirect("./logout.do");
	}
	
	private String getStatus(int status) {
		switch (status) {
		case SUCCESS:
			return null;
		case EMPTY_FIELDS:
			return "Input Fields are Empty!";
		case ERR_PID:
			return "Product ID should not be more than 5 digits";
		case ERR_PNAME:
			return "Product Name should not be more than 25 characters";
		case ERR_PRICE:
			return "Price should not be more than 5 digits";
		case DB_EXISTS:
			return "Product ID or Name Exists!";
		case DB_FAILED:
			return "Add Failed! Try Again.";
		case DB_EXCP:
			return "Database Error! Try Again.";
		default:
			return "Unknow Error! Try Again.";
		}
	}
}