package com.efrei;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.junit.jupiter.api.Test;
import com.efrei.model.MyConnection;

class CinemaTest {

	@Test
	void testCinema() throws Exception {
		Connection connection = MyConnection.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM cinema");

		while (resultSet.next()) {
			int id = resultSet.getInt("idCinema");
			String name = resultSet.getString("nomCine");
			String city = resultSet.getString("villeCine");
			System.out.println("ID: " + id + ", Name: " + name + ", Email: " + city);
		}

		resultSet.close();
		statement.close();
		connection.close();
	}

}
