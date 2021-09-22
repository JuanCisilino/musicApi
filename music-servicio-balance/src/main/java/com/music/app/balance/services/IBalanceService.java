package com.music.app.balance.services;

import java.util.List;

import com.music.app.balance.models.Usuario;

public interface IBalanceService {

	public List<Usuario> findAll();
	public Usuario findById(Long id);
	public Usuario findByUsername(String username);
	
	public Usuario save(Usuario usuario);
	
	public Usuario cobrarTrack(String username, Double monto);
	
	public Usuario cargar(String username, Double monto);
	
}
