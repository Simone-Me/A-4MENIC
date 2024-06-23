package com.efrei.controller;

import java.io.IOException;

import com.efrei.MainApp;
import com.efrei.model.MainPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	private Label nameAdmin;

	public void initialize() throws Exception {
		MainPage mp = new MainPage();
		mp.addNameAdmin(nameAdmin);
	}

	@FXML
	void userLogout(ActionEvent event) throws IOException {
		MainApp ma = new MainApp();
		ma.changeScene("Login.fxml");
	}

	@FXML
	void showBooking(MouseEvent event) throws IOException {
		MainApp ma = new MainApp();
		ma.changeScene("Reservation.fxml");
	}

	@FXML
	void showFilm(MouseEvent event) throws IOException {
		MainApp ma = new MainApp();
		ma.changeScene("Seance.fxml");
	}

	@FXML
	void showResult(MouseEvent event) throws IOException {
		MainApp ma = new MainApp();
		ma.changeScene("Statistique.fxml");
	}

	@FXML
	void showRoom(MouseEvent event) throws IOException {
		MainApp ma = new MainApp();
		ma.changeScene("Room.fxml");
	}

}