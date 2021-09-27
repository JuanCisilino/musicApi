package com.music.app.balance.services;

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

import com.music.app.balance.models.Usuario;

@Service
public class BalanceServiceImpl implements IBalanceService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public List<Usuario> findAll() {
		return Arrays.asList(restTemplate.getForObject("http://servicio-usuarios/listar", Usuario[].class));
	}
	
	@Override
	public Usuario findByUsername(String username) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("username", username.toString());
		return restTemplate.getForObject("http://servicio-usuarios/ver/{username}", Usuario.class, pathVariables);
	}

	@Override
	public Usuario save(Usuario usuario) {
		HttpEntity<Usuario> body = new HttpEntity<Usuario>(usuario);
		ResponseEntity<Usuario> response = restTemplate.exchange("http://servicio-usuarios/crear", HttpMethod.POST, body, Usuario.class);
		return response.getBody();
	}

	@Override
	public Usuario cobrarTrack(String username, Double monto) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("username", username.toString());
		pathVariables.put("monto", monto.toString());
		HttpEntity<Usuario> body = new HttpEntity<Usuario>(findByUsername(username));
		ResponseEntity<Usuario> response = restTemplate.exchange("http://servicio-usuarios/cobrar/{username}/{monto}", HttpMethod.PUT, body, Usuario.class, pathVariables);
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
