package com.example.musiclibrary.controller;

import com.example.musiclibrary.entity.SongEntity;
import com.example.musiclibrary.model.Song;
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
			@RequestBody Song song) {

		songService.saveSong(songService.songModelToEntity(song));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Song getSong(
			@PathVariable(name = "id") Long id) {

		return songService.songEntityToModel(songService.getSong(id));
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Song> getSong(
			@RequestParam(value = "id") List<Long> ids) {
		
		return songService.getSongs(ids).stream()
				.map(songService::songEntityToModel)
				.collect(Collectors.toList());

	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public void updateSong(
			@PathVariable(name = "id") Long id,
			@RequestBody SongEntity songEntity) {

		songService.editSong(songEntity, id);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void deleteSong(
			@PathVariable(name = "id") Long id) {

		songService.deleteSong(id);
	}
}
