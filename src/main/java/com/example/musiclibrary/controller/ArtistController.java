package com.example.musiclibrary.controller;

import com.example.musiclibrary.DTO.ArtistDTO;
import com.example.musiclibrary.service.ArtistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/artist")
public class ArtistController {
	private final ArtistService artistService;
	
	public ArtistController(ArtistService artistService) {
		this.artistService = artistService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void saveArtist(
			@RequestBody ArtistDTO artistDTO) {

		artistService.saveArtist(artistDTO);
	}
	
	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public List<ArtistDTO> getAll() {
		return artistService.getAll();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ArtistDTO getArtist(
			@PathVariable(name = "id") Long id) {

		return ArtistDTO.getArtistDTO(artistService.getArtist(id));
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<ArtistDTO> getArtist(
			@RequestParam(value = "id") List<Long> ids) {
		
		return artistService.getArtists(ids).stream()
				.map(ArtistDTO::getArtistDTO)
				.collect(Collectors.toList());
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public void updateArtist(
			@PathVariable(name = "id") Long id,
			@RequestBody ArtistDTO artistDTO) {

		artistService.editArtist(artistDTO, id);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void deleteArtist(
			@PathVariable(name = "id") Long id) {

		artistService.deleteArtist(id);
	}
}
