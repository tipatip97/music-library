package com.example.musiclibrary.model;

import java.util.Date;
import java.util.List;

public class Artist {
	
	public Artist() {
	}
	
	public Artist(Long id, String name, Date birthDay, List<Long> songIds) {
		this.id = id;
		this.name = name;
		this.birthDay = birthDay;
		this.songIds = songIds;
	}
	
	private Long id;

	private String name;

	private Date birthDay;

	private List<Long> songIds;

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
	
	public List<Long> getSongIds() {
		return songIds;
	}
	
	public void setSongIds(List<Long> songIds) {
		this.songIds = songIds;
	}
}
