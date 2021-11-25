package com.webapi.movieapp.repositories;

import com.webapi.movieapp.models.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
}
