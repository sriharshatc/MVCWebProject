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
import com.model.Inventory;

@WebServlet("/admin.do")
public class Admin extends HttpServlet implements Constants {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Inventory.updateInventory();
		request.getRequestDispatcher("./admin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserInfo user = (UserInfo) request.getSession().getAttribute(CON_USER);

		if(user.isAdmin()) {
			AdminTask task = new AdminTask(request.getParameter(CON_PID), null, null);
			String msg = getStatus(task.removeProduct());
			if(msg != null)
				request.setAttribute(ALERT, msg);
			this.doGet(request, response);
		}
		else
			response.sendRedirect("./logout.do");
	}
	
	private String getStatus(int status) {
		switch (status) {
		case SUCCESS:
			return null;
		case DB_FAILED:
			return "Remove Failed! Try Again.";
		case DB_EXCP:
			return "Database Error! Try Again.";
		default:
			return "Unknow Error! Try Again.";
		}
	}
}