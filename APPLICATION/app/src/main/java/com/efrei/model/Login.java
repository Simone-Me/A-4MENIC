package com.efrei.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.efrei.MainApp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Login {

	public boolean checkLogin(String username, String password, Label wrongLogin) throws Exception {
		boolean connected = false;
		String user = "";
		String pass = "";
		Integer userId = 0;
		Integer cinemaId = 0;

		Connection connection = MyConnection.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement
				.executeQuery("SELECT * FROM admin NATURAL JOIN utilisateur where username = '" + username + "'");
		while (resultSet.next()) {
			user = resultSet.getString("username");
			pass = resultSet.getString("password");
			userId = resultSet.getInt("idUser");
			cinemaId = resultSet.getInt("idCinema");
		}

		if (username.equals(user) && password.equals(pass)) {
			SessionManager.getInstance().setUserId(userId);
			SessionManager.getInstance().setUsername(user);
			SessionManager.getInstance().setCinemaId(cinemaId);
			wrongLogin.setText("Success !");
			connected = true;
		} else if (username.isEmpty() && password.isEmpty()) {
			wrongLogin.setText("Remplissez les champs...");
		} else {
			wrongLogin.setText("Infos incorrects !");
		}
		return connected;
	}

	public boolean showTextArea(boolean text, Label infoInsertCode) throws Exception {
		if (!text) {
			infoInsertCode.setText("Veuillez inserer le code a six chiffres recu. Ex. 000000");
			text = true;
		} else {
			infoInsertCode.setText("");
			text = false;
		}
		return text;
	}

	public boolean showSigninArea(boolean area, AnchorPane signinArea) throws Exception {
		if (!area) {
			signinArea.setVisible(true);
			area = true;
		} else {
			signinArea.setVisible(false);
			area = false;
		}
		return area;
	}

	public void checkSignin(TextField codePassword, Label infoInsertCode) throws Exception {
		String pw = codePassword.getText();

		if (pw.isEmpty()) {
			infoInsertCode.setText("Veuillez remplir le code fourni");
		} else {
			String pattern = ".*(?:\\d.*?){6,}.*";
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(pw);

			if (!m.matches()) {
				infoInsertCode.setText("ERR : Inserez le code fournis a 6 chiffres");
			} else {
				MainApp ma = new MainApp();
				ma.changeScene("Signin.fxml");
			}
		}
	}
}