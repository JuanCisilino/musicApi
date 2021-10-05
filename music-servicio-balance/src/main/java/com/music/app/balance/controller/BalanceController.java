package com.music.app.balance.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.music.app.balance.models.Usuario;
import com.music.app.balance.services.IBalanceService;

@RestController
public class BalanceController {
	
	@Autowired
	private CircuitBreakerFactory cbFactory;

	@Autowired
	private IBalanceService usuarioService;
	
	@GetMapping("/")
	public List<Usuario> listar(){
		return cbFactory.create("detalle")
				.run(() -> usuarioService.findAll(), error -> metodoAlternativoLista());
	}
		
	@PutMapping("/cobrar/{username}/{monto}")
	public Usuario cobrarTrack(@PathVariable String username, @PathVariable Double monto) {
		return cbFactory.create("cobrarTrack")
				.run(() -> usuarioService.cobrarTrack(username, monto), error -> metodoAlternativo());
	}
	
	@PutMapping("/cargar/{username}/{monto}")
	public Usuario cargar(@PathVariable String username, @PathVariable Double monto) {
		return cbFactory.create("cargar")
				.run(() -> usuarioService.cargar(username, monto), error -> metodoAlternativo());
	}
	
	private Usuario metodoAlternativo() {
		Usuario dummyUsuario = new Usuario();
		dummyUsuario.setName("Algo");
		dummyUsuario.setLastName("Andamal");
		dummyUsuario.setUsername("CircuitBreaker");
		return dummyUsuario;
	}
	
	private List<Usuario> metodoAlternativoLista() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		lista.add(metodoAlternativo());
		return lista;
	}
}
