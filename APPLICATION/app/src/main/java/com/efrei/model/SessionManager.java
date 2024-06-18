package com.efrei.model;

public class SessionManager {

	private static SessionManager instance;
	private int userId;
	private int cinemaId;
	private String username;

	// Private constructor to prevent instantiation
	private SessionManager() {
	}

	// Method to get the singleton instance
	public static SessionManager getInstance() {
		if (instance == null) {
			instance = new SessionManager();
		}
		return instance;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}

}
