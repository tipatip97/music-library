package com.example.musiclibrary.controller;

import com.example.musiclibrary.model.Artist;
import com.example.musiclibrary.service.ArtistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/artist")
public class ArtistController {
	private final ArtistService artistService;
	
	public ArtistController(ArtistService artistService) {
		this.artistService = artistService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void saveArtist(
			@RequestBody Artist artist) {

		artistService.saveArtist(artist);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Artist getArtist(
			@PathVariable(name = "id") Long id) {

		return artistService.getArtist(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Artist> getArtist(
			@RequestParam(value = "id") List<Long> ids) {

		return artistService.getArtists(ids);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public void updateArtist(
			@PathVariable(name = "id") Long id,
			@RequestBody Artist artist) {

		artistService.editArtist(artist, id);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void deleteArtist(
			@PathVariable(name = "id") Long id) {

		artistService.deleteArtist(id);
	}
}
