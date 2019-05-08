package com.example.musiclibrary.controller;

import com.example.musiclibrary.model.Song;
import com.example.musiclibrary.service.SongService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

		songService.saveSong(song);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Song getSong(
			@PathVariable(name = "id") Long id) {

		return songService.getSong(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Song> getSong(
			@RequestParam(value = "id") List<Long> ids) {

		return songService.getSongs(ids);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public void updateSong(
			@PathVariable(name = "id") Long id,
			@RequestBody Song song) {

		songService.editSong(song, id);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void deleteSong(
			@PathVariable(name = "id") Long id) {

		songService.deleteSong(id);
	}
}
