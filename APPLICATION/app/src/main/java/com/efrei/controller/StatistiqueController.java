package com.efrei.controller;

import java.io.IOException;

import com.efrei.MainApp;
import com.efrei.model.Statistique;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Button;

public class StatistiqueController {

	@FXML
	private StackedBarChart<String, Integer> barChartRoom;

	@FXML
	private Button btnLogout;

	@FXML
	private Button btnMainPage;
	
    @FXML
    private Button filmSelect;

    @FXML
    private Button placeSelect;
	
	@FXML
	public void initialize() throws Exception {
		Statistique s = new Statistique();
		s.addDataRoom(barChartRoom);
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
    void showFilm(ActionEvent event) throws Exception {
    	barChartRoom.getData().clear();
    	Statistique s = new Statistique();
		s.addDataFllm(barChartRoom);
    }

    @FXML
    void showPlace(ActionEvent event) throws Exception {
    	barChartRoom.getData().clear();
    	Statistique s = new Statistique();
		s.addDataRoom(barChartRoom);
    }


}
