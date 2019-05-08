package com.example.musiclibrary.service;

import com.example.musiclibrary.controller.errors.NotFoundException;
import com.example.musiclibrary.model.Artist;
import com.example.musiclibrary.model.Artist;
import com.example.musiclibrary.repository.ArtistRepository;
import com.example.musiclibrary.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {
	final ArtistRepository artistRepository;
	
	public ArtistService(ArtistRepository artistRepository) {
		this.artistRepository = artistRepository;
	}
	
	public Artist getArtist(Long id) {
		return artistRepository.findById(id).orElseThrow(NotFoundException::new);
	}
	
	public List<Artist> getArtists(List<Long> ids) {
		return artistRepository.findAllById(ids);
	}
	
	public void saveArtist(Artist artist) {
		artistRepository.saveAndFlush(artist);
	}
	
	public void editArtist(Artist artist, Long id) {
		Artist savedArtist = artistRepository.findById(id).orElseThrow(NotFoundException::new);
		
		savedArtist.setName(artist.getName());
		savedArtist.setBirthDay(artist.getBirthDay());
		savedArtist.setSongs(artist.getSongs());
		
		artistRepository.saveAndFlush(savedArtist);
	}
	
	public void deleteArtist(Long id) {
		if (artistRepository.existsById(id)) {
			artistRepository.deleteById(id);
		} else {
			throw new NotFoundException();
		}
	}
}
