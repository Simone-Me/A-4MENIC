package com.efrei.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Signin {

	public void arrayFillVille(ChoiceBox<String> villeField) throws Exception {

		Connection connection = MyConnection.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT DISTINCT villecine FROM cinema ORDER BY villecine");

		ObservableList<String> villeList = FXCollections.observableArrayList();
		while (resultSet.next()) {
			String ville = resultSet.getString("villecine");
			villeList.add(ville);
		}
		villeField.setItems(villeList);
	}

	public void arrayFillCinema(ChoiceBox<String> cinemaField, ChoiceBox<String> villeField, Label infoInsertCode)
			throws Exception {

		String selectedVille = villeField.getValue();

		if ("Ville".equals(selectedVille)) {
			infoInsertCode.setText("Veuillez selectionner la ville de votre etablissement");
		} else if (!selectedVille.isEmpty()) {
			Connection connection = MyConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT DISTINCT nomCine FROM cinema WHERE villecine = '" + selectedVille + "' ORDER BY nomCine");

			ObservableList<String> cinemaList = FXCollections.observableArrayList();

			while (resultSet.next()) {
				String ville = resultSet.getString("nomCine");
				cinemaList.add(ville);
			}
			cinemaField.setItems(cinemaList);
			infoInsertCode.setText("");
		}
	}

	public void checkSignin(ChoiceBox<String> cinemaField, ChoiceBox<String> villeField, TextField username,
			PasswordField password, Label infoInsertCode) throws Exception {
		Connection connection = MyConnection.getConnection();
		Statement statement = connection.createStatement();
		String selectedVille = villeField.getValue();
		String selectedCinema = cinemaField.getValue();
		String user = username.getText();
		String pw = password.getText();

		if ("Ville".equals(selectedVille) || "Cinema".equals(selectedCinema) || user.isEmpty() || pw.isEmpty()) {
			infoInsertCode.setText("Veuillez remplir tous les champs du formulaire");
		} else {
			ResultSet resultSetUser = statement
					.executeQuery("SELECT * FROM utilisateur " + "WHERE username = '" + user + "' LIMIT 1;");

			String pattern = ".*(?:\\d.*?){6,}.*";
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(pw);
			String userCopy = "";
			String userId = "";
			String cinemaId = "";

			while (resultSetUser.next()) {
				userCopy = resultSetUser.getString("username");
			}

			if (user.equals(userCopy)) {
				infoInsertCode.setText("Veuillez choisir un autre identifiant");
			} else if (!m.matches()) {
				infoInsertCode.setText("Veuillez inserir le code fournis a 6 chiffres");
			} else {
				try {
					// this query find the last id present in the BDD
					ResultSet resultId = statement
							.executeQuery("SELECT idUser FROM utilisateur ORDER BY idUser DESC LIMIT 1;");
					while (resultId.next()) {
						userId = resultId.getString("idUser");
					}
					// this query find the cinema id selected in the BDD with protection in case of
					// strange cinema name
					String findCinemaQuery = "SELECT idCinema FROM cinema WHERE nomCine = ? ORDER BY nomCine";
					PreparedStatement findCinemaStmt = connection.prepareStatement(findCinemaQuery);
					findCinemaStmt.setString(1, selectedCinema);
					ResultSet resultIdCinema = findCinemaStmt.executeQuery();
					while (resultIdCinema.next()) {
						cinemaId = resultIdCinema.getString("idCinema");
					}
					// this query add into user the new user
					String insertUserQuery = "INSERT INTO utilisateur (username, password) VALUES ('" + user + "','"
							+ pw + "');";
					PreparedStatement insertUserStmt = connection.prepareStatement(insertUserQuery);
					insertUserStmt.executeUpdate();
					// this query grant admin role the user
					String insertAdminQuery = "INSERT INTO admin (isAdmin, idUser, idCinema) VALUES (1, '" + userId
							+ "','" + cinemaId + "');";
					PreparedStatement insertAdminStmt = connection.prepareStatement(insertAdminQuery);
					insertAdminStmt.executeUpdate();

					infoInsertCode.setText("COMPTE CREE");
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}
}
