package com.efrei.controller;

import java.io.IOException;

import com.efrei.MainApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class MainPageController {

	@FXML
	private Button btnBooking;

	@FXML
	private Button btnFilm;

	@FXML
	private Button btnLogout;

	@FXML
	private Button btnResult;

	@FXML
	private Button btnRoom;

	@FXML
	void userLogout(ActionEvent event) throws IOException {
		MainApp ma = new MainApp();
		ma.changeScene("Login.fxml");
	}

	@FXML
	void showBooking(MouseEvent event) throws IOException {
	}

	@FXML
	void showFilm(MouseEvent event) throws IOException {
	}

	@FXML
	void showResult(MouseEvent event) throws IOException {
	}

	@FXML
	void showRoom(MouseEvent event) throws IOException {
		MainApp ma = new MainApp();
		ma.changeScene("Room.fxml");
	}

}