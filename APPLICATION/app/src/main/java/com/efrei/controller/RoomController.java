package com.efrei.controller;

import java.io.IOException;

import com.efrei.MainApp;
import com.efrei.model.Room;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RoomController {

	@FXML
	private Button btnAdd;

	@FXML
	private TableColumn<Room, Integer> capacityRoom;

	@FXML
	private TableColumn<Room, String> nameRoom;

	@FXML
	private TableColumn<Room, Integer> numRoom;

	@FXML
	private TableView<Room> tableView;

	@FXML
	private AnchorPane addPartHide;

	@FXML
	private Button btnConfirmAdd;

	@FXML
	private Button btnConfirmModify;

	@FXML
	private Button btnLogout;

	@FXML
	private Button btnMainPage;

	@FXML
	private Button btnModify;

	@FXML
	private TextField capacityRoomAdd;

	@FXML
	private TextField capacityRoomModify;

	@FXML
	private TextField idRoomModify;

	@FXML
	private AnchorPane modifyPartHide;

	@FXML
	private TextField nameRoomAdd;

	@FXML
	private TextField nameRoomModify;

	@FXML
	private Label wrongModify;

	@FXML
	public void initialize() throws Exception {
		Room rm = new Room(0, "", 0);
		rm.fillTable(tableView, nameRoom, capacityRoom, numRoom);
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
	void showModify(ActionEvent event) throws Exception {
		Room rm = new Room(0, null, 0);
		rm.showModifyArea(modifyPartHide, addPartHide);
	}

	@FXML
	void showAdd(ActionEvent event) {
		Room rm = new Room(0, null, 0);
		rm.showModifyArea(addPartHide, modifyPartHide);
	}

	@FXML
	void confirmRoomAdd(ActionEvent event) throws Exception {
		Room rm = new Room(0, null, 0);
		rm.addRoom(nameRoomAdd, capacityRoomAdd, wrongModify);
	}

	@FXML
	void confirmRoomModify(ActionEvent event) throws Exception {
		Room rm = new Room(0, null, 0);
		rm.modifyRoom(nameRoomModify, capacityRoomModify, idRoomModify, wrongModify);
	}
}