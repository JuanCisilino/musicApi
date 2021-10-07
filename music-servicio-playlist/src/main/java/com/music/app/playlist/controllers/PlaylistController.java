package com.music.app.playlist.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.music.app.playlist.models.Track;
import com.music.app.playlist.models.Usuario;
import com.music.app.playlist.services.IPlaylistService;

@RestController
public class PlaylistController {

	@Autowired
	private IPlaylistService service;
	
	@GetMapping("/usuarios")
	public List<Usuario> listar(){
		return service.findAll();
	}
	
	@GetMapping("/usuarios/{username}")
	public Usuario detalleUsuario(@PathVariable String username) {
		return service.findByUsername(username);
	}
	
	@GetMapping("/tracks")
	public List<Track> listarTracks(){
		return service.findAllTracks();
	}
	
	@GetMapping("/tracks/{id}")
	public Track detalleTrack(@PathVariable Long id) {
		return service.findTrackById(id);
	}
	
	@GetMapping("/playlist/{username}")
	public ArrayList<Track> listarPlaylist(@PathVariable String username) {
		return service.getPlaylist(username);
	}
	
	@GetMapping("/recomendar/{animo}")
	public Track recomendar(@PathVariable String animo) {
		return service.recomend(animo);
	}
	
	@PostMapping("/usuarios")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario crearUsuario(@RequestBody Usuario usuario) {
		return service.save(usuario);
	}
	
	@DeleteMapping("/usuarios/{username}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminarUsuario(@PathVariable String username) {
		service.delete(username);
	}
	
	@PutMapping("/comprar/{username}/{trackId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario comprarTrack(@PathVariable String username, @PathVariable Long trackId) {
		return service.comprarTrack(username, trackId);
	}
	
	@PutMapping("/cargar/{username}/{monto}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario cargar(@PathVariable String username, @PathVariable Double monto) {
		return service.cargar(username, monto);
	}
}
