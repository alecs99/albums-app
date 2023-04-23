package com.facultate.albumstore.repository;

import com.facultate.albumstore.entity.Albume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumeRepository extends JpaRepository<Albume, Integer> {
}