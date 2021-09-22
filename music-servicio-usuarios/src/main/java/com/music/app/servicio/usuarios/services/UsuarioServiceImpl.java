package com.music.app.servicio.usuarios.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.music.app.servicio.usuarios.models.entity.Usuario;
import com.music.app.servicio.usuarios.models.repository.UsuarioRepo;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private UsuarioRepo usuarioRepo;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioRepo.findAll();
	}

	@Override
	public Usuario findById(Long id) {
		return usuarioRepo.findById(id).orElse(null);
	}

	@Override
	public Usuario findByUsername(String username) {
		if (username == null) {
			return null;
		}else {
			return usuarioRepo.findByUsername(username);
		}
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		return usuarioRepo.save(usuario);
	}

	@Override
	@Transactional
	public void deleteByUsername(String username) {
		usuarioRepo.delete(usuarioRepo.findByUsername(username));
	}

}
