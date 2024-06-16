package com.efrei.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

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
		System.out.println(selectedVille);

		if ("Ville".equals(selectedVille)) {
			infoInsertCode.setText("Veuillez selectionner la ville de votre etablissement");
			text = true;
		} else if (!selectedVille.isEmpty()) {
			System.out.println("working");
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
}
