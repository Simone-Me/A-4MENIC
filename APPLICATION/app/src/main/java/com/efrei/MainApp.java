package com.efrei;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainApp extends Application {

	private static Stage stg;

	@Override
	public void start(Stage stage) throws Exception {
		stg = stage;
		stage.setResizable(false);
		Parent root = FXMLLoader.load(getClass().getResource("vue/Login.fxml"));

		Scene scene = new Scene(root, 600, 400);

		stage.setTitle("A-4MENIC");
		stage.setScene(scene);
		stage.show();
	}

	public void changeScene(String fxml) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("vue/" + fxml));
		stg.getScene().setRoot(pane);
	}

	public void changeScene(String fxml, double width, double height, boolean resize) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("vue/" + fxml));
		stg.getScene().setRoot(pane);
		stg.setWidth(width);
		stg.setHeight(height);
        stg.setResizable(resize);
        stg.centerOnScreen();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void addTextLabel(Label label, String newLabel) {
		String oldText = label.getText();
		label.setText(oldText + newLabel);
	}

}