package com.efrei.controller;

import java.io.IOException;

import com.efrei.MainApp;
import com.efrei.model.Reservation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ReservationController {

	@FXML
	private Button acceptBook;

	@FXML
	private Button btnLogout;

	@FXML
	private Button btnMainPage;

	@FXML
	private Button confirmCom;

	@FXML
	private Button refuseBook;

	@FXML
	private Label wrongBook;

	@FXML
	private AnchorPane textHide;

	@FXML
	private TextField commentaire;

	@FXML
	private ChoiceBox<String> idBookSelect;

	@FXML
	private TableColumn<Reservation, String> answerBook;

	@FXML
	private TableColumn<Reservation, String> askBook;

	@FXML
	private TableColumn<Reservation, String> commentBook;

	@FXML
	private TableColumn<Reservation, String> dateWait;

	@FXML
	private TableColumn<Reservation, String> idResaWait;

	@FXML
	private TableColumn<Reservation, String> replyBook;

	@FXML
	private TableColumn<Reservation, String> roomBook;
	
	@FXML
	private TableColumn<Reservation, String> adminBook;
	
	@FXML
	private TableColumn<Reservation, String> idBook;

	@FXML
	private TableColumn<Reservation, String> roomWait;

	@FXML
	private TableColumn<Reservation, String> userBook;

	@FXML
	private TableColumn<Reservation, String> userWait;

	@FXML
	private TableView<Reservation> tableBook;

	@FXML
	private TableView<Reservation> tableWait;

	@FXML
	public void initialize() throws Exception {
		Reservation r = new Reservation(null, null, null, null, null, null, null, null);
		r.arrayFillResaNumber(idBookSelect);
		r.fillTableWait(tableWait, idResaWait, dateWait, roomWait, userWait);
		r.fillTable(tableBook, roomBook, userBook, askBook, answerBook, replyBook, adminBook, commentBook, idBook);
	}

	@FXML
	void directHomePage(ActionEvent event) throws IOException {
		MainApp ma = new MainApp();
		ma.changeScene("MainPage.fxml");
	}

	@FXML
	void refuseBook(ActionEvent event) {
		Reservation r = new Reservation(null, null, null, null, null, null, null, null);
		r.showComArea(textHide);
	}

	@FXML
	void confirmBook(ActionEvent event) throws Exception {
		Reservation r = new Reservation(null, null, null, null, null, null, null, null);
		r.confirmResa(idBookSelect, wrongBook);;
	}

	@FXML
	void confirmCom(ActionEvent event) throws Exception {
		Reservation r = new Reservation(null, null, null, null, null, null, null, null);
		r.confirmRefuse(idBookSelect, commentaire, wrongBook);
	}

	@FXML
	void userLogout(ActionEvent event) throws IOException {
		MainApp ma = new MainApp();
		ma.changeScene("Login.fxml");
	}

}
