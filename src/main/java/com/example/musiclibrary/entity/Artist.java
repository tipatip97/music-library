package com.example.musiclibrary.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "artist")
public class Artist {

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
	@JoinTable(name = "artists_songs",
			joinColumns = @JoinColumn(name = "artists_id"),
			inverseJoinColumns = @JoinColumn(name = "songs_id"))
	private List<Song> songs = new ArrayList<>();

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

	public List<Song> getSongEntities() {
		return songs;
	}

	public void setSongEntities(List<Song> songEntities) {
		this.songs = songEntities;
	}
}
