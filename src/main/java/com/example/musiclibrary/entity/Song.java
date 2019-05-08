package com.example.musiclibrary.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "song")
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
	
	@ManyToMany()
	@JoinTable(name = "artists_songs",
			joinColumns = @JoinColumn(name = "songs_id"),
			inverseJoinColumns = @JoinColumn(name = "artists_id"))
	private List<Artist> artists = new ArrayList<>();
	
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
	
	public List<Artist> getArtistEntities() {
		return artists;
	}
	
	public void setArtistEntities(List<Artist> artistEntities) {
		this.artists = artistEntities;
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
