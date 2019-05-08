package com.example.musiclibrary.DTO;

import com.example.musiclibrary.entity.Artist;
import com.example.musiclibrary.entity.Song;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ArtistDTO {
	
	private Long id;

	private String name;

	private Date birthDay;

	private List<Long> songIds = new ArrayList<>();
	
	public static ArtistDTO getArtistDTO(Artist artist) {
		ArtistDTO artistDTO = new ArtistDTO();
		
		artistDTO.setId(artist.getId());
		artistDTO.setName(artist.getName());
		artistDTO.setBirthDay(artist.getBirthDay());
		artistDTO.setSongIds(artist.getSongEntities().stream()
				.map(Song::getId)
				.collect(Collectors.toList()));
		
		return artistDTO;
	}

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
