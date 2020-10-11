package com.bridgelabz.registerapp.service;

import java.sql.SQLException;
import java.util.List;

import com.bridgelabz.registerapp.dao.UserDao;
import com.bridgelabz.registerapp.model.User;

public class UserService {

	private UserDao userDao = new UserDao();
	
	public boolean registerUser(User user) throws SQLException {
		return userDao.registerUser(user);
	}

	public boolean checkValidUser(User user) {
		return userDao.checkValidUser(user);
	}

	public List<User> getAllUsers() {
		System.out.println("Inside user service.");
		return userDao.getAllUsers();
	}

}
