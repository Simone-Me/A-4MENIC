package com.efrei.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class Reservation {
	String idReservation;
	String dateDemande;
	String dateReponse;
	String salle;
	String user;
	String admin;
	String commentaire;
	String reponse;

	public ObservableList<Reservation> reservationList;

	public Reservation(String idReservation, String dateDemande, String dateReponse, String salle, String user,
			String admin, String commentaire, String reponse) {
		super();
		this.idReservation = idReservation;
		this.dateDemande = dateDemande;
		this.dateReponse = dateReponse;
		this.salle = salle;
		this.user = user;
		this.admin = admin;
		this.commentaire = commentaire;
		this.reponse = reponse;
	}

	public String getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(String idReservation) {
		this.idReservation = idReservation;
	}

	public String getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(String dateDemande) {
		this.dateDemande = dateDemande;
	}

	public String getDateReponse() {
		return dateReponse;
	}

	public void setDateReponse(String dateReponse) {
		this.dateReponse = dateReponse;
	}

	public String getSalle() {
		return salle;
	}

	public void setSalle(String salle) {
		this.salle = salle;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public void arrayFillResaNumber(ChoiceBox<String> idBookSelect) throws Exception {
		Connection connection = MyConnection.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSetRoom = statement
				.executeQuery("SELECT * FROM reservationsalle WHERE reponse IS null ORDER BY dateResa");

		ObservableList<String> resaList = FXCollections.observableArrayList();
		while (resultSetRoom.next()) {
			String idReservationAdd = resultSetRoom.getString("idReservation");
			resaList.add(idReservationAdd);
		}
		idBookSelect.setItems(resaList);
		System.out.println(resaList);
	}

	public void fillTableWait(TableView<Reservation> tableWait, TableColumn<Reservation, String> idResaWait,
			TableColumn<Reservation, String> dateWait, TableColumn<Reservation, String> roomWait,
			TableColumn<Reservation, String> userWait) throws Exception {

		idResaWait.setCellValueFactory(new PropertyValueFactory<>("idReservation"));
		dateWait.setCellValueFactory(new PropertyValueFactory<>("dateDemande"));
		roomWait.setCellValueFactory(new PropertyValueFactory<>("Salle"));
		userWait.setCellValueFactory(new PropertyValueFactory<>("User"));

		String idResaBook = "";
		String dateWaitBook = "";
		String roomWaitBook = "";
		String userWaitBook = "";

		Connection connection = MyConnection.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSetSeance = statement.executeQuery(
				"SELECT * FROM reservationsalle NATURAL JOIN salle NATURAL JOIN utilisateur WHERE reponse IS NULL ORDER BY dateResa");
		ObservableList<Reservation> reservationList = FXCollections.observableArrayList();
		while (resultSetSeance.next()) {
			idResaBook = resultSetSeance.getString("idReservation");
			dateWaitBook = resultSetSeance.getString("dateResa");
			roomWaitBook = resultSetSeance.getString("nomSalle");
			userWaitBook = resultSetSeance.getString("username");

			reservationList
					.add(new Reservation(idResaBook, dateWaitBook, null, roomWaitBook, userWaitBook, null, null, null));
		}
		tableWait.setItems(reservationList);
	}

	public void fillTable(TableView<Reservation> tableBook, TableColumn<Reservation, String> roomBook,
			TableColumn<Reservation, String> userBook, TableColumn<Reservation, String> askBook,
			TableColumn<Reservation, String> answerBook, TableColumn<Reservation, String> replyBook,
			TableColumn<Reservation, String> adminBook, TableColumn<Reservation, String> commentBook,
			TableColumn<Reservation, String> idBook) throws Exception {

		roomBook.setCellValueFactory(new PropertyValueFactory<>("salle"));
		userBook.setCellValueFactory(new PropertyValueFactory<>("user"));
		askBook.setCellValueFactory(new PropertyValueFactory<>("dateDemande"));
		answerBook.setCellValueFactory(new PropertyValueFactory<>("dateReponse"));
		replyBook.setCellValueFactory(new PropertyValueFactory<>("reponse"));
		commentBook.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
		adminBook.setCellValueFactory(new PropertyValueFactory<>("admin"));
		idBook.setCellValueFactory(new PropertyValueFactory<>("idReservation"));

		String idResa = "";
		String dateBook = "";
		String dateReply = "";
		String idUserBook = "";
		String idSalleBook = "";
		String idAdminBook = "";
		String commentaireBook = "";
		String reponseBook = "";
		String nameUserBook = "";

		Connection connection = MyConnection.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSetSeance = statement.executeQuery(
				"SELECT * FROM reservationsalle NATURAL JOIN salle NATURAL JOIN utilisateur WHERE reponse IS NOT NULL");
		ObservableList<Reservation> reservationList = FXCollections.observableArrayList();
		while (resultSetSeance.next()) {
			idResa = resultSetSeance.getString("idReservation");
			dateBook = resultSetSeance.getString("dateResa");
			dateReply = resultSetSeance.getString("dateReponse");
			idSalleBook = resultSetSeance.getString("nomSalle");
			commentaireBook = resultSetSeance.getString("commentaire");
			reponseBook = resultSetSeance.getString("reponse");

			idUserBook = resultSetSeance.getString("idUser");
			idAdminBook = resultSetSeance.getString("idAdmin");
			try {
				Connection connection2 = MyConnection.getConnection();
				Statement statement2 = connection2.createStatement();
				ResultSet resultSetSeance2 = statement2
						.executeQuery("SELECT * FROM utilisateur WHERE idUser = '" + idUserBook + "'");
				while (resultSetSeance2.next()) {
					nameUserBook = resultSetSeance.getString("username");

				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}

			reservationList.add(new Reservation(idResa, dateBook, dateReply, idSalleBook, nameUserBook, idAdminBook,
					commentaireBook, reponseBook));
		}
		tableBook.setItems(reservationList);
	}

	public void showComArea(AnchorPane areaSelected) {
		areaSelected.setVisible(!areaSelected.isVisible());
	}

	public void confirmRefuse(ChoiceBox<String> idBookSelect, TextField commentaire, Label wrongBook) throws Exception {
		String idBooking = idBookSelect.getValue();
		String comBooking = commentaire.getText();
		int userId = SessionManager.getInstance().getUserId();

		Connection connection = MyConnection.getConnection();
		Statement statement = connection.createStatement();

		if ("".equals(idBooking) || "".equals(comBooking)) {
			wrongBook.setText("Remplissez les champs pour repondre ...");
		} else {
			LocalDate today = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String formattedDate = today.format(formatter);

			try {
				String insertUserQuery = "UPDATE reservationsalle SET dateReponse = '" + formattedDate
						+ "', reponse = 'NO', commentaire = '" + comBooking + "', idAdmin = '" + userId
						+ "' WHERE idReservation ='" + idBooking + "';";
				System.out.println(insertUserQuery);
				PreparedStatement insertSeanceStmt = connection.prepareStatement(insertUserQuery);
				insertSeanceStmt.executeUpdate();
				wrongBook.setText("Reponse ajoutee, recharger la page");
			} catch (Exception e) {
				System.err.println(e.getMessage() + "line 178");
				wrongBook.setText("error dans l'ajout de la Seance");
			}
		}
	}

	public void confirmResa(ChoiceBox<String> idBookSelect, Label wrongBook) throws Exception {
		String idBooking = idBookSelect.getValue();
		int userId = SessionManager.getInstance().getUserId();

		Connection connection = MyConnection.getConnection();
		Statement statement = connection.createStatement();

		if ("".equals(idBooking)) {
			wrongBook.setText("Choisir la reservation pour y repondre ...");
		} else {
			LocalDate today = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String formattedDate = today.format(formatter);

			try {
				String insertUserQuery = "UPDATE reservationsalle SET dateReponse = '" + formattedDate
						+ "', reponse = 'YES', idAdmin = '" + userId
						+ "' WHERE idReservation ='" + idBooking + "';";
				System.out.println(insertUserQuery);
				PreparedStatement insertSeanceStmt = connection.prepareStatement(insertUserQuery);
				insertSeanceStmt.executeUpdate();
				wrongBook.setText("Reponse ajoutee, recharger la page");
			} catch (Exception e) {
				System.err.println(e.getMessage());
				wrongBook.setText("error dans l'ajout de la Seance");
			}
		}
	}
}