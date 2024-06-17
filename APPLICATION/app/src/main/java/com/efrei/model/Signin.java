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

	public void arrayFillCinema(ChoiceBox<String> cinemaField, ChoiceBox<String> villeField, boolean text,
			Label infoInsertCode) throws Exception {

		String selectedVille = villeField.getValue();

		if ("Ville".equals(selectedVille)) {
			infoInsertCode.setText("Veuillez selectionner la ville de votre etablissement");
			text = true;
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
			text = false;
		}

	}

	public void checkSignin(ChoiceBox<String> cinemaField, ChoiceBox<String> villeField, TextField username,
			PasswordField password, boolean text, Label infoInsertCode) throws Exception {
		Connection connection = MyConnection.getConnection();
		Statement statement = connection.createStatement();
		String selectedVille = villeField.getValue();
		String selectedCinema = cinemaField.getValue();
		String user = username.getText();
		String pw = password.getText();

		if ("Ville".equals(selectedVille) || "Cinema".equals(selectedCinema) || user.isEmpty() || pw.isEmpty()) {
			infoInsertCode.setText("Veuillez remplir tous les champs du formulaire");
		} else {
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM utilisateur " + "WHERE username = '" + user + "' LIMIT 1;");

			String pattern = ".*(?:\\d.*?){6,}.*";
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(pw);
			String userCopy = "";

			while (resultSet.next()) {
				userCopy = resultSet.getString("username");
			}

			if (user.equals(userCopy)) {
				infoInsertCode.setText("Veuillez choisir un autre identifiant");
			} else if (!m.matches()) {
				infoInsertCode.setText("Veuillez inserir le code fournis a 6 chiffres");
			} else {
				infoInsertCode.setText("COMPTE CREE");
				String insertAdminQuery = "INSERT INTO utilisateur (username, password) VALUES ('" + user + "','" + pw
						+ "')";
				PreparedStatement insertAdminStmt = connection.prepareStatement(insertAdminQuery);
				insertAdminStmt.executeUpdate();

			}
		}

	}

}
