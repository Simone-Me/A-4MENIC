package com.efrei;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.efrei.model.MyConnection;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {

	@FXML
	private Button bntLogin;

	@FXML
	private Button bntSignin;

	@FXML
	private PasswordField password;

	@FXML
	private TextField username;

	@FXML
	private Label wrongLogin;

	public boolean checkLogin(String username, String password, Label wrongLogin) throws Exception {
		boolean connected = false;
		String user = "";
		String pass = "";

		Connection connection = MyConnection.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM utilisateur WHERE username = '" + username + "'");
		while (resultSet.next()) {
			user = resultSet.getString("username");
			pass = resultSet.getString("password");
		}

		if (username.equals(user) && password.equals(pass)) {
			wrongLogin.setText("Success !");
			connected = true;
		} else if (username.isEmpty() && password.isEmpty()) {
			wrongLogin.setText("Remplissez les champs...");
		} else {
			wrongLogin.setText("Infos incorrects !");
		}
		return connected;
	}
}