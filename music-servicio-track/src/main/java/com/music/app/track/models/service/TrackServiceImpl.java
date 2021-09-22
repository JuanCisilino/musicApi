package com.music.app.track.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.music.app.track.models.entity.Track;
import com.music.app.track.models.repository.TrackRepo;

@Service
public class TrackServiceImpl implements ITrackService{

	@Autowired
	private TrackRepo trackRepo;
	
	@Override
	@Transactional(readOnly = true)
	public List<Track> findAll() {
		return (List<Track>) trackRepo.findAll();
	}

	@Override
	public Track findById(Long id) {
		return trackRepo.findById(id).orElse(null);
	}

}
