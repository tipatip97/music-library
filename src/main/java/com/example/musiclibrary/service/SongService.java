package com.example.musiclibrary.service;

import com.example.musiclibrary.controller.errors.NotFoundException;
import com.example.musiclibrary.entity.ArtistEntity;
import com.example.musiclibrary.entity.SongEntity;
import com.example.musiclibrary.model.Song;
import com.example.musiclibrary.repository.ArtistRepository;
import com.example.musiclibrary.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongService {
	final SongRepository songRepository;
	final ArtistRepository artistRepository;
	
	public SongService(SongRepository songRepository, ArtistRepository artistRepository) {
		this.songRepository = songRepository;
		this.artistRepository = artistRepository;
	}
	
	public SongEntity getSong(Long id) {
		return songRepository.findById(id).orElseThrow(NotFoundException::new);
	}
	
	public List<SongEntity> getSongs(List<Long> ids) {
		return songRepository.findAllById(ids);
	}
	
	public void saveSong(SongEntity songEntity) {
		songRepository.saveAndFlush(songEntity);
	}
	
	public void editSong(SongEntity songEntity, Long id) {
		SongEntity savedSongEntity = songRepository.findById(id).orElseThrow(NotFoundException::new);
		
		savedSongEntity.setTitle(songEntity.getTitle());
		savedSongEntity.setAlbum(songEntity.getAlbum());
		savedSongEntity.setReleaseDate(songEntity.getReleaseDate());
		savedSongEntity.setArtistEntities(songEntity.getArtistEntities());
		
		songRepository.saveAndFlush(savedSongEntity);
	}
	
	public void deleteSong(Long id) {
		if (songRepository.existsById(id)) {
			songRepository.deleteById(id);
		} else {
			throw new NotFoundException();
		}
	}
	
	public Song songEntityToModel(SongEntity songEntity) {
		Song song = new Song();
		
		song.setId(songEntity.getId());
		song.setTitle(songEntity.getTitle());
		song.setAlbum(songEntity.getAlbum());
		song.setReleaseDate(songEntity.getReleaseDate());
		song.setArtistIds(songEntity.getArtistEntities()
				.stream()
				.map(ArtistEntity::getId)
				.collect(Collectors.toList()));
		
		return song;
	}
	
	public SongEntity songModelToEntity(Song song) {
		SongEntity songEntity = new SongEntity();
		
		songEntity.setId(song.getId());
		songEntity.setTitle(song.getTitle());
		songEntity.setAlbum(song.getAlbum());
		
		List<ArtistEntity> songEntities = artistRepository.findAllById(song.getArtistIds());
		songEntity.setArtistEntities(songEntities);
		
		return songEntity;
	}
	
}
