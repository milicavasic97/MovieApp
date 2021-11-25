package com.webapi.movieapp.repositories;

import com.webapi.movieapp.models.MovieCast;
import com.webapi.movieapp.models.MovieCastId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCastRepository extends JpaRepository<MovieCast, MovieCastId> {
}
