package com.example.musiclibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.musiclibrary.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    void saveAndFlush(Map<String, Object> song, Long id);
//    List<Song> findByAlbum(String album);
}
