package com.efrei;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {

	@FXML
	private Button button;

	@FXML
	private Label wrongLogin;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	public boolean checkLogin(String username, String password, Label wrongLogin) throws IOException {
		
		boolean connected = false;
		
		if (username.equals("admin") && password.equals("admin")) {
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