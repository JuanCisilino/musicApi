package com.music.app.servicio.usuarios.services;

import java.util.List;

import com.music.app.servicio.usuarios.models.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> findAll();
	public Usuario findById(Long id);
	public Usuario findByUsername(String username);
	
	public Usuario save(Usuario usuario);
	
	public void deleteByUsername(String username);

}
