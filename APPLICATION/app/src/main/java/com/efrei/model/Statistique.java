package com.efrei.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class Statistique {

	public void addDataRoom(StackedBarChart<String, Integer> barChartRoom) throws Exception {
		XYChart.Series<String, Integer> seats = new Series<String, Integer>();
		int cinemaId = SessionManager.getInstance().getCinemaId();
		Connection connection = MyConnection.getConnection();
		Statement statement = connection.createStatement();
		// transform the room's id in its name
		try {

			ResultSet resultSetRoom = statement.executeQuery(
					"SELECT sum(nbPlace) as totale, nomSalle FROM `ticket` NATURAL join seance NATURAL JOIN salle WHERE idCinema = '"
							+ cinemaId + "' GROUP BY idSalle;");
			while (resultSetRoom.next()) {
				String nomSalle = resultSetRoom.getString("nomSalle");
				int capacite = resultSetRoom.getInt("totale");

				seats.setName("Places vendu");
				seats.getData().add(new XYChart.Data<>(nomSalle, capacite));
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		barChartRoom.getData().add(seats);
	}

	public void addDataFllm(StackedBarChart<String, Integer> barChartRoom) throws Exception {
		XYChart.Series<String, Integer> film = new Series<String, Integer>();
		barChartRoom.getData().clear();
		int cinemaId = SessionManager.getInstance().getCinemaId();
		Connection connection = MyConnection.getConnection();
		Statement statement = connection.createStatement();
		// transform the room's id in its name
		try {
			ResultSet resultSetRoom = statement.executeQuery(
					"SELECT sum(nbPlace) as totale, nomFilm FROM `ticket` NATURAL join seance NATURAL JOIN film NATURAL JOIN salle WHERE idCinema = '"
							+ cinemaId + "' GROUP BY nomFilm;");
			while (resultSetRoom.next()) {
				String nomFilm = resultSetRoom.getString("nomFilm");
				int capacite = resultSetRoom.getInt("totale");
				
				film.setName("Film vu");
				film.getData().add(new XYChart.Data<>(nomFilm, capacite));
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		barChartRoom.getData().add(film);
	}
}
