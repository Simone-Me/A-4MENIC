package com.efrei.controller;

import com.efrei.Login;
import com.efrei.MainApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class LoginController {

	@FXML
	private Button bntInsertCode;

	@FXML
	private Button bntLogin;

	@FXML
	private Button bntSignin;

	@FXML
	private Label infoInsertCode;

	@FXML
	private PasswordField password;

	@FXML
	private TextField username;

	@FXML
	private Label wrongLogin;

	@FXML
	private AnchorPane signinArea;

	boolean textInfoArea = false;
	boolean codeInsertArea = false;

	public void connectionCheck() throws Exception {
		MainApp ma = new MainApp();
		Login login = new Login();
		String user = username.getText();
		String pw = password.getText();
		boolean connected = login.checkLogin(user, pw, wrongLogin);

		if (connected) {
			ma.changeScene("MainPage.fxml");
		}
	}

	@FXML
	public void userLogin(ActionEvent event) throws Exception {
		connectionCheck();
	}

	@FXML
	void userLoginEnter(KeyEvent event) throws Exception {
		if (event.getCode() == KeyCode.ENTER) {
			connectionCheck();
		}
	}

	@FXML
	public void showCodeArea(ActionEvent event) throws Exception {
		Login login = new Login();
		boolean infoArea = login.showSigninArea(codeInsertArea, signinArea);

		if (infoArea) {
			codeInsertArea = true;
		} else {
			codeInsertArea = false;
		}
	}

	@FXML
	void userSignin(ActionEvent event) throws Exception {
		MainApp ma = new MainApp();
		ma.changeScene("Signin.fxml");
	}

	@FXML
	void showInfoArea(MouseEvent event) throws Exception {
		Login login = new Login();
		boolean infoArea = login.showTextArea(textInfoArea, infoInsertCode);

		if (infoArea) {
			textInfoArea = true;
		} else {
			textInfoArea = false;
		}
	}

}
