package com.music.app.playlist.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		return Arrays.asList(restTemplate.getForObject("http://servicio-usuarios/listar", Usuario[].class));
	}

	@Override
	public Usuario findById(Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		return restTemplate.getForObject("http://servicio-usuarios/ver/{id}", Usuario.class, pathVariables);
	}
	
	@Override
	public Usuario findByUsername(String username) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("username", username);
		return restTemplate.getForObject("http://servicio-usuarios/ver_user/{username}", Usuario.class, pathVariables);
	}

	@Override
	public List<Track> findAllTracks() {
		List<Track> lista = Arrays.asList(restTemplate.getForObject("http://servicio-track/listar", Track[].class));
		lista.forEach(track -> track.setCaracteristcs());
		return lista;
	}

	@Override
	public Track findTrackById(Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		return restTemplate.getForObject("http://servicio-track/ver/{id}", Track.class, pathVariables);
	}

	@Override
	public Usuario save(Usuario usuario) {
		HttpEntity<Usuario> body = new HttpEntity<Usuario>(usuario);
		ResponseEntity<Usuario> response = restTemplate.exchange("http://servicio-usuarios/crear", HttpMethod.POST, body, Usuario.class);
		return response.getBody();
	}

	@Override
	public void delete(String username) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("username", username.toString());
		restTemplate.delete("http://servicio-usuarios/eliminar/{username}", pathVariables);
	}

	@Override
	public void comprarTrack(String username, Double monto) {
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
		ResponseEntity<Usuario> response = restTemplate.exchange("http://servicio-usuarios/agregar_track/{username}/{id}", HttpMethod.PUT, body, Usuario.class, pathVariables);
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
	
}
