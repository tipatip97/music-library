package com.example.musiclibrary.service;

import com.example.musiclibrary.controller.errors.NotFoundException;
import com.example.musiclibrary.model.Song;
import com.example.musiclibrary.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
	final SongRepository songRepository;
	
	public SongService(SongRepository songRepository) {
		this.songRepository = songRepository;
	}
	
	public Song getSong(Long id) {
		return songRepository.findById(id).orElseThrow(NotFoundException::new);
	}
	
	public List<Song> getSongs(List<Long> ids) {
		return songRepository.findAllById(ids);
	}
	
	public void saveSong(Song song) {
		songRepository.saveAndFlush(song);
	}
	
	public void editSong(Song song, Long id) {
		Song savedSong = songRepository.findById(id).orElseThrow(NotFoundException::new);
		
		savedSong.setTitle(song.getTitle());
		savedSong.setAlbum(song.getAlbum());
		savedSong.setReleaseDate(song.getReleaseDate());
		savedSong.setArtists(song.getArtists());
		
		songRepository.saveAndFlush(savedSong);
	}
	
	public void deleteSong(Long id) {
		if (songRepository.existsById(id)) {
			songRepository.deleteById(id);
		} else {
			throw new NotFoundException();
		}
	}
}
