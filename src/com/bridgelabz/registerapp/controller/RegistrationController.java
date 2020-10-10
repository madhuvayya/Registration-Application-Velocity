package com.bridgelabz.registerapp.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridgelabz.registerapp.model.User;
import com.bridgelabz.registerapp.service.UserService;

@WebServlet("/register")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setFirstName(request.getParameter("first_name"));
		user.setLastName(request.getParameter("last_name"));
		user.setPhoneNumber(request.getParameter("phone_number"));
		user.setEmailId(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setConfirmPassword(request.getParameter("confirm-password"));
		
		String message = "";
		String path = "";
		try {
			userService.registerUser(user);
			message = "Success";
			path = "login.vm";
		} catch (SQLException e) {
			message = "Failed";
			path = "register.vm";
			e.printStackTrace();
		}
		
		request.setAttribute("message", message);
        RequestDispatcher requestDispatcher =  request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
	}
}
