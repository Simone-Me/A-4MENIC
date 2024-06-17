package com.efrei.controller;

import java.io.IOException;

import com.efrei.MainApp;
import com.efrei.model.Signin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SigninController {

	@FXML
	private Button bntSignin;

	@FXML
	private AnchorPane loginSpace;

	@FXML
	private ChoiceBox<String> cinemaField;

	@FXML
	private PasswordField password;

	@FXML
	private TextField username;

	@FXML
	private ChoiceBox<String> villeField;

	@FXML
	private Label wrongSignin;

	boolean textInfoArea = false;

	@FXML
	void userSignin(ActionEvent event) throws Exception {
		Signin sn = new Signin();
		sn.checkSignin(cinemaField, villeField, username, password, textInfoArea, wrongSignin);
	}

	@FXML
	void userLoginEnter(KeyEvent event) {

	}

	@FXML
	public void initialize() throws Exception {
		Signin sn = new Signin();
		sn.arrayFillVille(villeField);
		cinemaField.setValue("Cinema");
		villeField.setValue("Ville");
	}

	@FXML
	void addCinemaCity(MouseEvent event) throws Exception {
		Signin sn = new Signin();
		sn.arrayFillCinema(cinemaField, villeField, textInfoArea, wrongSignin);
	}

	@FXML
	void directLogin(MouseEvent event) throws IOException {
		MainApp ma = new MainApp();
		ma.changeScene("Login.fxml");
	}
}
