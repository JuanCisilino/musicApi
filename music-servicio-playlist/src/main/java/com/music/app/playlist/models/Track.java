package com.music.app.playlist.models;

import com.music.app.playlist.models.Track;

public class Track {

	private Long id;
	private String producer;	
	private String artist;
	private Integer members;	
	private String album;
	private Integer albumSongs;
	private Integer release;
	private String song;
	private Double duration;
	private String category;
	private Double price;

	private Boolean isShort;
	private Boolean isOld;
	private Boolean isNew;
	private Boolean isBoring;

	public void setCaracteristcs() {
		isShort = getIsShort(this.duration);
		isNew = getIsNew(this.release);
		isOld = getIsOld(this.release);
		isBoring = isBoring(this);
	}

	private Boolean getIsShort(Double duration) {
		if (duration != null && duration < 2.00) {
			return true;
		} else {
			return false;
		}
	}
	
	private Boolean getIsOld(Integer release) {
		if (release != null && release < 2000) {
			return true;
		} else {
			return false;
		}
	}
	
	private Boolean getIsNew(Integer release) {
		if (release != null && release > 2014) {
			return true;
		} else {
			return false;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Integer getMembers() {
		return members;
	}

	public void setMembers(Integer members) {
		this.members = members;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public Integer getAlbumSongs() {
		return albumSongs;
	}

	public void setAlbumSongs(Integer albumSongs) {
		this.albumSongs = albumSongs;
	}

	public Integer getRelease() {
		return release;
	}

	public void setRelease(Integer release) {
		this.release = release;
	}

	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Boolean getIsShort() {
		return isShort;
	}

	public void setIsShort(Boolean isShort) {
		this.isShort = isShort;
	}

	public Boolean getIsOld() {
		return isOld;
	}

	public void setIsOld(Boolean isOld) {
		this.isOld = isOld;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public Boolean getIsBoring() {
		return isBoring;
	}

	public void setIsBoring(Boolean isBoring) {
		this.isBoring = isBoring;
	}

	private Boolean isBoring(Track track) {
		if (track.category.equals("PUNK") && track.duration > 2.00) {
			return true;
		} else if (track.category.equals("REGGAETON") && track.isOld) {
			return true;
		} else if (track.category.equals("ROCK") && track.albumSongs < 9) {
			return true;
		} else if (track.category.equals("POP") && track.members == 1) {
			return true;
		} else {
			return false;
		}
	}

}
