package com.music.app.playlist.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.music.app.playlist.models.Track;
import com.music.app.playlist.models.Usuario;


@Service
public class PlaylistServiceImpl implements IPlaylistService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public List<Usuario> findAll() {
		return Arrays.asList(restTemplate.getForObject("http://servicio-usuarios/usuarios", Usuario[].class));
	}

	@Override
	public Usuario findByUsername(String username) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("username", username);
		return restTemplate.getForObject("http://servicio-usuarios/usuarios/{username}", Usuario.class, pathVariables);
	}

	@Override
	public List<Track> findAllTracks() {
		List<Track> lista = Arrays.asList(restTemplate.getForObject("http://servicio-track/tracks", Track[].class));
		lista.forEach(track -> track.setCaracteristcs());
		return lista;
	}

	@Override
	public Track findTrackById(Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		Track selectedTrack = restTemplate.getForObject("http://servicio-track/tracks/{id}", Track.class, pathVariables);
		selectedTrack.setCaracteristcs();
		return selectedTrack;
	}

	@Override
	public Usuario save(Usuario usuario) {
		HttpEntity<Usuario> body = new HttpEntity<Usuario>(usuario);
		ResponseEntity<Usuario> response = restTemplate.exchange("http://servicio-usuarios/usuarios", HttpMethod.POST, body, Usuario.class);
		return response.getBody();
	}

	@Override
	public void delete(String username) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("username", username.toString());
		restTemplate.delete("http://servicio-usuarios/usuarios/{username}", pathVariables);
	}

	public void comprar(String username, Double monto) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("username", username.toString());
		pathVariables.put("monto", monto.toString());
		HttpEntity<Usuario> body = new HttpEntity<Usuario>(findByUsername(username));
		restTemplate.exchange("http://servicio-usuarios/cobrar/{username}/{monto}", HttpMethod.PUT, body, Usuario.class, pathVariables);
	}

	@Override
	public Usuario agregarTrack(String username, String id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("username", username.toString());
		pathVariables.put("id", id.toString());
		HttpEntity<Usuario> body = new HttpEntity<Usuario>(findByUsername(username));
		ResponseEntity<Usuario> response = restTemplate.exchange("http://servicio-usuarios/usuarios/{username}/{id}", HttpMethod.PUT, body, Usuario.class, pathVariables);
		return response.getBody();
	}

	@Override
	public Usuario cargar(String username, Double monto) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("username", username.toString());
		pathVariables.put("monto", monto.toString());
		HttpEntity<Usuario> body = new HttpEntity<Usuario>(findByUsername(username));
		ResponseEntity<Usuario> response = restTemplate.exchange("http://servicio-usuarios/cargar/{username}/{monto}", HttpMethod.PUT, body, Usuario.class, pathVariables);
		return response.getBody();
	}
	
	@Override
	public Usuario comprarTrack(String username, Long trackId) {
		Track selectedTrack = findTrackById(trackId);
		Usuario usuario = findByUsername(username);
		if (usuario.getPremium() == true) {
			agregarTrack(username, selectedTrack.getId().toString());
		} else if (usuario.getBalance() >= selectedTrack.getPrice()) {
			comprar(username, selectedTrack.getPrice());
			agregarTrack(username, selectedTrack.getId().toString());
		}
		return findByUsername(username);
	}

	@Override
	public ArrayList<Track> getPlaylist(String username) {
		ArrayList<Track> playList = new ArrayList<Track>();
		Usuario usuario = findByUsername(username);
		if (!usuario.getPlaylist().isEmpty()) { 
			return getPlaylist(usuario, playList);
		} else {
			return playList;
		}
	}
	
	private ArrayList<Track> getPlaylist(Usuario usuario, ArrayList<Track> playList){
		List<Track> tracks = findAllTracks();
		tracks.forEach(track -> checkList(track, usuario, playList));
		return playList;
	}
	
	private void checkList(Track track, Usuario usuario, List<Track> playList) {
		if (usuario.getPlaylist().contains(track.getId().toString()) && !track.getIsBoring()) { 
			playList.add(track);
		}
	}

	@Override
	public Track recomend(String animo) {
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
		List<Track> lista = findAllTracks();
		ArrayList<Track> playList = new ArrayList<Track>();
		lista.forEach(track -> filterListByRelease(track, playList));
		return getRandomElement(playList);
	}

	private Track getByCategory(String category) {
		List<Track> lista = findAllTracks();
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
	
}
