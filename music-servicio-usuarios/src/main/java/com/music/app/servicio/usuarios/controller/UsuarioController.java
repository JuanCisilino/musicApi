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

import com.music.app.servicio.usuarios.models.entity.Usuario;
import com.music.app.servicio.usuarios.services.IUsuarioService;

@RestController
public class UsuarioController {

	@Autowired
	private IUsuarioService service;
	
	@GetMapping("/listar")
	public List<Usuario> listar(){
		return service.findAll();
	}
	
	@GetMapping("/ver/{id}")
	public Usuario detalle(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@GetMapping("/ver_user/{username}")
	public Usuario detalleUser(@PathVariable String username) {
		return service.findByUsername(username);
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario crear(@RequestBody Usuario usuario) {
		checkBalance(usuario);
		return service.save(usuario);
	}
	
	@DeleteMapping("/eliminar/{username}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable String username) {
		service.deleteByUsername(username);
	}
	
	@PutMapping("/cargar/{username}/{monto}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario cargar(@PathVariable String username, @PathVariable Double monto) {
		Usuario usuarioDb = service.findByUsername(username);
		checkBalance(usuarioDb);
		usuarioDb.setBalance(usuarioDb.getBalance() + monto);
		return service.save(usuarioDb);
	}
	
	private void checkBalance(Usuario usuario) {
		if (usuario.getBalance() == null) {
			usuario.setBalance(0.0);
		}
	}
	
	@PutMapping("/cobrar/{username}/{monto}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario cobrar(@PathVariable String username, @PathVariable Double monto) {
		Usuario usuarioDb = service.findByUsername(username);
		usuarioDb.setBalance(usuarioDb.getBalance() - monto);
		return service.save(usuarioDb);
	}
	
	@PutMapping("/agregar_track/{username}/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario agregarTrack(@RequestBody Usuario usuario, @PathVariable String username, @PathVariable String id) {
		Usuario usuarioDb = service.findByUsername(username);
		usuarioDb.addPlaylist(id);
		return service.save(usuarioDb);
	}
}