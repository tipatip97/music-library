package com.example.musiclibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.musiclibrary.entity.SongEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<SongEntity, Long> {

}
