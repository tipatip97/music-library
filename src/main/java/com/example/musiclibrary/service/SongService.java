package com.example.musiclibrary.service;

import com.example.musiclibrary.DTO.SongDTO;
import com.example.musiclibrary.controller.errors.NotFoundException;
import com.example.musiclibrary.entity.Artist;
import com.example.musiclibrary.entity.Song;
import com.example.musiclibrary.repository.ArtistRepository;
import com.example.musiclibrary.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongService {
	private final SongRepository songRepository;
	private final ArtistRepository artistRepository;
	
	public SongService(SongRepository songRepository, ArtistRepository artistRepository) {
		this.songRepository = songRepository;
		this.artistRepository = artistRepository;
	}
	
	public Song getSong(Long id) {
		return songRepository.findById(id).orElseThrow(NotFoundException::new);
	}
	
	public List<Song> getSongs(List<Long> ids) {
		return songRepository.findAllById(ids);
	}
	
	public void saveSong(SongDTO songDTO) {
		Song song = songModelToEntity(songDTO);
		songRepository.saveAndFlush(song);
	}
	
	public void editSong(SongDTO songDTO, Long id) {
		Song song = songModelToEntity(songDTO);
		
		Song savedSong = songRepository.findById(id).orElseThrow(NotFoundException::new);
		
		savedSong.setTitle(song.getTitle());
		savedSong.setAlbum(song.getAlbum());
		savedSong.setReleaseDate(song.getReleaseDate());
		savedSong.setArtistEntities(song.getArtistEntities());
		
		songRepository.saveAndFlush(savedSong);
	}
	
	public void deleteSong(Long id) {
		if (songRepository.existsById(id)) {
			songRepository.deleteById(id);
		} else {
			throw new NotFoundException();
		}
	}
	
	public Song songModelToEntity(SongDTO song) {
		Song songEntity = new Song();
		
		songEntity.setId(song.getId());
		songEntity.setTitle(song.getTitle());
		songEntity.setAlbum(song.getAlbum());
		
		if (song.getArtistIds() != null) {
			List<Artist> songEntities = artistRepository.findAllById(song.getArtistIds());
			songEntity.setArtistEntities(songEntities);
		} else {
			songEntity.setArtistEntities(null);
		}
		if (song.getArtistIds().size() != songEntity.getArtistEntities().size()) {
			throw new NotFoundException();
		}
		
		return songEntity;
	}
	
	
	public List<SongDTO> getAll() {
		return songRepository.findAll().stream()
				.map(SongDTO::getSongDTO)
				.collect(Collectors.toList());
	}
}
