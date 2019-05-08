package com.example.musiclibrary.controller;

import com.example.musiclibrary.controller.errors.NotFoundException;
import com.example.musiclibrary.model.Song;
import com.example.musiclibrary.repository.ArtistRepository;
import com.example.musiclibrary.repository.SongRepository;
import com.example.musiclibrary.service.LibraryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/song")
public class SongController {
	private final LibraryService libraryService;
	private final SongRepository songRepository;
	private final ArtistRepository singerRepository;
	
	public SongController(LibraryService libraryService, SongRepository songRepository, ArtistRepository singerRepository) {
		this.libraryService = libraryService;
		this.songRepository = songRepository;
		this.singerRepository = singerRepository;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void saveSong(
			@RequestBody Song song) {

		songRepository.saveAndFlush(song);
		System.out.println("song created");
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Song getSong(
			@PathVariable(name = "id") Long id) {

		return songRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Song> getSong(
			@RequestParam(value = "id") List<Long> ids) {

		return songRepository.findAllById(ids);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public Song updateSong(
			@PathVariable(name = "id") Long id,
			@RequestBody Song song) {

		Song savedSong = songRepository.findById(id).orElseThrow(NotFoundException::new);

		savedSong.setTitle(song.getTitle());
		savedSong.setAlbum(song.getAlbum());
		savedSong.setReleaseDate(song.getReleaseDate());
		savedSong.setArtists(song.getArtists());

		songRepository.saveAndFlush(savedSong);

		return savedSong;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void deleteSong(
			@PathVariable(name = "id") Long id) {

		if (songRepository.existsById(id)) {
			songRepository.deleteById(id);
		} else {
			throw new NotFoundException();
		}
	}
}
