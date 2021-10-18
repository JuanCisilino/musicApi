package com.music.app.servicio.usuarios.controller;

import java.util.List;

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
import org.springframework.web.server.ResponseStatusException;

import com.music.app.servicio.usuarios.models.entity.Usuario;
import com.music.app.servicio.usuarios.services.IUsuarioService;

@RestController
public class UsuarioController {

	@Autowired
	private IUsuarioService service;
	
	@GetMapping("/usuarios")
	public List<Usuario> listar(){
		return service.findAll();
	}
	
	@GetMapping("/usuarios/{username}")
	public Usuario detalleUser(@PathVariable String username) {
		Usuario usuario = service.findByUsername(username);
		if (usuario == null) {
			throw setException("No existe el usuario");
		} else {
			return usuario;
		}
	}
	
	@PostMapping("/usuarios")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario crear(@RequestBody Usuario usuario) {
		checkBalance(usuario);
		Usuario usuarioCheck = service.findByUsername(usuario.getUsername());
		if (usuarioCheck != null) {
			throw setException("El usuario ya existe");
		} else {
			return service.save(usuario);
		}
	}
	
	@DeleteMapping("/usuarios/{username}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable String username) {
		Usuario usuario = service.findByUsername(username);
		if (usuario == null) {
			throw setException("No existe el usuario");
		} else {
			service.deleteByUsername(username);;
		}
	}
	
	@PutMapping("/cargar/{username}/{monto}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario cargar(@PathVariable String username, @PathVariable Double monto) {
		Usuario usuarioDb = service.findByUsername(username);
		if (usuarioDb == null) {
			throw setException("No existe el usuario");
		} else {
			usuarioDb.setBalance(usuarioDb.getBalance() + monto);
			return service.save(usuarioDb);
		}
	}
	
	private void checkBalance(Usuario usuario) {
		if (usuario.getBalance() == null) {
			usuario.setBalance(0.0);
		}
	}
	
	private ResponseStatusException setException(String message) {
		return new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
	}
	
	@PutMapping("/cobrar/{username}/{monto}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario cobrar(@PathVariable String username, @PathVariable Double monto) {
		Usuario usuarioDb = service.findByUsername(username);
		if (usuarioDb == null) {
			throw setException("No existe el usuario");
		} else {
			usuarioDb.setBalance(usuarioDb.getBalance() - monto);
			return service.save(usuarioDb);
		}
	}
	
	@PutMapping("/usuarios/{username}/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario agregarTrack(@RequestBody Usuario usuario, @PathVariable String username, @PathVariable String id) {
		Usuario usuarioDb = service.findByUsername(username);
		if (usuarioDb == null) {
			throw setException("No existe el usuario");
		} else {
			if (usuarioDb.getPlaylist().contains(id)) {
				throw setException("Este track ya esta en la playlist");
			} else {
				usuarioDb.addPlaylist(id);
				return service.save(usuarioDb);
			}	
		}
	}
}
