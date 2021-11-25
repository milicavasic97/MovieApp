package com.webapi.movieapp.repositories;

import com.webapi.movieapp.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
