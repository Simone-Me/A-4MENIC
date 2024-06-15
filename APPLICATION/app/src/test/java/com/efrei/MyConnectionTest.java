package com.efrei;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import com.efrei.model.MyConnection;

class MyConnectionTest {
	MyConnection mc = new MyConnection();

	@Test
	void test() throws Exception {
		Connection connection = MyConnection.getConnection();
		System.out.println("Connection set.");
		assertNotNull(connection);
	}
}
