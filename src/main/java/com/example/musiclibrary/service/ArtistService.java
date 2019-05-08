package com.example.musiclibrary.service;

import com.example.musiclibrary.controller.errors.NotFoundException;
import com.example.musiclibrary.entity.ArtistEntity;
import com.example.musiclibrary.entity.SongEntity;
import com.example.musiclibrary.model.Artist;
import com.example.musiclibrary.repository.ArtistRepository;
import com.example.musiclibrary.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistService {
	final ArtistRepository artistRepository;
	final SongRepository songRepository;
	
	public ArtistService(ArtistRepository artistRepository, SongRepository songRepository) {
		this.artistRepository = artistRepository;
		this.songRepository = songRepository;
	}
	
	public ArtistEntity getArtist(Long id) {
		return artistRepository.findById(id).orElseThrow(NotFoundException::new);
	}
	
	public List<ArtistEntity> getArtists(List<Long> ids) {
		return artistRepository.findAllById(ids);
	}
	
	public void saveArtist(ArtistEntity artistEntity) {
		artistRepository.saveAndFlush(artistEntity);
	}
	
	public void editArtist(ArtistEntity artistEntity, Long id) {
		ArtistEntity savedArtistEntity = artistRepository.findById(id).orElseThrow(NotFoundException::new);
		
		savedArtistEntity.setName(artistEntity.getName());
		savedArtistEntity.setBirthDay(artistEntity.getBirthDay());
		savedArtistEntity.setSongEntities(artistEntity.getSongEntities());
		
		artistRepository.saveAndFlush(savedArtistEntity);
	}
	
	public void deleteArtist(Long id) {
		if (artistRepository.existsById(id)) {
			artistRepository.deleteById(id);
		} else {
			throw new NotFoundException();
		}
	}
	
	public Artist artistEntityToModel(ArtistEntity artistEntity) {
		Artist artist = new Artist();
		
		artist.setId(artistEntity.getId());
		artist.setName(artistEntity.getName());
		artist.setBirthDay(artistEntity.getBirthDay());
		artist.setSongIds(artistEntity.getSongEntities()
				.stream()
				.map(SongEntity::getId)
				.collect(Collectors.toList())
		);
		
		return artist;
	}
	
	public ArtistEntity artistModelToEntity(Artist artist) {
		ArtistEntity artistEntity = new ArtistEntity();
		
		artistEntity.setId(artist.getId());
		artistEntity.setName(artist.getName());
		artistEntity.setBirthDay(artist.getBirthDay());
		
		if (artist.getSongIds() != null) {
			List<SongEntity> songEntities = songRepository.findAllById(artist.getSongIds());
			artistEntity.setSongEntities(songEntities);
		} else {
			artistEntity.setSongEntities(null);
		}
		
		return artistEntity;
	}
}
