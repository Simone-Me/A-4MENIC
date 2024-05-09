package com.efrei.controller;

import java.io.IOException;

import com.efrei.MainApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainPageController {

	@FXML
	private Button btnLogout;

	@FXML
	void userLogout(ActionEvent event) throws IOException {
		MainApp ma = new MainApp();

		ma.changeScene("Login.fxml");
	}

	public void initialize() {
	}
}