package com.example.musiclibrary.model;

import java.util.Date;
import java.util.List;

public class Song {
	
	public Song() {
	}
	
	public Song(Long id, String title, String album, Date releaseDate, List<Long> artistIds) {
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
	
	private List<Long> artistIds;
	
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
