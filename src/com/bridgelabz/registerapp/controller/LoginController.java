package com.bridgelabz.registerapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridgelabz.registerapp.model.User;
import com.bridgelabz.registerapp.service.UserService;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		UserService userService = new UserService();
		user.setEmailId(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		
		String message = "";
		String path = "";
		boolean isRegisteredUser = true;
		isRegisteredUser = userService.checkValidUser(user);
		if (isRegisteredUser) {
			path = "home.vm";
		} else {
			message = "Enter registred user name and password.";
			path = "login.vm";
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher(path).forward(request, response);
	}
}
