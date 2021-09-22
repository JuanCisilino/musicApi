package com.music.app.playlist.models;

import java.util.ArrayList;

public class Usuario {
	
	private Long id;
	private String username;
	private String password;
	private Boolean premium;
	private String name;
	private String lastName;
	private Double balance;
	private ArrayList<String> playlist;
	
	public ArrayList<String> getPlaylist() {
		if (this.playlist == null) {
			return new ArrayList<String>();
		} else {
			return playlist;
		}
	}

	public void setPlaylist(ArrayList<String> playlist) {
		this.playlist = playlist;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getPremium() {
		return premium;
	}

	public void setPremium(Boolean premium) {
		this.premium = premium;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
