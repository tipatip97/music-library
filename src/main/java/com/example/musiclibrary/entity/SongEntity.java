package com.example.musiclibrary.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "song")
public class SongEntity {
	
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
	private List<ArtistEntity> artists;
	
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
	
	public List<ArtistEntity> getArtistEntities() {
		return artists;
	}
	
	public void setArtistEntities(List<ArtistEntity> artistEntities) {
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
