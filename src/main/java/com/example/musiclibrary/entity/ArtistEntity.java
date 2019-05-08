package com.example.musiclibrary.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "artist")
public class ArtistEntity {

	@Id
	@GeneratedValue
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column
	private String name;

	@Column
	@Temporal(TemporalType.DATE)
	private Date birthDay;

	@ManyToMany
	private List<SongEntity> songs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public List<SongEntity> getSongEntities() {
		return songs;
	}

	public void setSongEntities(List<SongEntity> songEntities) {
		this.songs = songEntities;
	}
}
