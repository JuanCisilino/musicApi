package com.music.app.servicio.usuarios.models.entity;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 20)
	private String username;

	@Column(length = 60)
	private String password;

	private Boolean premium;
	private String name;

	@Column(name = "last_name")
	private String lastName;

	private Double balance;
	
	@Column(name = "user_playlist")
	private ArrayList<String> playlist;
	
	
	public ArrayList<String> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(ArrayList<String> playlist) {
		this.playlist = playlist;
	}
	
	public void addPlaylist(String id) {
		if (this.playlist == null) {
			this.playlist = new ArrayList<String>();
		}
		this.playlist.add(id);
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

	private static final long serialVersionUID = -5953993862063464886L;

}
