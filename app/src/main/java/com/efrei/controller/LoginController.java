package com.efrei.controller;

import java.io.IOException;

import com.efrei.Login;
import com.efrei.MainApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private Button button;

	@FXML
	private Label wrongLogin;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	public void userLogin(ActionEvent event) throws IOException {

		MainApp ma = new MainApp();

		Login login = new Login();
		String user = username.getText();
		String pw = password.getText();

		boolean connected = login.checkLogin(user, pw, wrongLogin);

		if (connected == true) {
			ma.changeScene("MainPage.fxml");
		}

	}

}
