package com.bridgelabz.registerapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridgelabz.registerapp.model.User;
import com.bridgelabz.registerapp.service.UserService;

@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getAttribute("message"));
		System.out.println("Home Controller");
		UserService userService = new UserService();
		List<User> users = userService.getAllUsers();
		
		request.setAttribute("users", users);
		request.getRequestDispatcher("home.vm").forward(request, response);
	}

}
