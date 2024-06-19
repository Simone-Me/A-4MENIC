package com.efrei.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Seance {
	int idSeance;
	String room;
	String hour;
	String film;

	public ObservableList<Seance> seanceList;

	public Seance(String room, String hour, String film) {
		super();
		this.room = room;
		this.hour = hour;
		this.film = film;
	}

	public int getIdSeance() {
		return idSeance;
	}

	public void setIdSeance(int idSeance) {
		this.idSeance = idSeance;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getFilm() {
		return film;
	}

	public void setFilm(String film) {
		this.film = film;
	}

	public void arrayFillRoom(ChoiceBox<String> roomField) throws Exception {
		int cinemaId = SessionManager.getInstance().getCinemaId();
		Connection connection = MyConnection.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSetRoom = statement
				.executeQuery("SELECT DISTINCT * FROM salle NATURAL JOIN cinema WHERE idCinema = '" + cinemaId
						+ "'ORDER BY nomSalle");

		ObservableList<String> roomList = FXCollections.observableArrayList();
		while (resultSetRoom.next()) {
			String room = resultSetRoom.getString("nomSalle");
			roomList.add(room);
		}
		roomField.setItems(roomList);
	}

	public void arrayFillHour(ChoiceBox<String> hourField, ChoiceBox<String> roomField, Label wrongSelectFilm)
			throws Exception {
		ObservableList<String> hourList = FXCollections.observableArrayList();
		hourList.add("21:30");
		hourList.add("19:00");

		String nameRoom = roomField.getValue();
		System.out.println(nameRoom);
		Connection connection = MyConnection.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSetRoom = statement
				.executeQuery("SELECT DISTINCT * FROM seance NATURAL JOIN salle WHERE nomSalle = '" + nameRoom
						+ "'ORDER BY nomSalle");
		while (resultSetRoom.next()) {
			String hourSeance = resultSetRoom.getString("horaire");
			hourList.remove(hourSeance);
			if ("21:30".equals(hourSeance)) {
				wrongSelectFilm.setText("Horaire 21:30 deja choisi");
			} else if ("19:00".equals(hourSeance)) {
				wrongSelectFilm.setText("Horaire 19:00 deja choisi");
			}

		}

		hourField.setItems(hourList);
	}

	public void arrayFillFilm(ChoiceBox<String> filmField) throws Exception {
		Connection connection = MyConnection.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSetRoom = statement.executeQuery("SELECT DISTINCT * FROM film ORDER BY nomFilm");

		ObservableList<String> filmList = FXCollections.observableArrayList();
		while (resultSetRoom.next()) {
			String film = resultSetRoom.getString("nomFilm");
			filmList.add(film);
		}
		filmField.setItems(filmList);
	}

	public void addSeance(ChoiceBox<String> roomField, ChoiceBox<String> hourField, ChoiceBox<String> filmField,
			Label wrongSelectFilm) throws Exception {
		String roomSeance = roomField.getValue();
		String hourSeance = hourField.getValue();
		String filmSeance = filmField.getValue();
		int cinemaId = SessionManager.getInstance().getCinemaId();
		Connection connection = MyConnection.getConnection();
		Statement statement = connection.createStatement();

		if ("".equals(room) || "".equals(hour) || "".equals(film)) {
			wrongSelectFilm.setText("Remplissez les champs pour ajouter la seance ...");
		} else {
			String idSalle = "";
			String idFilm = "";

			// transform the room's name in its id
			try {
				ResultSet resultSetRoom = statement
						.executeQuery("SELECT * FROM salle NATURAL JOIN cinema WHERE nomSalle = '" + roomSeance
								+ "' AND idCinema = '" + cinemaId + "'");
				while (resultSetRoom.next()) {
					idSalle = resultSetRoom.getString("idSalle");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			// transform the film's name in its id
			try {
				ResultSet resultSetFilm = statement
						.executeQuery("SELECT * FROM film NATURAL JOIN cinema WHERE nomFilm = '" + filmSeance + "'");
				while (resultSetFilm.next()) {
					idFilm = resultSetFilm.getString("idFilm");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			try {
				String insertUserQuery = "INSERT INTO seance (idFilm, idSalle, horaire) VALUES ('" + idFilm + "','"
						+ idSalle + "','" + hourSeance + "');";
				PreparedStatement insertSeanceStmt = connection.prepareStatement(insertUserQuery);
				insertSeanceStmt.executeUpdate();
				wrongSelectFilm.setText("Seance ajoutee, recharger la page");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				wrongSelectFilm.setText("error dans l'ajout de la Seance");
			}

		}
	}

	public void fillTable(TableView<Seance> tableViewFilm, TableColumn<Seance, String> filmSeance,
			TableColumn<Seance, String> hourSeance, TableColumn<Seance, String> roomSeance) throws Exception {

		filmSeance.setCellValueFactory(new PropertyValueFactory<>("film"));
		hourSeance.setCellValueFactory(new PropertyValueFactory<>("hour"));
		roomSeance.setCellValueFactory(new PropertyValueFactory<>("room"));

		int cinemaId = SessionManager.getInstance().getCinemaId();
		String heureSeance = "";
		String idFilm = "";
		String idSalle = "";
		String nomSalle = "";
		String nomFilm = "";

		Connection connection = MyConnection.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSetSeance = statement
				.executeQuery("SELECT * FROM seance NATURAL JOIN cinema where idCinema = '" + cinemaId + "'");
		ObservableList<Seance> seanceList = FXCollections.observableArrayList();
		while (resultSetSeance.next()) {
			idSalle = resultSetSeance.getString("idSalle");
			heureSeance = resultSetSeance.getString("horaire");
			idFilm = resultSetSeance.getString("idFilm");

			Connection connection2 = MyConnection.getConnection();
			Statement statement2 = connection2.createStatement();
			// transform the room's id in its name
			try {

				ResultSet resultSetRoom = statement2
						.executeQuery("SELECT * FROM salle WHERE idSalle = '" + idSalle + "'");
				while (resultSetRoom.next()) {
					nomSalle = resultSetRoom.getString("nomSalle");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			// transform the film's id in its name
			try {
				ResultSet resultSetFilm = statement2.executeQuery("SELECT * FROM film WHERE idFilm = '" + idFilm + "'");
				while (resultSetFilm.next()) {
					nomFilm = resultSetFilm.getString("nomFilm");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			seanceList.add(new Seance(nomSalle, heureSeance, nomFilm));
		}
		tableViewFilm.setItems(seanceList);
	}

}