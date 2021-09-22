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
	
	@GetMapping("/listar_usuarios")
	public List<Usuario> listar(){
		return service.findAll();
	}
	
	@GetMapping("/ver_usuario/{id}")
	public Usuario detalle(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@GetMapping("/ver/{username}")
	public Usuario detalleUsuario(@PathVariable String username) {
		return service.findByUsername(username);
	}
	
	@GetMapping("/listar_tracks")
	public List<Track> listarTracks(){
		return service.findAllTracks();
	}
	
	@GetMapping("/ver_track/{id}")
	public Track detalleTrack(@PathVariable Long id) {
		return service.findTrackById(id);
	}
	
	@GetMapping("/ver_playlist/{username}")
	public ArrayList<Track> listarPlaylist(@PathVariable String username) {
		ArrayList<Track> playList = new ArrayList<Track>();
		Usuario usuario = service.findByUsername(username);
		if (!usuario.getPlaylist().isEmpty()) { 
			return getPlaylist(usuario, playList);
		} else {
			return playList;
		}
	}
	
	private ArrayList<Track> getPlaylist(Usuario usuario, ArrayList<Track> playList){
		List<Track> tracks = listarTracks();
		tracks.forEach(track -> checkList(track, usuario, playList));
		return playList;
	}
	
	private void checkList(Track track, Usuario usuario, List<Track> playList) {
		if (usuario.getPlaylist().contains(track.getId().toString()) && !track.getIsBoring()) { 
			playList.add(track);
		}
	}
	
	@GetMapping("/recomendar/{animo}")
	public Track recomendar(@PathVariable String animo) {
		if (animo.toUpperCase().equals("ENOJADO")) {
			return getByCategory("PUNK");
		} else if (animo.toUpperCase().equals("MELANCOLICO")) {
			return getOldRandomTrack();
		} else if (animo.toUpperCase().equals("CONTENTO")) {
			return getByCategory("REGGAETON");
		} else {
			return new Track();
		}
	}

	private Track getOldRandomTrack() {
		List<Track> lista = listarTracks().stream().collect(Collectors.toList());
		ArrayList<Track> playList = new ArrayList<Track>();
		lista.forEach(track -> filterListByRelease(track, playList));
		return getRandomElement(playList);
	}

	private Track getByCategory(String category) {
		List<Track> lista = listarTracks().stream().collect(Collectors.toList());
		ArrayList<Track> playList = new ArrayList<Track>();
		lista.forEach(track -> filterListByCategory(category,track, playList));
		return getRandomElement(playList);
	}

	private void filterListByCategory(String category, Track track, ArrayList<Track> playList) {
		if (track.getCategory().equals(category)) {
			playList.add(track);
		}
	}
	
	private void filterListByRelease(Track track, ArrayList<Track> playList) {
		if (track.getIsOld() == true) {
			playList.add(track);
		}
	}
	
	public Track getRandomElement(ArrayList<Track> lista) {
        Random rand = new Random();
        if (lista.isEmpty()) {
        	return new Track();
        }
        return lista.get(rand.nextInt(lista.size()));
    }
	
	@PostMapping("/crear_usuario")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario crearUsuario(@RequestBody Usuario usuario) {
		return service.save(usuario);
	}
	
	@DeleteMapping("/eliminar_usuario/{username}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminarUsuario(@PathVariable String username) {
		service.delete(username);
	}
	
	@PutMapping("comprar_track/{username}/{trackId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario comprarTrack(@PathVariable String username, @PathVariable Long trackId) {
		Track selectedTrack = service.findTrackById(trackId);
		Usuario usuario = service.findByUsername(username);
		if (usuario.getPremium() == true) {
			service.agregarTrack(username, selectedTrack.getId().toString());
		} else if (usuario.getBalance() > selectedTrack.getPrice()) {
			service.comprarTrack(username, selectedTrack.getPrice());
			service.agregarTrack(username, selectedTrack.getId().toString());
		}
		return service.findByUsername(username);
	}
	
	@PutMapping("cargar/{username}/{monto}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario cargar(@PathVariable String username, @PathVariable Double monto) {
		return service.cargar(username, monto);
	}
}
