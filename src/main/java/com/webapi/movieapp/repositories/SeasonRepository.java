package com.webapi.movieapp.repositories;

import com.webapi.movieapp.models.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season, Integer> {
}
