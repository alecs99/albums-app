package com.facultate.albumstore.repository;

import com.facultate.albumstore.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
}