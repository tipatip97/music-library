package com.example.musiclibrary.controller;

import com.example.musiclibrary.DTO.SongDTO;
import com.example.musiclibrary.service.SongService;
import org.springframework.web.bind.annotation.*;

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
	public void saveSong(
			@RequestBody SongDTO songDTO) {

		songService.saveSong(songDTO);
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
	public void updateSong(
			@PathVariable(name = "id") Long id,
			@RequestBody SongDTO songDTO) {

		songService.editSong(songDTO, id);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void deleteSong(
			@PathVariable(name = "id") Long id) {

		songService.deleteSong(id);
	}
}
