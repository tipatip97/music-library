package com.example.musiclibrary.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
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
	private List<Song> songs;

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

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
}
