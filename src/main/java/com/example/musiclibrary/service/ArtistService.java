package com.example.musiclibrary.service;

import com.example.musiclibrary.controller.errors.NotFoundException;
import com.example.musiclibrary.entity.Artist;
import com.example.musiclibrary.entity.Song;
import com.example.musiclibrary.DTO.ArtistDTO;
import com.example.musiclibrary.repository.ArtistRepository;
import com.example.musiclibrary.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistService {
	private final ArtistRepository artistRepository;
	private final SongRepository songRepository;
	
	public ArtistService(ArtistRepository artistRepository, SongRepository songRepository) {
		this.artistRepository = artistRepository;
		this.songRepository = songRepository;
	}
	
	public Artist getArtist(Long id) {
		return artistRepository.findById(id).orElseThrow(NotFoundException::new);
	}
	
	public List<Artist> getArtists(List<Long> ids) {
		return artistRepository.findAllById(ids);
	}
	
	public Artist saveArtist(ArtistDTO artistDTO) {
		Artist artist = artistModelToEntity(artistDTO);
		return artistRepository.saveAndFlush(artist);
	}
	
	public void editArtist(ArtistDTO artistDTO, Long id) {
		Artist artist = artistModelToEntity(artistDTO);
		Artist savedArtistEntity = artistRepository.findById(id).orElseThrow(NotFoundException::new);
		
		savedArtistEntity.setName(artist.getName());
		savedArtistEntity.setBirthDay(artist.getBirthDay());
		savedArtistEntity.setSongEntities(artist.getSongEntities());
		
		artistRepository.saveAndFlush(savedArtistEntity);
	}
	
	public void deleteArtist(Long id) {
		if (artistRepository.existsById(id)) {
			artistRepository.deleteById(id);
		} else {
			throw new NotFoundException();
		}
	}
	
	public Artist artistModelToEntity(ArtistDTO artistDTO) {
		Artist artistEntity = new Artist();
		
		artistEntity.setId(artistDTO.getId());
		artistEntity.setName(artistDTO.getName());
		artistEntity.setBirthDay(artistDTO.getBirthDay());
		
		if (artistDTO.getSongIds() != null) {
			List<Song> songEntities = songRepository.findAllById(artistDTO.getSongIds());
			artistEntity.setSongEntities(songEntities);
		} else {
			artistEntity.setSongEntities(null);
		}
		
		if (artistDTO.getSongIds().size() != artistEntity.getSongEntities().size()) {
			throw new NotFoundException();
		}
		
		return artistEntity;
	}
	
	public List<ArtistDTO> getAll() {
		return artistRepository.findAll().stream()
				.map(ArtistDTO::getArtistDTO)
				.collect(Collectors.toList());
	}
}
