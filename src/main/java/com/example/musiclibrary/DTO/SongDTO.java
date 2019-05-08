package com.example.musiclibrary.DTO;

import com.example.musiclibrary.entity.Artist;
import com.example.musiclibrary.entity.Song;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SongDTO {
	
	public SongDTO() {
	}
	
	public SongDTO(Long id, String title, String album, Date releaseDate, List<Long> artistIds) {
		this.id = id;
		this.title = title;
		this.album = album;
		this.releaseDate = releaseDate;
		this.artistIds = artistIds;
	}
	
	private Long id;
	
	private String title;
	
	private String album;
	
	private Date releaseDate;
	
	private List<Long> artistIds = new ArrayList<>();
	
	public static SongDTO getSongDTO(Song song) {
		SongDTO songDTO = new SongDTO();
		
		songDTO.setId(song.getId());
		songDTO.setTitle(song.getTitle());
		songDTO.setAlbum(song.getAlbum());
		songDTO.setReleaseDate(song.getReleaseDate());
		songDTO.setArtistIds(song.getArtistEntities().stream()
				.map(Artist::getId)
				.collect(Collectors.toList()));
		
		return songDTO;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public void setAlbum(String album) {
		this.album = album;
	}
	
	public Date getReleaseDate() {
		return releaseDate;
	}
	
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public List<Long> getArtistIds() {
		return artistIds;
	}
	
	public void setArtistIds(List<Long> artistIds) {
		this.artistIds = artistIds;
	}
}
