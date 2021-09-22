package com.music.app.track.models.service;

import java.util.List;

import com.music.app.track.models.entity.Track;

public interface ITrackService {

	public List<Track> findAll();
	
	public Track findById(Long id);
}
