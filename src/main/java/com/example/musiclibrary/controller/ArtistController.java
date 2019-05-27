package com.example.musiclibrary.controller;

import com.example.musiclibrary.DTO.ArtistDTO;
import com.example.musiclibrary.service.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> saveArtist(
			@RequestBody ArtistDTO artistDTO,
			UriComponentsBuilder b) {

		Long id = artistService.saveArtist(artistDTO).getId();

		UriComponents uriComponents = b.path("/artist/{id}").buildAndExpand(id);

		return ResponseEntity.created(uriComponents.toUri()).build();
	}
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
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
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateArtist(
			@PathVariable(name = "id") Long id,
			@RequestBody ArtistDTO artistDTO) {

		artistService.editArtist(artistDTO, id);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteArtist(
			@PathVariable(name = "id") Long id) {

		artistService.deleteArtist(id);
	}
}
