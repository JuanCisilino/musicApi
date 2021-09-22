package com.music.app.servicio.usuarios.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.music.app.servicio.usuarios.models.entity.Usuario;

@RepositoryRestResource(path="usuarios")
public interface UsuarioRepo extends PagingAndSortingRepository<Usuario, Long>{

	@Query("select u from Usuario u where u.username = ?1")
	public Usuario findByUsername(String username);
}
