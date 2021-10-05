package com.music.app.track.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.music.app.track.models.entity.Track;
import com.music.app.track.models.service.ITrackService;

@RestController
public class TrackController {

	@Autowired
	private ITrackService trackService;
	
	@GetMapping("/tracks")
	public List<Track> listar(){
		return trackService.findAll();
	}
	
	@GetMapping("/tracks/{id}")
	public Track detalle(@PathVariable Long id) {
		Track track = trackService.findById(id);
		if (track == null) {
			throw setException("La cancion no existe");
		} else {
			return track;
		}
	}
	
	private ResponseStatusException setException(String message) {
		return new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
	}
}
