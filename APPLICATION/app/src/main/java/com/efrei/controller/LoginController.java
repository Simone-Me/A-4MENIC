package com.efrei.controller;

import com.efrei.Login;
import com.efrei.MainApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

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

	@FXML
	public void userLogin(ActionEvent event) throws Exception {

		MainApp ma = new MainApp();

		Login login = new Login();
		String user = username.getText();
		String pw = password.getText();

		boolean connected = login.checkLogin(user, pw, wrongLogin);

		if (connected) {
			ma.changeScene("MainPage.fxml");
		}

	}

}
