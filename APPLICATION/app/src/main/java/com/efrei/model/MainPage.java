package com.efrei.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainPage {

	@FXML
	public void addNameAdmin(Label nameAdmin) throws Exception {
		int userId = SessionManager.getInstance().getUserId();
		System.out.println(userId);
		String nomUser = "";

		Connection connection = MyConnection.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement
				.executeQuery("SELECT * FROM admin NATURAL JOIN utilisateur where idUser = '" + userId + "'");
		while (resultSet.next()) {
			nomUser = resultSet.getString("username");
			System.out.println(nomUser);
			nameAdmin.setText(nomUser);
		}
	}
}