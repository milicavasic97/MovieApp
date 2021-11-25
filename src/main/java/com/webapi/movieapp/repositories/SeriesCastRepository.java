package com.webapi.movieapp.repositories;

import com.webapi.movieapp.models.SeriesCast;
import com.webapi.movieapp.models.SeriesCastId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesCastRepository extends JpaRepository<SeriesCast, SeriesCastId> {
}
