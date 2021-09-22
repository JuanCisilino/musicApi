package com.music.app.track.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.music.app.track.models.entity.Track;
import com.music.app.track.models.service.ITrackService;

@RestController
public class TrackController {

	@Autowired
	private ITrackService trackService;
	
	@GetMapping("/listar")
	public List<Track> listar(){
		return trackService.findAll();
	}
	
	@GetMapping("/ver/{id}")
	public Track detalle(@PathVariable Long id) {
		return trackService.findById(id);
	}
	
}
