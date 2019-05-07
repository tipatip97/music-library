package com.example.musiclibrary.controller;

import com.example.musiclibrary.model.Artist;
import com.example.musiclibrary.model.Song;
import com.example.musiclibrary.repository.ArtistRepository;
import com.example.musiclibrary.repository.SongRepository;
import com.example.musiclibrary.service.LibraryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class SongController {
	private final LibraryService libraryService;
	private final SongRepository songRepository;
	private final ArtistRepository singerRepository;
	
	public SongController(LibraryService libraryService, SongRepository songRepository, ArtistRepository singerRepository) {
		this.libraryService = libraryService;
		this.songRepository = songRepository;
		this.singerRepository = singerRepository;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "test")
	public void test() {
		Song song = new Song();
		song.setTitle("LALA");
		songRepository.saveAndFlush(song);
		
		Artist singer = new Artist();
		singer.setName("Anton");
		singer.setBirthDay(new Date());
		
		List<Song> songs = new ArrayList<>();
		songs.add(song);
		singer.setSongs(songs);
		
		singerRepository.saveAndFlush(singer);
		
		System.out.println(libraryService.test());
	}

}
