package com.music.app.balance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.music.app.balance.models.Usuario;
import com.music.app.balance.services.IBalanceService;

@RestController
public class BalanceController {

	@Autowired
	private IBalanceService usuarioService;
	
	@GetMapping("/listar")
	public List<Usuario> listar(){
		return usuarioService.findAll();
	}
	
	@GetMapping("/ver/{id}")
	public Usuario detalle(@PathVariable Long id) {
		return usuarioService.findById(id);
	}
	
	@PutMapping("/cobrar/{username}/{monto}")
	public Usuario cobrarTrack(@PathVariable String username, @PathVariable Double monto) {
		return usuarioService.cobrarTrack(username, monto);
	}
	
	@PutMapping("/cargar/{username}/{monto}")
	public Usuario cargar(@PathVariable String username, @PathVariable Double monto) {
		return usuarioService.cargar(username, monto);
	}
}
