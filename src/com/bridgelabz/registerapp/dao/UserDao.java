package com.bridgelabz.registerapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.registerapp.model.User;

public class UserDao {

	private Connection connection = DBConnection.getConnection();

	public boolean registerUser(User user) throws SQLException {
		String registerUserQuery = "INSERT INTO user_details (first_name,last_name,phone_number,email_id,password)\r\n"
				+ "VALUES (?,?,?,?,?)";

		PreparedStatement preparedStatement = connection.prepareStatement(registerUserQuery);
		preparedStatement.setString(1, user.getFirstName());
		preparedStatement.setString(2, user.getLastName());
		preparedStatement.setString(3, user.getPhoneNumber());
		preparedStatement.setString(4, user.getEmailId());
		preparedStatement.setString(5, user.getPassword());
		return preparedStatement.executeUpdate() == 1;
	}

	public boolean checkValidUser(User user) {
		String checkValidUserQuery = "SELECT * FROM user_details WHERE email_id = ? and password = ?";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(checkValidUserQuery);
			preparedStatement.setString(1, user.getEmailId());
			preparedStatement.setString(2, user.getPassword());
			ResultSet resultset = preparedStatement.executeQuery();
			if (resultset.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<User> getAllUsers() {
		System.out.println("Inside user dao.");
		String getAllUsersQuery = "SELECT first_name, last_name, phone_number, email_id FROM user_details";
		List<User> users = new ArrayList<User>(); 
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(getAllUsersQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				User user = new User();
				user.setFirstName(resultSet.getString(1));
				user.setLastName(resultSet.getString(2));
				user.setPhoneNumber(resultSet.getString(3));
				user.setEmailId(resultSet.getString(4));
				
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

}
