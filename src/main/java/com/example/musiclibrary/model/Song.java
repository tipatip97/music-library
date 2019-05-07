package com.example.musiclibrary.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Song {
	
	@Id
	@GeneratedValue
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column
	private String title;
	
	@Column
	private String album;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date releaseDate;
	
	@ManyToMany(mappedBy = "songs")
	private List<Artist> artists;
	
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
	
	public List<Artist> getArtists() {
		return artists;
	}
	
	public void setArtists(List<Artist> artists) {
		this.artists = artists;
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
}
