package com.efrei.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class SigninController {

	@FXML
	private Button bntSignin;

	@FXML
	private AnchorPane btnLogin;

	@FXML
	private ChoiceBox<?> cinemaField;

	@FXML
	private PasswordField password;

	@FXML
	private TextField username;

	@FXML
	private ChoiceBox<?> villeField;

	@FXML
	private Label wrongSignin;

	@FXML
	void userLogin(ActionEvent event) {

	}

	@FXML
	void userLoginEnter(KeyEvent event) {

	}

}
