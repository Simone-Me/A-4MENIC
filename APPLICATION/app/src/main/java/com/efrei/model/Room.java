package com.efrei.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class Room {

	int capacity;
	int idRoom;
	String nameRoom;

	public ObservableList<Room> roomList;

	public Room(int idRoom, String nameRoom, int capacity) {
		super();
		this.capacity = capacity;
		this.nameRoom = nameRoom;
		this.idRoom = idRoom;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getNameRoom() {
		return nameRoom;
	}

	public void setNameRoom(String nameRoom) {
		this.nameRoom = nameRoom;
	}

	public int getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}

	// method to show all the rooms of the cinema
	public void fillTable(TableView<Room> tableView, TableColumn<Room, String> nameRoom,
			TableColumn<Room, Integer> capacityRoom, TableColumn<Room, Integer> numRoom) throws Exception {

		numRoom.setCellValueFactory(new PropertyValueFactory<>("idRoom"));
		nameRoom.setCellValueFactory(new PropertyValueFactory<>("nameRoom"));
		capacityRoom.setCellValueFactory(new PropertyValueFactory<>("capacity"));

		int cinemaId = SessionManager.getInstance().getCinemaId();
		int capaciteSalle;
		int numSalle;
		String nomSalle;

		Connection connection = MyConnection.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement
				.executeQuery("SELECT * FROM salle NATURAL JOIN cinema where idCinema = '" + cinemaId + "'");
		ObservableList<Room> roomList = FXCollections.observableArrayList();
		while (resultSet.next()) {
			numSalle = resultSet.getInt("idSalle");
			capaciteSalle = resultSet.getInt("capacite");
			nomSalle = resultSet.getString("nomSalle");
			roomList.add(new Room(numSalle, nomSalle, capaciteSalle));
		}
		tableView.setItems(roomList);
	}

	// function to show only modify or add form and not together
	public void showModifyArea(AnchorPane areaSelected, AnchorPane areaHide) {
		if (areaSelected.isVisible() && areaHide.isVisible()) {
			areaSelected.setVisible(false);
			areaHide.setVisible(false);
		} else if (!areaSelected.isVisible()) {
			areaSelected.setVisible(true);
			areaHide.setVisible(false);
		} else {
			areaSelected.setVisible(false);
		}
	}

	// function to add a room in the DB
	public void addRoom(TextField nameRoomAdd, TextField capacityRoomAdd, Label wrongAdd) throws Exception {
		String capacityRoom = capacityRoomAdd.getText();
		String selectedRoom = nameRoomAdd.getText();
		int cinemaId = SessionManager.getInstance().getCinemaId();
		Connection connection = MyConnection.getConnection();

		if ("".equals(capacityRoom) || "".equals(selectedRoom)) {
			wrongAdd.setText("Remplissez les champs pour ajouter la salle ...");
		} else {
			String insertUserQuery = "INSERT INTO salle (nomSalle, capacite, idCinema) VALUES ('" + selectedRoom + "','"
					+ capacityRoom + "','" + cinemaId + "');";
			PreparedStatement insertUserStmt = connection.prepareStatement(insertUserQuery);
			insertUserStmt.executeUpdate();
			wrongAdd.setText("Salle ajoutee, recharger la page");
		}
	}

	public void modifyRoom(TextField nameRoomModify, TextField capacityRoomModify, TextField idRoomModify,
			Label wrongAdd) throws Exception {
		String capacityRoom = capacityRoomModify.getText();
		String selectedRoom = nameRoomModify.getText();
		String idRoom = idRoomModify.getText();
		int cinemaId = SessionManager.getInstance().getCinemaId();

		if ("".equals(capacityRoom) || "".equals(selectedRoom) || "".equals(idRoom)) {
			wrongAdd.setText("Remplissez les champs pour ajouter la salle ...");
		} else {
			Connection connection = MyConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultRoom = statement.executeQuery("SELECT * FROM salle where idSalle = '" + idRoom + "';");
			if (resultRoom.next()) {
				String modifyUserQuery = "UPDATE salle SET nomSalle = '" + selectedRoom + "', capacite = '"
						+ capacityRoom + "' WHERE idSalle ='" + idRoom + "';";

				PreparedStatement insertUserStmt = connection.prepareStatement(modifyUserQuery);
				insertUserStmt.executeUpdate();
				wrongAdd.setText("Salle modifiee, recharger la page");
			} else {
				wrongAdd.setText("Le numero de la salle est erronee...");
			}

		}
	}
}