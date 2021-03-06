package com.music.app.playlist.services;

import java.util.ArrayList;
import java.util.List;

import com.music.app.playlist.models.Usuario;
import com.music.app.playlist.models.Track;

public interface IPlaylistService {

	public List<Usuario> findAll();
	public Usuario findByUsername(String username);
	
	public List<Track> findAllTracks();
	public Track findTrackById(Long id);
	
	public Usuario save(Usuario usuario);
	public void delete(String username);
	
	public Usuario comprarTrack(String username, Long trackId);
	public Usuario agregarTrack(String username, String id);
	
	public Usuario cargar(String username, Double monto);
	
	public ArrayList<Track> getPlaylist(String username);
	public Track recomend(String animo);
	
}
