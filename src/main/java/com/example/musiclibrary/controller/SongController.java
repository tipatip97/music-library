package com.example.musiclibrary.controller;

import com.example.musiclibrary.DTO.SongDTO;
import com.example.musiclibrary.service.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/song")
public class SongController {
	private final SongService songService;
	
	public SongController(SongService songService) {
		this.songService = songService;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> saveSong(
			@RequestBody SongDTO songDTO,
			UriComponentsBuilder b) {

		Long id = songService.saveSong(songDTO).getId();

		UriComponents uriComponents = b.path("/{id}").buildAndExpand(id);

		return ResponseEntity.created(uriComponents.toUri()).build();
	}

	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public List<SongDTO> getAll() {
		return songService.getAll();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public SongDTO getSong(
			@PathVariable(name = "id") Long id) {

		return SongDTO.getSongDTO(songService.getSong(id));
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<SongDTO> getSong(
			@RequestParam(value = "id") List<Long> ids) {
		
		return songService.getSongs(ids).stream()
				.map(SongDTO::getSongDTO)
				.collect(Collectors.toList());

	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateSong(
			@PathVariable(name = "id") Long id,
			@RequestBody SongDTO songDTO) {

		songService.editSong(songDTO, id);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSong(
			@PathVariable(name = "id") Long id) {

		songService.deleteSong(id);
	}
}
