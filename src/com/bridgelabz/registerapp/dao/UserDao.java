package com.bridgelabz.registerapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
