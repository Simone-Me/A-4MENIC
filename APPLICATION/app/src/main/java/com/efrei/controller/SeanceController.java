package com.efrei.controller;

import java.io.IOException;

import com.efrei.MainApp;
import com.efrei.model.Seance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class SeanceController {

	@FXML
	private Button btnLogout;

	@FXML
	private Button btnMainPage;

	@FXML
	private Button confirmFilm;

	@FXML
	private ChoiceBox<String> filmField;

	@FXML
	private TableColumn<Seance, String> filmSeance;

	@FXML
	private ChoiceBox<String> hourField;

	@FXML
	private TableColumn<Seance, String> hourSeance;

	@FXML
	private ChoiceBox<String> roomField;

	@FXML
	private TableColumn<Seance, String> roomSeance;

	@FXML
	private TableView<Seance> tableViewFilm;

	@FXML
	private Label wrongSelectFilm;

	@FXML
	public void initialize() throws Exception {
		Seance sn = new Seance(null, null, null);
		sn.arrayFillRoom(roomField);
		sn.arrayFillFilm(filmField);
		sn.fillTable(tableViewFilm, filmSeance, hourSeance, roomSeance);
		roomField.setValue("Salle");
		hourField.setValue("19:30");
		filmField.setValue("Moonlight");
	}

	@FXML
	void directHomePage(ActionEvent event) throws IOException {
		MainApp ma = new MainApp();
		ma.changeScene("MainPage.fxml");
	}

	@FXML
	void userLogout(ActionEvent event) throws IOException {
		MainApp ma = new MainApp();
		ma.changeScene("Login.fxml");
	}

	@FXML
	void confirmAddSeance(ActionEvent event) throws Exception {
		Seance sn = new Seance(null, null, null);
		sn.addSeance(roomField, hourField, filmField, wrongSelectFilm);
	}

	@FXML
	void checkHourAvailable(MouseEvent event) throws Exception {
		Seance sn = new Seance(null, null, null);
		sn.arrayFillHour(hourField, roomField, wrongSelectFilm);
	}

}
