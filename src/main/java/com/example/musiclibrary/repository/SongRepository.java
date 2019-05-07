package com.example.musiclibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.musiclibrary.model.Song;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

}
